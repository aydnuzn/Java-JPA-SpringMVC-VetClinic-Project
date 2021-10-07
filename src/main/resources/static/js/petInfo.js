let pet_id;
let type;
let breedControl;
let jtbp_Id;
$(document).on('click', '#pet_group', function () {
    alert("tiklandi" + this.value);
    pet_id = this.value;

    $.ajax({
        url: './getPetInfo/' + pet_id,
        type: 'GET',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        success: function (data) {
            debugger;
            if (data != null) {
                type=1;
                jtbp_Id = data.joinTypeBreedPet.jtbp_id;
                alert("JTBP --> " +jtbp_Id );
            //    alert("İşlem Başarılı.");
                $('#pet_name').val(data.pet_name)
                $('#pet_chipNumber').val(data.pet_chipNumber)
                $('#pet_earTag').val(data.pet_earTag)
                $('#pet_bornDate').val(data.pet_bornDate.split('T')[0])

                $('#pet_type').val(data.joinTypeBreedPet.typePet.ty_id)
                $('#pet_type').selectpicker('refresh')
                breedControl = data.joinTypeBreedPet.breedPet.br_id
                getBreeds(data.joinTypeBreedPet.typePet.ty_id, breedControl);
                $('#pet_color').val(data.colorPet.color_id)
                $('#pet_color').selectpicker('refresh')
           //     $('#pet_breed').val(data.joinTypeBreedPet.breedPet.br_id)
            //    $('#pet_breed').selectpicker('refresh')
                if(data.pet_gender){
                    $('#pet_gender1').attr('checked', true)
                }else{
                    $('#pet_gender2').attr('checked', true)
                }
                $('#pet_neutering').val(data.pet_neutering)



            } else {
                alert("islem basarisiz");
            }
        },
        error: function (err) {
        //    debugger;
            alert("Hata");
            console.log(err)
        }
    });

});


$('#update_petform').submit(function (event) {
    debugger;
    event.preventDefault();
    console.log("Pet Ekleniyor.");
   // debugger;

    const obj = {
        jtbpId : jtbp_Id,
        petId: pet_id,
        name: $('#pet_name').val(),
        chipNumber: $('#pet_chipNumber').val(),
        earTag: $('#pet_earTag').val(),
        bornDate: $('#pet_bornDate').val(),
        type: $('#pet_type').val(),
        color: $('#pet_color').val(),
        breed: $('#pet_breed').val(),
        gender: document.querySelector(`input[name="pet_gender"]:checked`).value,
        neutering: $('#pet_neutering').val(),
    }

    $.ajax({
        url: './updatePet',
        type: 'POST',
        data: JSON.stringify(obj),
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        success: function (data) {
        //    debugger;
            if (data == true) {
                alert("İşlem Başarılı.");
                $('#modalClose').click();
                location.reload();
            } else {
                alert("Pet girişleri validation'ı geçemedi.");
            }
        },
        error: function (err) {
        //    debugger;
            console.log(err)
        }
    })
});

//Irkları Alma
function getBreeds(index, breedControl) {
    $.ajax({
        url: './getBreeds/' + index,
        type: 'GET',
        contentType: "application/json",
        dataType: 'json',
        success: function (data) {
            if (data) {
                console.log("Veriler" + data)
                addBreedPetSelect(data,breedControl);
                breedControl = 0;
            } else {
                console.log("Seçilen türe ait ırk bulunamadı.")
            }
        },
        error: function (err) {
            console.log(err)
        }
    })
}


function addBreedPetSelect(data, breedId) {
    console.log(data);
    let html = `<option data-subtext="">Seçim Yapınız</option>`;
    if (data != null) {
        for (let i = 0; i < data.length; i++) {
            const item = data[i];
            html += `<option value=` + item.br_id + `>` + item.br_name + `</option>`;
        }
    }
    console.log(html)
    //debugger;

    $("#pet_breed").html(html);
    if(type==1){
        $("#pet_breed").val(breedId);
    }
    $("#pet_breed").selectpicker('refresh');
}


$(document).on('change', '#pet_type', function () {
    let key = this.value;
    type=2;
    getBreeds(key,0);
    alert(key);
});