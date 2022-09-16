function PrikazSvihKupaca(){

    var tabelaKupca = $("#kupciTable");
    var tbodyKupac = $("#tbodyKupac");

    function prikaziKupce(){
    	$('#dodavanje').hide();
    	$('#prijava').hide();
    	$('#btnLogin').hide();
    	$('#prikazDugicaSvihKorisnika').hide();
    	$('#pretragaArtikla').hide();
    	$('#pretragaArtiklaDole').hide();
        $.ajax({

            type: "GET",
            contentType : 'application/json; charset=utf-8',
            url : "http://localhost:8080/api/kupac",
            success : function(result){
            	tabelaKupca.show();
            	tbodyKupac.empty();
                for(kupac in result){
                	tbodyKupac.append(
                    '<tr>'
                			
                        +'<td align="center">'+result[kupac].idKupac+'</td>'
                        +'<td align="center">'+result[kupac].imeKupca+'</td>'
                        +'<td align="center">'+result[kupac].prezimeKupca+'</a>'+'</td>'
                        +'<td align="center">'+result[kupac].korImeKupca+'</td>'
                        +'<td align="center">'+result[kupac].adresaKupca+'</td>'
                        +'<td align="center">'+result[kupac].blokiran+'</td>'
                        +'<td>'
                        	+'<button type="submit" class="btn btn-dark" style="margin-right: 5%;" onclick="blokirajKupca('+result[kupac].idKupac+')">BLOK</button>'
	                        +'<button type="submit" class="btn btn-warning" style="margin-right: 5%;" onclick="editKupac('+result[kupac].idKupac+')">IZMENI</button>'
	                        +'<button type="submit" class="btn btn-danger" onclick="deleteKupac('+result[kupac].idKupac+')">OBRIŠI</button>'
                        +'</td>'
                    +'</tr>'
                    
                    )};
                    
            },
            error :function(e){
                alert('Doslo je do neke greške!');
            }


        });
    }
    prikaziKupce();

}

function submitKupac(){

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
            "imeKupca" : imeInput,
            "prezimeKupca" : prezimeInput,
            "korImeKupca" : korisnickoImeInput,
            "lozinkaKupca" : lozinkaInput,
            "adresaKupca" : adresaInput
        }

        $.ajax({
            url : "http://localhost:8080/api/kupac",
            type : "POST",
            contentType: 'application/json; charset=utf-8',
            data : JSON.stringify(formData),
            success: function(){
                alert('Kupac je uspesno dodat!');
                odrediPrikaz('sviKupci');
            },
            error : function(e){
                alert('Doslo je do neke greške!');
                console.log("ERROR: ", e);
            }
        });
    }

}

function editKupac(id){

	$('#kupciTable').hide();
	$('#dodajKupca').show();
	$('#izmeniKupca').show();
	$('#btnDodajKupca').hide();
	$('#pretragaArtikla').hide();
	$('#pretragaArtiklaDole').hide();
	
    function prikaziKupca(){
        $.ajax({
            type: "GET",
            contentType : 'application/json; charset=utf-8',
            url : "http://localhost:8080/api/kupac/" + id,

            success: function(result){
            	
                var idKupac = $("#idKupac");
                var ime = $("#imeKupca");
                var prezime = $("#prezimeKupca");
                var korisnickoIme = $("#korImeKupca");
                var lozinka = $("#lozinkaKupca");
                var adresa = $("#adresaKupca");
                               
                idKupac.val(result.idKupac);
                ime.val(result.imeKupca);
                prezime.val(result.prezimeKupca);
                korisnickoIme.val(result.korImeKupca);
                lozinka.val(result.lozinkaKupca);
                adresa.val(result.adresaKupca);

            },
            error : function(e){
                alert('Doslo je do neke greške!')
                console.log("ERROR: ", e);
            }
        });
    }
    prikaziKupca();

}

function submitUpdateKupac(){
    
    var id = $("#idKupac").val();
    var ime = $("#imeKupca").val();
    var prezime = $("#prezimeKupca").val();
    var korisnickoIme = $("#korImeKupca").val();
    var lozinka = $("#lozinkaKupca").val();
    var adresa = $("#adresaKupca").val();
    
    var formData = {
        "imeKupca" : ime,
        "prezimeKupca" : prezime,
        "korImeKupca" : korisnickoIme,
        "lozinkaKupca" : lozinka,
        "adresaKupca" : adresa
    }

    $.ajax({

        url: "http://localhost:8080/api/kupac/" + id,
        type: "PUT",
        contentType: 'application/json; charset=utf-8',
        data : JSON.stringify(formData),
        success: function(result){
            alert('Kupac ' + result.korImeKupca + ' je uspesno izmenjen!');
            odrediPrikaz('sviKupci');
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });

}

function deleteKupac(id){
    $.ajax({
        url:'http://localhost:8080/api/kupac/' + id,
        type: 'DELETE',
        contentType: 'application/json; charset=utf-8',
        success: function(result){
            alert("Kupac je obrisan!");
            odrediPrikaz('sviKupci');
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });
}

function blokirajKupca(id){
    $.ajax({
        url:'http://localhost:8080/api/kupac/' + id + '/blokiranje',
        type: 'POST',
        contentType: 'application/json; charset=utf-8',
        success: function(result){
        	if(result.blokiran){
        		alert("Kupac je blokiran!");
        	}else{
        		alert("Kupac je odblokiran!");
        	}
            odrediPrikaz('sviKupci');
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });
}

function vratiNaPocetnuKupac(){
	$('#kupciTable').hide();
	$('#prodavciTablee').hide();
	$('#DugmeOdjava').show();
	$('#DugmePrikazLogiina').show();
	$('#DugmePrikazArtikala').show();
	$('#DugmePrikazKorisnike').show();
	$('#DugmeArtikal').show();
	$('#pretragaArtikla').show();
	$('#pretragaArtiklaDole').show();
}

function vratiSaDodavanjaIizmeneKupac(){
	$('#kupciTable').show();
	$('#dodajKupca').hide();
	$('#dodavanjeKupca').show();
	$('#pretragaArtikla').show();
	$('#pretragaArtiklaDole').show();
}

// IZMENA SAMOG SEBE -----------------------------------------------------------------------------------------------------

function prikazKupca(){

	$('#kupciTable').hide();
	$('#dodajKupca').show();
	$('#izmeniKupca').show();
	$('#btnDodajKupca').hide();
	$('#pretragaArtikla').hide();
	$('#pretragaArtiklaDole').hide();
	
    function prikaziKupca(){
        $.ajax({
            type: "GET",
            contentType : 'application/json; charset=utf-8',
            url : "http://localhost:8080/api/kupac/podaci",

            success: function(result){
            	
                var idKupac = $("#idKupac");
                var ime = $("#imeKupca");
                var prezime = $("#prezimeKupca");
                var korisnickoIme = $("#korImeKupca");
                var lozinka = $("#lozinkaKupca");
                var adresa = $("#adresaKupca");
                               
                idKupac.val(result.idKupac);
                ime.val(result.imeKupca);
                prezime.val(result.prezimeKupca);
                korisnickoIme.val(result.korImeKupca);
                lozinka.val(result.lozinkaKupca);
                adresa.val(result.adresaKupca);

            },
            error : function(e){
                alert('Doslo je do neke greške!')
                console.log("ERROR: ", e);
            }
        });
    }
    prikaziKupca();

}

function IzmenaKupca(){
    
    var id = $("#idKupac").val();
    var ime = $("#imeKupca").val();
    var prezime = $("#prezimeKupca").val();
    var korisnickoIme = $("#korImeKupca").val();
    var lozinka = $("#lozinkaKupca").val();
    var adresa = $("#adresaKupca").val();
    
    var lozinkaGreska;
    
    var formData = {
        "imeKupca" : ime,
        "prezimeKupca" : prezime,
        "korImeKupca" : korisnickoIme,
        "lozinkaKupca" : lozinka,
        "adresaKupca" : adresa
    }

    $.ajax({

        url: "http://localhost:8080/api/kupac/" + id,
        type: "PUT",
        contentType: 'application/json; charset=utf-8',
        data : JSON.stringify(formData),
        success: function(result){
            alert('Kupac ' + result.korImeKupca + ' je uspesno izmenjen!');
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });

}

//-------------------------------------------------------------------------------------------------------------------------------

function PrikazSvihProdavacaZaNarudzbinu(){

    var tabelaProdavaca = $("#prodavciTablee");
    var tbodyProdavac = $("#tbodyProdavac");

    function prikaziProdavcee(){
    	
    	$('#pretragaArtikla').hide();
    	$('#pretragaArtiklaDole').hide();
    	
        $.ajax({

            type: "GET",
            contentType : 'application/json; charset=utf-8',
            url : "http://localhost:8080/api/prodavac",
            success : function(result){
            	tabelaProdavaca.show();
            	tbodyProdavac.empty();
                for(prodavac in result){
                	tbodyProdavac.append(
                    '<tr>'
                			
                        +'<td align="center">'+result[prodavac].idProdavac+'</td>'
                        +'<td align="center">'+result[prodavac].ime+'</td>'
                        +'<td align="center">'+result[prodavac].prezime+'</a>'+'</td>'
                        +'<td align="center">'+result[prodavac].nazivProdavca+'</td>'

                        +'<td>'
                        	+'<button type="submit" class="btn btn-success" style="margin-right: 5%;" onclick="PrikazSvihArtikalaProdavacaZaNarucivanje('+result[prodavac].idProdavac+')">ARTIKLI</button>'

                        +'</td>'
                    +'</tr>'
                    
                    )};
                    
            },
            error :function(e){
                alert('Doslo je do neke greške!');
            }


        });
    }
    prikaziProdavcee();

}

function PrikazSvihArtikalaProdavacaZaNarucivanje(id){

    var tabelaArtiklaProdavca = $("#artikliTableProdavac");
    var tbodyArtiklaProdavca = $("#tbodyArtiklaProdavca");

    function prikaziArtikleProdavcaa(){
    	$('#prodavciTablee').hide();
    	$('#DugmeArtikal').hide();
    	$('#DugmeOdjava').hide();
    	$('#pretragaArtikla').hide();
    	$('#pretragaArtiklaDole').hide();
    	
        $.ajax({

            type: "GET",
            contentType : 'application/json; charset=utf-8',
            url : "http://localhost:8080/api/prodavac/" + id + "/artikli",
            success : function(result){
            	tabelaArtiklaProdavca.show();
            	tbodyArtiklaProdavca.empty();
                for(artikal in result){
                	tbodyArtiklaProdavca.append(
                    '<tr>'
                			
                        +'<td align="center">'+result[artikal].idArtikla+'</td>'
                        +'<td align="center">'+result[artikal].naziv+'</td>'
                        +'<td align="center">'+result[artikal].opis+'</a>'+'</td>'
                        +'<td align="center">'+result[artikal].cena+'</td>'
                        +'<td align="center">'+result[artikal].prodavac+'</td>'
                        +'<td>'
                        	+'<button type="submit" class="btn btn-success" style="margin-right: 5%;" onclick="PrikazSvihKomentaraArtikla('+result[artikal].idArtikla+')">KOMENTARI</button>'
	                        +'<button id="narucivanje" class="btn btn-success" onclick="PrikazPocetneKorpe('+result[artikal].idArtikla+')">Dodaj u korpu</button>'
//	                        +'<a th:href="|pocetnaKorpa?id=${'+result[artikal].idArtikla+'}|" class="btn btn-primary" id="kupi">Dodaj u listu zelja</a>'
                        +'</td>'
                    +'</tr>'
                  
                    )};
                    
            },
            error :function(e){
                alert('Doslo je do neke greške!');
            }

        });
    }
    prikaziArtikleProdavcaa();
    
}

function PrikazPocetneKorpe(id){

    var pocetnaKorpaTable = $("#pocetnaKorpaTable");
    var tbodyPocetnaKorpa = $("#tbodyPocetnaKorpa");

    function prikaziPocetnuKorpu(){
    	$('#prodavciTablee').hide();
    	$('#DugmeArtikal').hide();
    	$('#DugmeOdjava').hide();
    	$('#artikliTableProdavac').hide();
    	$('#pretragaArtikla').hide();
    	$('#pretragaArtiklaDole').hide();
    	
        $.ajax({

            type: "GET",
            contentType : 'application/json; charset=utf-8',
            url : "http://localhost:8080/api/artikal/" + id + "/korpaSesija",
            success : function(result){
            	pocetnaKorpaTable.show();
            	tbodyPocetnaKorpa.empty();
                for(artikal in result){
                	tbodyPocetnaKorpa.append(
                    '<tr>'
                			
                        +'<td align="center">'+result[artikal].idArtikla+'</td>'
                        +'<td align="center">'+result[artikal].naziv+'</td>'
                        +'<td align="center">'+result[artikal].opis+'</a>'+'</td>'
                        +'<td align="center">'+result[artikal].cena+'</td>'
                        +'<td align="center">'+result[artikal].prodavac+'</td>'
                        +'<td>'
	                       
                        	+'<button id="narucivanje" class="btn btn-success" onclick="">Kupi</button>'
                        +'</td>'
                    +'</tr>'
                  
                    )};
                    
            },
            error :function(e){
                alert('Doslo je do neke greške!');
            }

        });
    }
    prikaziPocetnuKorpu();
    
}