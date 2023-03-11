$(document).ready(function () {
    
    $("#DugmePrikazPretrageArtikla").click(function (event) {
        event.preventDefault();
        PretragaArtikla();
    });
    
    $("#DugmePrikazPretragePorudzbine").click(function (event) {
        event.preventDefault();
        PretragaPorudzbine();
    });
	
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
    
 // ZA PORUDZBINU ----------------------------------------------------------------------------------------------------------------------------------
    
    $("#btnKomentarPorudzbine").click(function (event) {
        event.preventDefault();
        pretragaPoKomentaruPorudzbine();
    });
    
    $("#btnOcenaPorudzbine").click(function (event) {
        event.preventDefault();
        pretragaPorudzbinePoOceni();
    });
    
    $("#btnKomentarAndOcenaPorudzbine").click(function (event) {
        event.preventDefault();
        pretragaPoKomentaruAndOceniPorudzbine();
    });
    
    $("#btnKomentarOrOcenaPorudzbine").click(function (event) {
        event.preventDefault();
        pretragaPoKomentaruOrOceniPorudzbine();
    });
    
    $("#btnCenaPorudzbine").click(function (event) {
        event.preventDefault();
        pretragaPorudzbinePoCeni();
    });

});

function PretragaArtikla(){
	
	var tabelaArtikal = $("#pretragaZaArtikal").slideDown('slow');
	
}

function PretragaPorudzbine(){
	
	var tabelaPorudzbina = $("#pretragaZaPorudzbinu").slideDown('slow');
	
}

function pretragaPoNazivuArtikla(){
	
    var nazivInput = "";
    
    nazivInput = $("#naziv").val();

    var formData = {
        "naziv" : nazivInput
    }

    $.ajax({
        url : "http://localhost:8080/search/nazivQuery?naziv="+nazivInput,
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
//    var opisInput = "";

    nazivInput = $("#nazivArtikla").val();
    cenaInput = $("#cena").val();
    fileInput = $("#opisFile").val();
//    opisInput = $("#opis").val();

    var formData = new FormData();    
    formData.append( 'naziv', nazivInput );
    formData.append( 'cena', cenaInput );
    formData.append( 'file', $('#opisFile')[0].files[0] );
//    formData.append( 'opis', opisInput );
    
    $.ajax({
        url : "http://localhost:8080/search/pdf",
        type : "POST",
        data : formData,
        processData: false,
        contentType: false,
        success: function(data){
            PrikazSvihArtikala();
            $('#dodajArtikal').hide();
        },
        error : function(e){
            console.log("ERROR: ", e);
        }
    });

}

function pretragaPoOpisuArtikla(){
	
    var opisInput = "";
    
    opisInput = $("#opisPretraga").val();

    console.log("OPIS JE : ", opisInput);
    
    var formData = {
        "opis" : opisInput
    }

    $.ajax({
        url : "http://localhost:8080/search/opisQuery?opis="+opisInput,
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
// ZA PORUDZBINU ----------------------------------------------------------------------------------------------------------------------------------
	
function pretragaPoKomentaruPorudzbine(){
	
    var komentarInput = "";
    
    komentarInput = $("#komentarPorudzbine").val();

    var formData = {
        "text" : komentarInput
    }

    $.ajax({
        url : "http://localhost:8080/search/porudzbinaKomentar",
        type : "POST",
        contentType: 'application/json; charset=utf-8',
        data : JSON.stringify(formData),
        success: function(data){
	          $('#resultPorudzbina').empty();
	          for(i = 0; i < data.length; i++){
	              var result = data[i]
	              $.each(result, function(key, value) {
	            	  if(result.anonimanKomentar===true){
	            		  result.anonimanKomentar="Komentar je anoniman"
	            	  }
	            	  if(result.anonimanKomentar===false){
	            		  result.anonimanKomentar="Komentar nije anoniman"
	            	  }

	                  $('#resultPorudzbina').append('<li>' + key + ': ' + value + '</li>');
	                
	              });
	          }
	          console.log("SUCCESS : ", data);
	          $("#btnKomentarPorudzbine").prop("disabled", false);
        },
        error : function(e){
            alert('Doslo je do neke greške!');
            console.log("ERROR: ", e);
        }
    });
	
}

function pretragaPorudzbinePoOceni(){
	
    var fromInput = "";
    var toInput = "";
    
    fromInput = $("#ocenaFrom").val();
    toInput = $("#ocenaTo").val();

    var formData = {
        "from" : fromInput,
        "to" : toInput
    }

    $.ajax({
        url : "http://localhost:8080/search/porudzbinaOcena?from="+fromInput+"&to="+toInput,
        type : "POST",
        contentType: 'application/json; charset=utf-8',
        data : JSON.stringify(formData),
        success: function(data){
	          $('#resultPorudzbina').empty();
	          for(i = 0; i < data.length; i++){
	              var result = data[i]
	              $.each(result, function(key, value) {
	            	  
	            	  if(result.anonimanKomentar===true){
	            		  result.anonimanKomentar="Komentar je anoniman"
	            	  }
	            	  if(result.anonimanKomentar===false){
	            		  result.anonimanKomentar="Komentar nije anoniman"
	            	  }
	            	  
	            	  $('#resultPorudzbina').append('<li>' + key + ': ' + value + '</li>');
	            	  
	              });
	          }
	          console.log("SUCCESS : ", data);
	          $("#btnOcenaPorudzbine").prop("disabled", false);
        },
        error : function(e){
            alert('Doslo je do neke greške!');
            console.log("ERROR: ", e);
        }
    });
	
}

function pretragaPoKomentaruAndOceniPorudzbine(){
	
	var komentarInput = "";
    var fromInput = "";
    var toInput = "";
    
    komentarInput = $("#komentarAnd").val();
    fromInput = $("#ocenaFromAnd").val();
    toInput = $("#ocenaToAnd").val();

    var formData = {
    	"komentar" : komentarInput,
        "from" : fromInput,
        "to" : toInput
    }

    $.ajax({
        url : "http://localhost:8080/search/porudzbinaKomentarAndOcena?komentar="+komentarInput+"&from="+fromInput+"&to="+toInput,
        type : "POST",
        contentType: 'application/json; charset=utf-8',
        data : JSON.stringify(formData),
        success: function(data){
	          $('#resultPorudzbina').empty();
	          for(i = 0; i < data.length; i++){
	              var result = data[i]
	              $.each(result, function(key, value) {
	            	  
	            	  if(result.anonimanKomentar===true){
	            		  result.anonimanKomentar="Komentar je anoniman"
	            	  }
	            	  if(result.anonimanKomentar===false){
	            		  result.anonimanKomentar="Komentar nije anoniman"
	            	  }
	            	  
	                  $('#resultPorudzbina').append('<li>' + key + ': ' + value + '</li>');
	                  
	              });
	          }
	          console.log("SUCCESS : ", data);
	          $("#btnKomentarAndOcenaPorudzbine").prop("disabled", false);
        },
        error : function(e){
            alert('Doslo je do neke greške!');
            console.log("ERROR: ", e);
        }
    });
	
}

function pretragaPoKomentaruOrOceniPorudzbine(){
	
	var komentarInput = "";
    var fromInput = "";
    var toInput = "";
    
    komentarInput = $("#komentarOr").val();
    fromInput = $("#ocenaFromOr").val();
    toInput = $("#ocenaToOr").val();

    var formData = {
    	"komentar" : komentarInput,
        "from" : fromInput,
        "to" : toInput
    }

    $.ajax({
        url : "http://localhost:8080/search/porudzbinaKomentarOrOcena?komentar="+komentarInput+"&from="+fromInput+"&to="+toInput,
        type : "POST",
        contentType: 'application/json; charset=utf-8',
        data : JSON.stringify(formData),
        success: function(data){
	          $('#resultPorudzbina').empty();
	          for(i = 0; i < data.length; i++){
	              var result = data[i]
	              $.each(result, function(key, value) {
	            	  
	            	  if(result.anonimanKomentar===true){
	            		  result.anonimanKomentar="Komentar je anoniman"
	            	  }
	            	  if(result.anonimanKomentar===false){
	            		  result.anonimanKomentar="Komentar nije anoniman"
	            	  }
	            	  
	                  $('#resultPorudzbina').append('<li>' + key + ': ' + value + '</li>');
	                  
	              });
	          }
	          console.log("SUCCESS : ", data);
	          $("#btnKomentarOrOcenaPorudzbine").prop("disabled", false);
        },
        error : function(e){
            alert('Doslo je do neke greške!');
            console.log("ERROR: ", e);
        }
    });
	
}

function pretragaPorudzbinePoCeni(){
	
    var fromInput = "";
    var toInput = "";
    
    fromInput = $("#cenaPFrom").val();
    toInput = $("#cenaPTo").val();

    var formData = {
        "from" : fromInput,
        "to" : toInput
    }

    $.ajax({
        url : "http://localhost:8080/search/porudzbinaCena?from="+fromInput+"&to="+toInput,
        type : "POST",
        contentType: 'application/json; charset=utf-8',
        data : JSON.stringify(formData),
        success: function(data){
	          $('#resultPorudzbina').empty();
	          for(i = 0; i < data.length; i++){
	              var result = data[i]
	              $.each(result, function(key, value) {
	            	  
	            	  if(result.anonimanKomentar===true){
	            		  result.anonimanKomentar="Komentar je anoniman"
	            	  }
	            	  if(result.anonimanKomentar===false){
	            		  result.anonimanKomentar="Komentar nije anoniman"
	            	  }
	            	  
	            	  $('#resultPorudzbina').append('<li>' + key + ': ' + value + '</li>');
	            	  
	              });
	          }
	          console.log("SUCCESS : ", data);
	          $("#btnCenaPorudzbine").prop("disabled", false);
        },
        error : function(e){
            alert('Doslo je do neke greške!');
            console.log("ERROR: ", e);
        }
    });
	
}