function PrikazSvihKupaca(){

    var tabelaKupca = $("#kupciTable");
    var tbodyKupac = $("#tbodyKupac");

    function prikaziKupce(){
    	$('#dodajKupca').hide();
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
                	if(result[kupac].blokiran){
                    	tbodyKupac.append(
	                        '<tr>'
	                    			
	                            +'<td align="center">'+result[kupac].idKupac+'</td>'
	                            +'<td align="center">'+result[kupac].ime+'</td>'
	                            +'<td align="center">'+result[kupac].prezime+'</a>'+'</td>'
	                            +'<td align="center">'+result[kupac].korIme+'</td>'
	                            +'<td align="center">'+result[kupac].adresaKupca+'</td>'
	                            +'<td align="center">'+'JESTE'+'</td>'
	                            +'<td>'
	                            	+'<button type="submit" class="btn btn-dark" style="margin-right: 5%;" onclick="BlokirajKupca('+result[kupac].idKupac+')">BLOK</button>'
	    	                        +'<button type="submit" class="btn btn-warning" style="margin-right: 5%;" onclick="EditKupac('+result[kupac].idKupac+')">IZMENI</button>'
	    	                        +'<button type="submit" class="btn btn-danger" onclick="DeleteKupac('+result[kupac].idKupac+')">OBRIŠI</button>'
	                            +'</td>'
	                        +'</tr>')
                	}else{
                    	tbodyKupac.append(
                            '<tr>'
                        			
                                +'<td align="center">'+result[kupac].idKupac+'</td>'
                                +'<td align="center">'+result[kupac].ime+'</td>'
                                +'<td align="center">'+result[kupac].prezime+'</a>'+'</td>'
                                +'<td align="center">'+result[kupac].korIme+'</td>'
                                +'<td align="center">'+result[kupac].adresaKupca+'</td>'
                                +'<td align="center">'+'NIJE'+'</td>'
                                +'<td>'
                                	+'<button type="submit" class="btn btn-dark" style="margin-right: 5%;" onclick="BlokirajKupca('+result[kupac].idKupac+')">BLOK</button>'
        	                        +'<button type="submit" class="btn btn-warning" style="margin-right: 5%;" onclick="EditKupac('+result[kupac].idKupac+')">IZMENI</button>'
        	                        +'<button type="submit" class="btn btn-danger" onclick="DeleteKupac('+result[kupac].idKupac+')">OBRIŠI</button>'
                                +'</td>'
                            +'</tr>')
                	}};        
            },
            error :function(e){
                alert('Doslo je do neke greške!');
            }
        });
    }
    prikaziKupce();
}

function SubmitKupac(){

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
                PrikazSvihKupaca();
            },
            error : function(e){
                alert('Doslo je do neke greške!');
                console.log("ERROR: ", e);
            }
        });
    }

}

function EditKupac(id){

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
                ime.val(result.ime);
                prezime.val(result.prezime);
                korisnickoIme.val(result.korIme);
                lozinka.val(result.lozinka);
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

function SubmitUpdateKupac(){
    
    var id = $("#idKupac").val();
    var ime = $("#imeKupca").val();
    var prezime = $("#prezimeKupca").val();
    var korisnickoIme = $("#korImeKupca").val();
    var lozinka = $("#lozinkaKupca").val();
    var adresa = $("#adresaKupca").val();
    
    var formData = {
        "ime" : ime,
        "prezime" : prezime,
        "korIme" : korisnickoIme,
        "lozinka" : lozinka,
        "adresaKupca" : adresa
    }

    $.ajax({

        url: "http://localhost:8080/api/kupac/" + id,
        type: "PUT",
        contentType: 'application/json; charset=utf-8',
        data : JSON.stringify(formData),
        success: function(result){
            alert('Kupac je uspesno izmenjen!');
            PrikazSvihKupaca();
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });

}

function DeleteKupac(id){
    $.ajax({
        url:'http://localhost:8080/api/korisnik/' + id,
        type: 'DELETE',
        contentType: 'application/json; charset=utf-8',
        success: function(result){
            alert("Kupac je obrisan!");
            PrikazSvihKupaca();
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });
}

function BlokirajKupca(id){
    $.ajax({
        url:'http://localhost:8080/api/korisnik/' + id + '/blokiranje',
        type: 'POST',
        contentType: 'application/json; charset=utf-8',
        success: function(result){
        	if(result.blokiran){
        		alert("Kupac je blokiran!");
        	}else{
        		alert("Kupac je odblokiran!");
        	}
        	PrikazSvihKupaca();
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });
}

function VratiNaPocetnuKupac(){
	$('#kupciTable').hide();
	$('#prodavciTablee').hide();
	$('#DugmeOdjava').show();
	$('#DugmePrikazLogiina').show();
	$('#DugmePrikazArtikala').show();
	$('#DugmePrikazKorisnike').show();
	$('#DugmeArtikal').show();
	$('#pretragaArtikla').show();
	$('#pretragaArtiklaDole').show();
	$('#DugmePrikazKomentare').show();
	$('#DugmePrikazPretrageArtikla').show();
	$('#DugmePrikazPretragePorudzbine').show();
}

function DodavanjeKupca(){
	$("#kupciTable").hide();
	$("#izmeniKupca").hide();
	$("#btnDodajKupca").show();
	$("#dodajKupca").show();
}

function VratiSaDodavanjaIizmeneKupac(){
	$('#kupciTable').show();
	$('#dodajKupca').hide();
	$('#dodavanjeKupca').show();
	$('#pretragaArtikla').show();
	$('#pretragaArtiklaDole').show();
}

// IZMENA SAMOG SEBE -----------------------------------------------------------------------------------------------------

function PrikazKupca(){

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
                ime.val(result.ime);
                prezime.val(result.prezime);
                korisnickoIme.val(result.korIme);
                lozinka.val(result.lozinka);
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
        "ime" : ime,
        "prezime" : prezime,
        "korIme" : korisnickoIme,
        "lozinka" : lozinka,
        "adresaKupca" : adresa
    }

    $.ajax({

        url: "http://localhost:8080/api/kupac/" + id,
        type: "PUT",
        contentType: 'application/json; charset=utf-8',
        data : JSON.stringify(formData),
        success: function(result){
            alert('Kupac je uspesno izmenjen!');
            window.location.href = "/kupac.html";
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