$(document).ready(function () {
    
    $("#btnNazivArtikla").click(function (event) {
        event.preventDefault();
        pretragaPoNazivuArtikla();
    });
    
    $("#btnDodajArtikal").click(function (event) {
        event.preventDefault();
        submitFile();
    });
    
    $("#btnOpisArtikla").click(function (event) {
        event.preventDefault();
        pretragaPoOpisuArtikla();
    });
    
    $("#btnCenaArtikla").click(function (event) {
        event.preventDefault();
        pretragaPoCeniArtikla();
    });
    
    $("#btnNazivAndCenaArtikla").click(function (event) {
        event.preventDefault();
        pretragaPoNazivuAndCeniArtikla();
    });
    
    $("#btnNazivOrCenaArtikla").click(function (event) {
        event.preventDefault();
        pretragaPoNazivuOrCeniArtikla();
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

function submitFile(){

    var nazivInput = "";
    var cenaInput = "";
    var fileInput = "";

    nazivInput = $("#nazivArtikla").val();
    cenaInput = $("#cena").val();
    fileInput = $("#opisFile").val();

    var formData = new FormData();    
    formData.append( 'naziv', nazivInput );
    formData.append( 'cena', cenaInput );
    formData.append( 'opisFile', $('#opisFile')[0].files[0] );
    
    $.ajax({
        url : "http://localhost:8080/search/pdf",
        type : "POST",
        data : formData,
        processData: false,
        contentType: false,
        success: function(data){
//            alert('File je uspesno dodat!');
        },
        error : function(e){
//            alert('Doslo je do neke greške!');
            console.log("ERROR: ", e);
        }
    });

}

function pretragaPoOpisuArtikla(){
	
    var opisInput = "";
    
    opisInput = $("#opisPretraga").val();

    console.log("OPIS JE : ", opisInput);
    
    var formData = {
        "text" : opisInput
    }

    $.ajax({
        url : "http://localhost:8080/search/artikalOpis",
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
	          $("#btnOpisArtikla").prop("disabled", false);
        },
        error : function(e){
            alert('Doslo je do neke greške!');
            console.log("ERROR: ", e);
        }
    });
	
}

function pretragaPoCeniArtikla(){
	
    var fromInput = "";
    var toInput = "";
    
    fromInput = $("#cenaFrom").val();
    toInput = $("#cenaTo").val();

    var formData = {
        "from" : fromInput,
        "to" : toInput
    }

    $.ajax({
        url : "http://localhost:8080/search/artikalCena?from="+fromInput+"&to="+toInput,
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
	          $("#btnCenaArtikla").prop("disabled", false);
        },
        error : function(e){
            alert('Doslo je do neke greške!');
            console.log("ERROR: ", e);
        }
    });
	
}

function pretragaPoNazivuAndCeniArtikla(){
	
	var nazivInput = "";
    var fromInput = "";
    var toInput = "";
    
    nazivInput = $("#nazivAnd").val();
    fromInput = $("#cenaFromAnd").val();
    toInput = $("#cenaToAnd").val();

    var formData = {
    	"naziv" : nazivInput,
        "from" : fromInput,
        "to" : toInput
    }

    $.ajax({
        url : "http://localhost:8080/search/artikalNazivAndCena?naziv="+nazivInput+"&from="+fromInput+"&to="+toInput,
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
	          $("#btnNazivAndCenaArtikla").prop("disabled", false);
        },
        error : function(e){
            alert('Doslo je do neke greške!');
            console.log("ERROR: ", e);
        }
    });
	
}

function pretragaPoNazivuOrCeniArtikla(){
	
	var nazivInput = "";
    var fromInput = "";
    var toInput = "";
    
    nazivInput = $("#nazivOr").val();
    fromInput = $("#cenaFromOr").val();
    toInput = $("#cenaToOr").val();

    var formData = {
    	"naziv" : nazivInput,
        "from" : fromInput,
        "to" : toInput
    }

    $.ajax({
        url : "http://localhost:8080/search/artikalNazivOrCena?naziv="+nazivInput+"&from="+fromInput+"&to="+toInput,
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
	          $("#btnNazivOrCenaArtikla").prop("disabled", false);
        },
        error : function(e){
            alert('Doslo je do neke greške!');
            console.log("ERROR: ", e);
        }
    });
	
}
