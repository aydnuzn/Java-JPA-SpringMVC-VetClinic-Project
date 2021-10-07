package com.works.controllers;

import com.works.entities.Customer;
import com.works.entities.constant.pets.*;
import com.works.entities.projections.AllCustomerPetInfo;
import com.works.properties.UpdatePetInterlayer;
import com.works.repositories.*;
import com.works.restcontroller.ProfileInfoRestController;
import com.works.utils.Util;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class CustomerInfoController {

    final PetRepository petRepository;
    final ProfileInfoRestController profileInfoRestController;
    final JoinPetCustomerRepository joinPetCustomerRepository;
    final TypePetRepository typePetRepository;
    final ColorPetRepository colorPetRepository;
    final BreedPetRepository breedPetRepository;
    final JoinTypeBreedPetRepository joinTypeBreedPetRepository;

    public CustomerInfoController(PetRepository petRepository, ProfileInfoRestController profileInfoRestController, JoinPetCustomerRepository joinPetCustomerRepository, TypePetRepository typePetRepository, ColorPetRepository colorPetRepository, BreedPetRepository breedPetRepository, JoinTypeBreedPetRepository joinTypeBreedPetRepository) {
        this.petRepository = petRepository;
        this.profileInfoRestController = profileInfoRestController;
        this.joinPetCustomerRepository = joinPetCustomerRepository;
        this.typePetRepository = typePetRepository;
        this.colorPetRepository = colorPetRepository;
        this.breedPetRepository = breedPetRepository;
        this.joinTypeBreedPetRepository = joinTypeBreedPetRepository;
    }

    Integer cu_id;
    @GetMapping("/customerinfo/{stCid}")
    public String customerinfo(@PathVariable String stCid, Model model) {
        Integer cid = 0;

        try {
            cid = Integer.parseInt(stCid);
            System.out.println(cid);
        } catch (Exception e) {
            Util.log("CustomerInfoController Error " + e, this.getClass());
            return "redirect:/customerlist";
        }

        String value = "/images/profiles/anonim.jpg";
        model.addAttribute("profilePhoto", value);

        cu_id = cid;
        List<AllCustomerPetInfo> allCustomerPetInfoList = petRepository.getCustomerPets(cid);
        allCustomerPetInfoList.forEach(item -> {
            System.out.println(item);
        });
        //Müşteri ekranı açılabilmesi için, en az 1 pete sahip olmalı.
        //Bu müşterinin petleri ırka, tipe, renge vs sahip olmalı.
        if (allCustomerPetInfoList == null || allCustomerPetInfoList.size() == 0) {
            return "redirect:/customerlist";
        }
        model.addAttribute("all", allCustomerPetInfoList);

        model.addAttribute("typeList", typePetRepository.findAll());
        model.addAttribute("colorList", colorPetRepository.findAll());

        return "customerinfo";
    }

    @GetMapping("/deletePet/{index}")
    public String petDelete(@PathVariable String index, Model model){
        try{
            Integer petId = Integer.parseInt(index);

            Optional<JoinPetCustomer> optPetInfo = joinPetCustomerRepository.findById(petId);
            if(optPetInfo.isPresent()){
                // ilk önce bu peti kullanan(bagli tablo - hangi customer da hangi pet var) sayfa silinecek
                joinPetCustomerRepository.deleteById(optPetInfo.get().getJpt_id());
                // sonra bu petin baglı oldugu ırk ve cinsleri tutan tablo silinecek
                Pet pet = optPetInfo.get().getPet();

                JoinTypeBreedPet joinPetId = pet.getJoinTypeBreedPet();

                // ardından peti silecez
                petRepository.delete(pet);

                joinTypeBreedPetRepository.delete(joinPetId);            }


            List<AllCustomerPetInfo> allCustPet = petRepository.getCustomerPets(cu_id);
            //Müşteri ekranı açılabilmesi için, en az 1 pete sahip olmalı.
            if (allCustPet == null || allCustPet.size() == 0) {
                return "redirect:/customerlist";
            }
            model.addAttribute("all", allCustPet);
            return "redirect:/customerinfo/" + cu_id;
        }catch (Exception ex){
            Util.log("Customer's Pet Delete Error : " + ex, this.getClass());
        }
        return "redirect:/customerlist";
    }


    @PostMapping("/customerinfo/updatePet")
    @ResponseBody
    public Boolean updatePet(@RequestBody @Valid UpdatePetInterlayer item, BindingResult bindingResults){

        Boolean feedBack = false;
        if (!bindingResults.hasErrors()) {
            System.out.println(item);
            try {
                Pet pet = new Pet();
                pet.setPet_name(item.getName());
                pet.setPet_chipNumber(item.getChipNumber());
                pet.setPet_earTag(item.getEarTag());
                pet.setPet_bornDate(item.getBornDate());
                if (item.getNeutering() != null) {
                    pet.setPet_neutering(true);
                } else {
                    pet.setPet_neutering(false);
                }

                if (item.getGender().equals("1")) {
                    pet.setPet_gender(true);
                } else {
                    pet.setPet_gender(false);
                }

                //Color
                Integer color_id = 0;
                if (!item.getColor().equals("0")) {
                    color_id = Integer.parseInt(String.valueOf(item.getColor()));
                } else {
                    return false;
                }
                System.out.println("color_id : " + color_id);
                Optional<ColorPet> optColor_pet = colorPetRepository.findById(color_id);
                if (optColor_pet.isPresent()) {
                    pet.setColorPet(optColor_pet.get());
                }

                //Irk Tür Nesnesi oluşturma
                JoinTypeBreedPet joinTypeBreedPet = new JoinTypeBreedPet();

                //Type
                Integer type_id = 0;
                if (!item.getType().equals("0")) {
                    type_id = Integer.parseInt(String.valueOf(item.getType()));
                } else {
                    return false;
                }
                System.out.println("type_id : " + type_id);
                Optional<TypePet> optType = typePetRepository.findById(type_id);
                if (optType.isPresent()) {
                    joinTypeBreedPet.setTypePet(optType.get());
                }

                //Breed
                Integer breed_id = 0;
                if (!item.getBreed().equals("0")) {
                    breed_id = Integer.parseInt(String.valueOf(item.getBreed()));
                } else {
                    return false;
                }
                System.out.println("breed_id : " + breed_id);


                // Bağlı tablodaki petin cinsi ve ırkı değişmektedir.
                Optional<BreedPet> optBreed = breedPetRepository.findById(breed_id);
                if (optBreed.isPresent()) {
                    joinTypeBreedPet.setBreedPet(optBreed.get());
                }
                joinTypeBreedPet.setJtbp_id(item.getJtbpId());
                System.out.println("***********11111111111111" + item.getJtbpId());
                joinTypeBreedPet = joinTypeBreedPetRepository.saveAndFlush(joinTypeBreedPet);
                System.out.println("*************222222222222" + joinTypeBreedPet.getJtbp_id());
                pet.setJoinTypeBreedPet(joinTypeBreedPet);

                pet.setPet_id(item.getPetId());
                petRepository.saveAndFlush(pet);
                feedBack= true;

            } catch (Exception e) {
                Util.log("CustomerListController Error : " + e, this.getClass());
            }
        }
        return feedBack;
    }

    @GetMapping("/customerinfo/getPetInfo/{index}")
    @ResponseBody
    public Pet getPetInfo(@PathVariable String index){
        try{
            Integer petId = Integer.parseInt(index);
            return petRepository.findById(petId).get();
        }catch (Exception ex){
            Util.log("CustomerInfo getPetInfo Error :" + ex, this.getClass());
        }
        return null;
    }

    @GetMapping("/customerinfo/getBreeds/{stIndex}")
    @ResponseBody
    public List<BreedPet> getXBreeds(@PathVariable String stIndex) {
        try {
            Integer index = Integer.parseInt(stIndex);
            return breedPetRepository.getXDistricts(index);
        } catch (Exception e) {
            Util.log("CustomerController getXBreeds Error : " + e, this.getClass());
        }
        return null;
    }

}
