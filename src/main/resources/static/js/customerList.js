function getAllRows() {
    $.ajax({
        url: '/customerlist/getAllCustomer',
        type: 'GET',
        contentType: "application/json",
        dataType: 'json',
        success: function (data) {
            if (data != null) {
                rows(data);
            }
        },
        error: function (err) {
            console.log(err)
        }
    })
}

getAllRows();

function rows(data) {
    console.log(data)
    let html = ``
    if (data.length < 1) {
        $('#error_message').show();
        $('#customer_control').hide();
    }
    for (let i = 0; i < data.length; i++) {
        const item = data[i];
        html += `<tr role="row" class="odd">
            <td>` + item.cu_id + `</td>
            <td>` + item.cu_name + `</td>
            <td>` + item.cu_surname + `</td>
            <td>` + item.cu_tel1 + `</td>
            <td>` + item.cu_mail + `</td>
            <td>` + item.cu_tcnumber + `</td>
            <td>` + item.customerGroup.cu_gr_name + `</td>
            <td class="text-right" >
              <div class="btn-group" role="group" aria-label="basic mixed styles example">      
                 <button onclick="fncAddPet(` + item.cu_id + `)" type="button" class="btn btn-success">PET EKLE</button>     
                <a href="http://localhost:8090/customerinfo/` + item.cu_id + `" type="button"  class="btn btn-outline-primary "><i class="far fa-eye"></i></a>
               </div>
            </td>
          </tr>`;
    }
    if (data.length > 0) {
        $('#error_message').hide();
        $('#customer_control').show();
        $('#tableCustomerList').html(html);
    }

}

let globalcu_id;

function fncAddPet(cu_id) {
    globalcu_id = cu_id;
    $('#modalButton').click();
}


$(document).on('keyup', '#searchCustomer', function (e) {
    let strSearch = $("#searchCustomer").val();
    console.log(strSearch);
    if (strSearch.trim() != "") {
        getAllSearchList(strSearch);
    } else {
        getAllRows();
    }
})

function getAllSearchList(strSearch) {
    $.ajax({
        url: '/customerlist/search/' + strSearch,
        type: 'GET',
        contentType: "application/json",
        dataType: 'json',
        success: function (data) {
            if (data != null) {
                rows(data);
            } else {
                alert("Arama sonucu boş.")
                rows(null);
            }
        },
        error: function (err) {
            console.log(err)
        }
    })
}

//**********************************************
//**********************************************
//**********************************************
//Irkları Alma
function getBreeds(index) {
    $.ajax({
        url: './customer/getBreeds/' + index,
        type: 'GET',
        contentType: "application/json",
        dataType: 'json',
        success: function (data) {
            if (data) {
                console.log("Veriler" + data)
                addBreedPetSelect(data);
            } else {
                console.log("Seçilen türe ait ırk bulunamadı.")
            }
        },
        error: function (err) {
            console.log(err)
        }
    })
}


function addBreedPetSelect(data) {

    console.log(data);
    let html = `<option data-subtext="">Seçim Yapınız</option>`;
    if (data != null) {
        for (let i = 0; i < data.length; i++) {
            const item = data[i];
            html += `<option value=` + item.br_id + `>` + item.br_name + `</option>`;
        }
    }
    console.log(html)

    $("#pet_breed").html(html);
    $("#pet_breed").selectpicker('refresh');
}

//**************************************************

$(document).on('change', '#pet_type', function () {
    let key = this.value;
    getBreeds(key);
});


//$(document).on('click', '#addPet', function () {
$( "#add_petform" ).submit(function( event ) {
    event.preventDefault();

        console.log("Pet Ekleniyor.");
        debugger;

    const obj = {
        cu_id: globalcu_id,
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
        url: './customerlist/addPet',
        type: 'POST',
        data: JSON.stringify(obj),
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        success: function (data) {
            debugger;
            if (data == true) {
                alert("İşlem Başarılı.");
                alert("denee")
                $('#modalClose').click();
            } else {
                alert("Pet girişleri validation'ı geçemedi.");
            }
        },
        error: function (err) {
            debugger;
            console.log(err)
        }
    })
});
