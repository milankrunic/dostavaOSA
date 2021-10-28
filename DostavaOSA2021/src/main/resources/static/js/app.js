var prikaziArtikle = false;
var dodajArtikal = false;

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
        greska += "/nMorate uneti korisnicko ime!";
    }

    if(lozinkaInput === ""){
        lozGreska = true;
        greska += "/nMorate uneti lozinku!";
    }

    if(korImeGreska || lozGreska){
        alert(greska);
    }

    $.ajax({
        type: "GET",
        contentType: 'application/json; charset=utf-8',
        url: "http://localhost:8080/api/login",  
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
        }

    });
    login();
}

function odrediPrikaz(id){
    prikaziArtikle = false;
    dodavanjeArtikla = false;
    
    login = false;
    
    if(id === "sviArtikli"){
        prikaziArtikle = true;
    }else if(id === "dodajArtikal"){
    	dodavanjeArtikla = true;
    }else if(id === "prijava"){
    	login = true;
    }

    prikazi();
}

function prikazi(){
    
    var artikliTable = $("#artikliTable");
    var dodajArtikal = $("#dodajArtikal");

    artikliTable.hide();
    dodajArtikal.hide();

    if(prikaziArtikle){   
        PrikazSvihArtikala();
    }else if(dodavanjeArtikla){
        $('#nazivArtikla').val("");
        $('#opis').val("");
        $('#cena').val("");
        $('#dodavanje').hide();
        $('#btnDodajArtikal').show();
        $('#izmeniArtikal').hide();
        dajProdavce();
    	dodajArtikal.show();
    }
}