function pokaziLogin(){
	$('#prijava').show();
	$('#btnLogin').hide();
	$('#DugmePrikazArtikala').hide();
}

function pokaziDugmiceKorisnika(){
	$('#prikazDugicaSvihKorisnika').show();
	$('#DugmePrikazLogiina').hide();
	$('#DugmePrikazArtikala').hide();
	$('#DugmePrikazKorisnike').hide();
}

function login(){
	
    var greska = "";
    var korisnickoInput = "";
    var lozinkaInput = "";

    console.log('Ovde dodje!');
    
    korisnickoInput = $("#inputKorisnickoIme").val();
    lozinkaInput = $("#inputSifru").val();

    console.log(korisnickoInput);
    console.log(lozinkaInput);
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
//        var formData = {
//                "korIme" : korisnickoInput,
//                "lozinka" : lozinkaInput
//        }

        $.ajax({
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            url: "http://localhost:8080/api/login",  
//            data : JSON.stringify(formData),
            success: function(result){
                for(korisnik in result){
                    if(korisnickoInput === korisnik.korIme && lozinkaInput === korisnik.lozinka && korisnik.tipKorisnika === "KUPAC"){
                        alert("KUPAC");
                    }else if(korisnickoInput === korisnik.korIme && lozinkaInput === korisnik.lozinka && korisnik.tipKorisnika === "PRODAVAC"){
                        alert("PRODAVAC");
            		}else if(korisnickoInput === korisnik.korIme && lozinkaInput === korisnik.lozinka && korisnik.tipKorisnika === "ADMINISTRATOR"){
                    	alert("ADMINISTRATOR");
                    }else{
                    	alert("Pogresni podaci");
                    }
                }
            },
            error : function(e){
                alert('Doslo je do neke greške!');
                console.log("ERROR: ", e);
            }

        });
        login();
    }
}

function odrediPrikaz(id){
    prikaziArtikle = false;
    dodavanjeArtikla = false;
    
    prikaziProdavce = false;
    dodajProdavca = false;
    
    if(id === "sviArtikli"){
        prikaziArtikle = true;
    }else if(id === "dodajArtikal"){
    	dodavanjeArtikla = true;
    }else if(id === "sviProdavci"){
    	prikaziProdavce = true;
    }else if(id === dodajProdavca){
    	dodajProdavca = true;
    }

    prikazi();
}

function prikazi(){
    
    var artikliTable = $("#artikliTable");
    var dodajArtikal = $("#dodajArtikal");
    
    var prodavciTable = $("#prodavciTable");
    var dodajProdavca = $("#dodajProdavca");

    artikliTable.hide();
    dodajArtikal.hide();
    
    prodavciTable.hide();
    dodajProdavca.hide();

    if(prikaziArtikle){   
        PrikazSvihArtikala();
    }else if(dodavanjeArtikla){
        $('#izmeniArtikal').hide();
        $('#btnDodajArtikal').show();
        dajProdavce();
    	dodajArtikal.show();
    }else if(prikaziProdavce){
    	PrikazSvihProdavaca();
    }else if(dodajProdavca){
    	$("#izmeniProdavca").hide();
    	$("#btnDodajProdavca").show();
        dodajProdavca.show();
    }
}