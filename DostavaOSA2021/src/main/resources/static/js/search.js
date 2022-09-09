$(document).ready(function () {
    
    $("#btnNazivArtikla").click(function (event) {
        event.preventDefault();
        pretragaPoNazivuArtikla();
    });

});

function pretragaPoNazivuArtikla(){
	
    var nazivInput = "";
    
    nazivInput = $("#naziv").val();

    var formData = {
        "text" : nazivInput
    }

    $.ajax({
        url : "http://localhost:8080/search/artikalNaziv",
        type : "POST",
        contentType: 'application/json; charset=utf-8',
        data : JSON.stringify(formData),
        success: function(data){
	          $('#result').empty();
	          for(i = 0; i < data.length; i++){
	              var result = data[i]
	              $.each(result, function(key, value) {
	                $('#result').append('<li>' + key + ': ' + value + '</li>');
	              });
	          }
	          console.log("SUCCESS : ", data);
	          $("#btnNazivArtikla").prop("disabled", false);
        },
        error : function(e){
            alert('Doslo je do neke greške!');
            console.log("ERROR: ", e);
        }
    });
  

}