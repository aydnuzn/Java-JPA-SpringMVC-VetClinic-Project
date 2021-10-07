package com.works.controllers;

import com.works.entities.Customer;
import com.works.entities.constant.pets.*;
import com.works.properties.AddPetInterlayer;
import com.works.repositories.*;
import com.works.utils.Util;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("/customerlist")
@Controller
public class CustomerListController {

    final CustomerRepository customerRepository;
    final TypePetRepository typePetRepository;
    final ColorPetRepository colorPetRepository;
    final BreedPetRepository breedPetRepository;
    final PetRepository petRepository;
    final JoinTypeBreedPetRepository joinTypeBreedPetRepository;
    final JoinPetCustomerRepository joinPetCustomerRepository;

    public CustomerListController(CustomerRepository customerRepository, TypePetRepository typePetRepository, ColorPetRepository colorPetRepository, BreedPetRepository breedPetRepository, PetRepository petRepository, JoinTypeBreedPetRepository joinTypeBreedPetRepository, JoinPetCustomerRepository joinPetCustomerRepository) {
        this.customerRepository = customerRepository;
        this.typePetRepository = typePetRepository;
        this.colorPetRepository = colorPetRepository;
        this.breedPetRepository = breedPetRepository;
        this.petRepository = petRepository;
        this.joinTypeBreedPetRepository = joinTypeBreedPetRepository;
        this.joinPetCustomerRepository = joinPetCustomerRepository;
    }


    @GetMapping("")
    public String customerList(Model model) {
        model.addAttribute("typeList", typePetRepository.findAll());
        model.addAttribute("colorList", colorPetRepository.findAll());
        return "customerlist";
    }

    @GetMapping("/getAllCustomer")
    @ResponseBody
    public List<Customer> getAllCustomer() {
        return customerRepository.getCustomerSonOtuz();
    }

    @GetMapping("/show/{index}")
    @ResponseBody
    public Customer showCustomer(@PathVariable String index) {
        try {
            Integer customerId = Integer.parseInt(index);
            Optional<Customer> customer = customerRepository.findById(customerId);
            return customer.get();
        } catch (Exception ex) {
            Util.log("CustomerListController showCustomer Error : " + ex, this.getClass());
            return null;
        }


    }

    @GetMapping("/search/{strSearch}")
    @ResponseBody
    public List<Customer> getCustomerSearch(@PathVariable String strSearch) {
        try {
            return customerRepository.searchCustomer(strSearch.trim());
        } catch (Exception ex) {
            Util.log("CustomerListController getCustomerSearch Error : " + ex, this.getClass());
        }
        return null;
    }

    @PostMapping("/addPet")
    @ResponseBody
    public Boolean addPet(@RequestBody @Valid AddPetInterlayer item, BindingResult bindingResults) {
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
                Optional<BreedPet> optBreed = breedPetRepository.findById(breed_id);
                if (optBreed.isPresent()) {
                    joinTypeBreedPet.setBreedPet(optBreed.get());
                }
                joinTypeBreedPet = joinTypeBreedPetRepository.save(joinTypeBreedPet);
                pet.setJoinTypeBreedPet(joinTypeBreedPet);

                pet = petRepository.save(pet);

                JoinPetCustomer joinPetCustomer = new JoinPetCustomer();
                Optional<Customer> customer = customerRepository.findById(item.getCu_id());
                if (customer.isPresent()) {
                    joinPetCustomer.setCustomer(customer.get());
                }

                joinPetCustomer.setPet(pet);

                joinPetCustomerRepository.save(joinPetCustomer);
                feedBack = true;
                System.out.println("Added joinPetCustomers: " + joinPetCustomer);
            } catch (Exception e) {
                Util.log("CustomerListController Error : " + e, this.getClass());
            }
        }
        return feedBack;
    }


}