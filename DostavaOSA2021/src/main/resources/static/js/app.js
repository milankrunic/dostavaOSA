function pokaziLogin(){
	$('#prijava').show();
	$('#btnRegistracija').show();
	$('#btnLogin').hide();
	$('#DugmePrikazArtikala').hide();
}

function pokaziDugmiceKorisnika(){
	$('#prikazDugicaSvihKorisnika').show();
	$('#DugmeOdjava').hide();
	$('#DugmePrikazLogiina').hide();
	$('#DugmePrikazArtikala').hide();
	$('#DugmePrikazKorisnike').hide();
}

function login(){
		
    var greska = "";
    var korisnickoInput = "";
    var lozinkaInput = "";
    
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

function prikaziFormuKupac(){
	$("#dodajKupca").show();
	$("#dodajProdavca").hide();
	$("#registracija").hide();
}

function prikaziFormuPordavac(){
	$("#dodajProdavca").show();	
	$("#dodajKupca").hide();
	$("#registracija").hide();
}

function odrediPrikaz(id){
    prikaziArtikle = false;
    dodavanjeArtikla = false;
    
    prikaziAdmine = false;
    dodavanjeAdmina = false;
    
    prikaziProdavce = false;
    dodavanjeProdavca = false;
    
    prikaziKupce = false;
    dodavanjeKupca = false;
    
    prikaziArtikleProdavca = false;
    dodavanjeArtiklaProdavca = false;
    
    if(id === "sviArtikli"){
        prikaziArtikle = true;
    }else if(id === "dodajArtikal"){
    	dodavanjeArtikla = true;
    }else if(id === "sviAdministratori"){
    	prikaziAdmine = true;
    }else if(id === "dodajAdmina"){
    	dodavanjeAdmina = true;
    }else if(id === "sviProdavci"){
    	prikaziProdavce = true;
    }else if(id === "dodajProdavca"){
    	dodavanjeProdavca = true;
    }else if(id === "sviKupci"){
    	prikaziKupce = true;
    }else if(id === "dodajKupca"){
    	dodavanjeKupca = true;
    }else if(id === "artikliProdavca"){
    	prikaziArtikleProdavca = true;
    }else if(id === "dodajArtikal"){
    	dodavanjeArtiklaProdavca();
    }

    prikazi();
}

function prikazi(){
    
    var artikliTable = $("#artikliTable");
    var dodajArtikal = $("#dodajArtikal");
    
    var adminTable = $("#adminTable");
    var dodajAdmina = $("#dodajAdmina");
    
    var prodavciTable = $("#prodavciTable");
    var dodajProdavca = $("#dodajProdavca");
    
    var kupciTable = $("#kupciTable");
    var dodajKupca = $("#dodajKupca");
    
    var artikliTableProdavac = $("#artikliTableProdavac");
    var dodajArtikalProdavac = $("#dodajArtikal");

    artikliTable.hide();
    dodajArtikal.hide();
    
    adminTable.hide();
    dodajAdmina.hide();
    
    prodavciTable.hide();
    dodajProdavca.hide();
    
    kupciTable.hide();
    dodajKupca.hide();
    
    artikliTableProdavac.hide();

    if(prikaziArtikle){   
        PrikazSvihArtikala();
    }else if(dodavanjeArtikla){
        $('#izmeniArtikal').hide();
        $('#btnDodajArtikalProdavca').hide();
        $('#btnDodajArtikal').show();
        dajProdavce();
    	dodajArtikal.show();
    }else if(prikaziAdmine){
    	PrikazSvihAdministratora()
    }else if(dodavanjeAdmina){
    	$("#izmeniAdmina").hide();
    	$("#btnDodajAdmina").show();
    	dodajAdmina.show();
    }else if(prikaziProdavce){
    	PrikazSvihProdavaca();
    }else if(dodavanjeProdavca){
    	$("#izmeniProdavca").hide();
    	$("#btnDodajProdavca").show();
        dodajProdavca.show();
    }else if(prikaziKupce){
    	PrikazSvihKupaca();
    }else if(dodavanjeKupca){
    	$("#izmeniKupca").hide();
    	$("#btnDodajKupca").show();
    	dodajKupca.show();
    }else if(prikaziArtikleProdavca){
    	PrikazSvihArtikalaProdavaca();
    }else if(dodavanjeArtiklaProdavca){
        $('#izmeniArtikalProdavca').hide();
        $('#btnDodajArtikalProdavca').show();
        dajProdavce();
    	dodajArtikal.show();
    }
}
