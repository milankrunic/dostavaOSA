function pokaziLogin(){
	$('#prijava').show();
	$('#btnRegistracija').show();
	$('#btnLogin').hide();
	$('#DugmePrikazArtikala').hide();
}

function PokaziDugmiceKorisnika(){
	$('#prikazDugicaSvihKorisnika').show();
	$('#DugmeOdjava').hide();
	$('#DugmePrikazArtikala').hide();
	$('#DugmePrikazKorisnike').hide();
	$('#DugmePrikazKomentare').hide();
	$('#pretragaArtikla').hide();
	$('#pretragaArtiklaDole').hide();
	$('#DugmePrikazPretrageArtikla').hide();
	$('#DugmePrikazPretragePorudzbine').hide();
}

function login(){
		
    var greska = "";
    var korisnickoInput = "";
    var lozinkaInput = "";
    
    korisnickoInput = $("#inputKorisnickoIme").val();
    lozinkaInput = $("#inputSifru").val();

    var korImeGreska = false;
    var lozGreska = false;

    if(korisnickoInput === ""){
        korImeGreska = true;
        greska += "\nMorate uneti korisnicko ime!";
    }

    if(lozinkaInput === ""){
        lozGreska = true;
        greska += "\nMorate uneti lozinku!";
    }

    if(korImeGreska || lozGreska){
        alert(greska);
    }
    else{
        var formData = {
                "korIme" : korisnickoInput,
                "lozinka" : lozinkaInput
        }

        $.ajax({
        	url: "http://localhost:8080/api/korisnik/login",
            type: "POST",
            contentType: "application/json; charset=utf-8",  
            data : JSON.stringify(formData),
            success: function (response) {

            	if(response.blokiran === true){
            		alert("Korisnik je blokiran!");
            	}else if(response.tipKorisnika === "KUPAC"){
        			window.location.href = "kupac.html";
        		}else if(response.tipKorisnika === "PRODAVAC"){
        			window.location.href = "prodavac.html";
        		    console.log("KORISNIK: " + response);
        		}else if(response.tipKorisnika === "ADMINISTRATOR"){
        			window.location.href = "admin.html";
        		}           	
        		              
            },
            error: function () {
                alert("Uneli ste pogresne podatke!");
            }

        });
    }
}

//----------------- ZA REGISTRACIJU --------------------------------------------------------------

function PrikaziFormuKupac(){
	$("#dodajKupca").show();
	$("#dodajProdavca").hide();
	$("#registracija").hide();
}

function RegisterKupac(){

    var greska = "";
    var imeInput = "";
    var prezimeInput = "";
    var korisnickoImeInput = "";
    var lozinkaInput = "";
    var adresaInput = "";
    
    imeInput = $("#imeKupca").val();
    prezimeInput = $("#prezimeKupca").val();
    korisnickoImeInput = $("#korImeKupca").val();
    lozinkaInput = $("#lozinkaKupca").val();
    adresaInput = $("#adresaKupca").val();

    var imeGreska;
    var prezimeGreska;
    var korImeGreska;
    var lozinkaGreska;
    var adresaGreska;

    if(imeInput === ""){
    	imeGreska = true;
        greska += "\nMorate uneti ime kupca!";
    }
    if(prezimeInput === ""){
    	prezimeGreska = true;
        greska += "\nMorate uneti prezime kupca!";
    }
    if(korisnickoImeInput === ""){
    	korImeGreska = true;
        greska += "\nMorate uneti korisnicko ime kupca!";
    }
    if(lozinkaInput === ""){
    	lozinkaGreska = true;
        greska += "\nMorate uneti lozinku kupca!";
    }
    if(adresaInput === ""){
    	adresaGreska = true;
        greska += "\nMorate uneti adresu kupca!";
    }

    if(imeGreska || prezimeGreska || korImeGreska || lozinkaGreska || adresaGreska){
        alert(greska);
    }
    else{
        var formData = {
            "ime" : imeInput,
            "prezime" : prezimeInput,
            "korIme" : korisnickoImeInput,
            "lozinka" : lozinkaInput,
            "adresaKupca" : adresaInput
        }

        $.ajax({
            url : "http://localhost:8080/api/kupac",
            type : "POST",
            contentType: 'application/json; charset=utf-8',
            data : JSON.stringify(formData),
            success: function(){
                alert('Kupac je uspesno dodat!');
                window.location.href = "/";
            },
            error : function(e){
                alert('Doslo je do neke greške!');
                console.log("ERROR: ", e);
            }
        });
    }

}

function PrikaziFormuPordavac(){
	$("#dodajProdavca").show();	
	$("#dodajKupca").hide();
	$("#registracija").hide();
}

function RegisterProdavac(){

    var greska = "";
    var imeInput = "";
    var prezimeInput = "";
    var korisnickoImeInput = "";
    var lozinkaInput = "";
    var nazivProdavcaInput = "";
    var emailInput = "";
    var adresaInput = "";
    var poslujeOdInput = "";
    
    imeInput = $("#ime").val();
    prezimeInput = $("#prezime").val();
    korisnickoImeInput = $("#korIme").val();
    lozinkaInput = $("#lozinka").val();
    nazivProdavcaInput = $("#nazivProdavca").val();
    emailInput = $("#email").val();
    adresaInput = $("#adresa").val();
    poslujeOdInput = $("#poslujeOd").val();

    var imeGreska;
    var prezimeGreska;
    var korImeGreska;
    var lozinkaGreska;
    var nazivProdavcaGreska;
    var emailGreska;
    var adresaGreska;
    var poslujeOdGreska;
    
    if(imeInput === ""){
    	imeGreska = true;
        greska += "\nMorate uneti ime prodavca!";
    }
    if(prezimeInput === ""){
    	prezimeGreska = true;
        greska += "\nMorate uneti prezime prodavca!";
    }
    if(korisnickoImeInput === ""){
    	korImeGreska = true;
        greska += "\nMorate uneti korisnicko ime prodavca!";
    }
    if(lozinkaInput === ""){
    	lozinkaGreska = true;
        greska += "\nMorate uneti lozinku prodavca!";
    }
    if(nazivProdavcaInput === ""){
    	nazivProdavcaGreska = true;
        greska += "\nMorate uneti naziv prodavnice u kojoj radi prodavac!";
    }
    if(emailInput === ""){
    	emailGreska = true;
        greska += "\nMorate uneti email prodavca!";
    }
    if(adresaInput === ""){
    	adresaGreska = true;
        greska += "\nMorate uneti adresu prodavca!";
    }
    if(poslujeOdInput === ""){
    	poslujeOdGreska = true;
        greska += "\nMorate uneti kad je prodavac poceo da radi!";
    }

    if(imeGreska || prezimeGreska || korImeGreska || lozinkaGreska || nazivProdavcaGreska || emailGreska || adresaGreska || poslujeOdGreska){
        alert(greska);
    }
    else{
        var formData = {
            "ime" : imeInput,
            "prezime" : prezimeInput,
            "korIme" : korisnickoImeInput,
            "lozinka" : lozinkaInput,
            "nazivProdavca" : nazivProdavcaInput,
            "email" : emailInput,
            "adresa" : adresaInput,
            "poslujeOd" : poslujeOdInput
        }

        $.ajax({
            url : "http://localhost:8080/api/prodavac",
            type : "POST",
            contentType: 'application/json; charset=utf-8',
            data : JSON.stringify(formData),
            success: function(){
                alert('Prodavac je uspesno dodat!');
                window.location.href = "/";
            },
            error : function(e){
                alert('Doslo je do neke greške!');
                console.log("ERROR: ", e);
            }
        });
    }

}

//------------------------------------------------------------------------------------------------
