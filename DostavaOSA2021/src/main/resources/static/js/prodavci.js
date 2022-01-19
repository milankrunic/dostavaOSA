function PrikazSvihProdavaca(){

    var tabelaProdavaca = $("#prodavciTable");
    var tbodyProdavac = $("#tbodyProdavac");

    function prikaziProdavce(){
    	$('#dodavanje').hide();
    	$('#prijava').hide();
    	$('#DugmePrikazArtikala').hide();
    	$('#btnLogin').hide();
    	$('#prikazDugicaSvihKorisnika').hide();
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
                        +'<td align="center">'+result[prodavac].korIme+'</td>'
                        +'<td align="center">'+result[prodavac].nazivProdavca+'</td>'
                        +'<td align="center">'+result[prodavac].email+'</td>'
                        +'<td align="center">'+result[prodavac].adresa+'</td>'
                        +'<td align="center">'+result[prodavac].poslujeOd+'</td>'
                        +'<td align="center">'+result[prodavac].blokiran+'</td>'
                        +'<td>'
                        	+'<button type="submit" class="btn btn-success" style="margin-right: 5%;" onclick="PrikazSvihArtikalaProdavaca('+result[prodavac].idProdavac+')">ARTIKLI</button>'
	                        +'<button type="submit" class="btn btn-warning" style="margin-right: 5%;" onclick="editProdavac('+result[prodavac].idProdavac+')">IZMENI</button>'
	                        +'<button type="submit" class="btn btn-danger" onclick="deleteProdavac('+result[prodavac].idProdavac+')">OBRIŠI</button>'
	                        +'<button type="submit" class="btn btn-dark" style="margin-right: 5%;" onclick="blokirajProdavca('+result[prodavac].idProdavac+')">BLOK</button>'
                        +'</td>'
                    +'</tr>'
                    
                    )};
                    
            },
            error :function(e){
                alert('Doslo je do neke greške!');
            }


        });
    }
    prikaziProdavce();

}

function submitProdavac(){

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
                odrediPrikaz('sviProdavci');
            },
            error : function(e){
                alert('Doslo je do neke greške!');
                console.log("ERROR: ", e);
            }
        });
    }

}

function editProdavac(id){

	$('#prodavciTable').hide();
	$('#dodajProdavca').show();
	$('#izmeniProdavca').show();
	$('#btnDodajProdavca').hide();
	
    function prikaziProdavca(){
        $.ajax({
            type: "GET",
            contentType : 'application/json; charset=utf-8',
            url : "http://localhost:8080/api/prodavac/" + id,

            success: function(result){
            	
                var idProdavac = $("#idProdavac");
                var ime = $("#ime");
                var prezime = $("#prezime");
                var korisnickoIme = $("#korIme");
                var lozinka = $("#lozinka");
                var nazivProdavca = $("#nazivProdavca");
                var email = $("#email");
                var adresa = $("#adresa");
                var poslujeOd = $("#poslujeOd");
                               
                idProdavac.val(result.idProdavac);
                ime.val(result.ime);
                prezime.val(result.prezime);
                korisnickoIme.val(result.korIme);
                lozinka.val(result.lozinka);
                nazivProdavca.val(result.nazivProdavca);
                email.val(result.email);
                adresa.val(result.adresa);
                poslujeOd.val(result.poslujeOd);

            },
            error : function(e){
                alert('Doslo je do neke greške!')
                console.log("ERROR: ", e);
            }
        });
    }
    prikaziProdavca();

}

function submitUpdateProdavac(){
    
    var id = $("#idProdavac").val();
    var ime = $("#ime").val();
    var prezime = $("#prezime").val();
    var korisnickoIme = $("#korIme").val();
    var lozinka = $("#lozinka").val();
    var naziv = $("#nazivProdavca").val();
    var email = $("#email").val();
    var adresa = $("#adresa").val();
    var poslujeOd = $("#poslujeOd").val();

    var formData = {
        "ime" : ime,
        "prezime" : prezime,
        "korIme" : korisnickoIme,
        "lozinka" : lozinka,
        "nazivProdavca" : naziv,
        "email" : email,
        "adresa" : adresa,
        "poslujeOd" : poslujeOd
    }

    $.ajax({

        url: "http://localhost:8080/api/prodavac/" + id,
        type: "PUT",
        contentType: 'application/json; charset=utf-8',
        data : JSON.stringify(formData),
        success: function(result){
            alert('Prodavac ' + result.korIme + ' je uspesno izmenjen!');
            odrediPrikaz('sviProdavci');
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });

}

function deleteProdavac(id){
    $.ajax({
        url:'http://localhost:8080/api/prodavac/' + id,
        type: 'DELETE',
        contentType: 'application/json; charset=utf-8',
        success: function(result){
            alert("Prodavac je obrisan!");
            odrediPrikaz('sviProdavci');
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });
}

function blokirajProdavca(id){
    $.ajax({
        url:'http://localhost:8080/api/prodavac/' + id + '/blokiranje',
        type: 'POST',
        contentType: 'application/json; charset=utf-8',
        success: function(result){
        	if(result.blokiran){
        		alert("Prodavac je blokiran!");
        	}else{
        		alert("Prodavac je odblokiran!");
        	}
            odrediPrikaz('sviProdavci');
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });
}

function vratiNaPocetnuProdavci(){
	$('#prodavciTable').hide();
	$('#prijava').hide();
	$('#DugmeOdjava').show();
	$('#DugmePrikazLogiina').show();
	$('#DugmePrikazArtikala').show();
	$('#DugmePrikazKorisnike').show();
}

function vratiSaDodavanjaIizmeneProdavci(){
	$('#prodavciTable').show();
	$('#dodajProdavca').hide();
	$('#dodavanjeProdavca').show();
}

// ----------------- CRUD OPERACIJE ZA NJEGOVE ARTIKLE --------------------------------------------------------------

function PrikazSvihArtikalaProdavaca(){

    var tabelaArtiklaProdavca = $("#artikliTableProdavac");
    var tbodyArtiklaProdavca = $("#tbodyArtiklaProdavca");
    
    function prikaziArtikleProdavca(){
    	$('#dodavanje').show();
    	$('#prodavciTable').hide();
    	$('#DugmePrikazArtikala').hide();
    	$('#btnLogin').hide();
    	$('#DugmePrikazKorisnike').hide();
    	$('#DugmePrikazLogiina').hide();
    	$('#dodajArtikal').hide();
        $.ajax({

            type: "GET",
            contentType : 'application/json; charset=utf-8',
            url : "http://localhost:8080/api/prodavac/artikli",
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
	                        +'<button type="submit" class="btn btn-warning" style="margin-right: 5%;" onclick="editArtikalProdavac('+result[artikal].idArtikla+')">IZMENI</button>'
	                        +'<button type="submit" class="btn btn-danger" onclick="deleteArtikalProdavac('+result[artikal].idArtikla+')">OBRIŠI</button>'
                        +'</td>'
                    +'</tr>'
                  
                    )};
                    
            },
            error :function(e){
                alert('Doslo je do neke greške!');
            }

        });
    }
    prikaziArtikleProdavca();
    
}

function submitArtikalProdavac(){ //dodavanje artikala nalazi se u index.html kod forme za dodavanje

    var greska = "";
    var nazivInput = "";
    var opisInput = "";
    var cenaInput = "";
    var prodavacInput = "";

    nazivInput = $("#nazivArtikla").val();
    opisInput = $("#opis").val();
    cenaInput = $("#cena").val();
    prodavacInput = $("#inputProdavac").val();

    var nazivGreska;
    var opisGreska;
    var cenaGreska;
    
    if(nazivInput === ""){
    	nazivGreska = true;
        greska += "\nMorate uneti naziv artikla!";
    }
    if(opisInput === ""){
    	opisGreska = true;
        greska += "\nMorate uneti opis artikla!";
    }
    if(cenaInput === ""){
    	cenaGreska = true;
        greska += "\nMorate uneti cenu artikla!";
    }

    if(nazivGreska || opisGreska || cenaGreska){
        alert(greska);
        console.log("do ovde dodje!!!");
    }
    else{
        var formData = {
            "naziv" : nazivInput,
            "opis" : opisInput,
            "cena" : cenaInput,
            "idProdavac" : prodavacInput
        }

        $.ajax({
            url : "http://localhost:8080/api/artikal",
            type : "POST",
            contentType: 'application/json; charset=utf-8',
            data : JSON.stringify(formData),
            success: function(){
                alert('Artikal je uspesno dodat!');
                $('#dodajArtikal').hide();
                PrikazSvihArtikalaProdavaca();
            },
            error : function(e){
                alert('Doslo je do neke greške!');
                console.log("ERROR: ", e);
            }
        });
    }

}

function editArtikalProdavac(id){ 

	$('#artikliTableProdavac').hide();
	$('#dodajArtikal').show();
	$('#izmeniArtikalProdavca').show();
	$('#btnDodajArtikalProdavca').hide();
	$('#btnDodajArtikal').hide();
	
    function prikaziArtikalProdavca(){
        $.ajax({
            type: "GET",
            contentType : 'application/json; charset=utf-8',
            url : "http://localhost:8080/api/artikal/" + id,

            success: function(result){
            	
            	dajProdavce(result.idProdavac);
            	
                var idArtikla = $("#idArtikla");
                var nazivArtikla = $("#nazivArtikla");
                var opis = $("#opis");
                var cena = $("#cena");

                idArtikla.val(result.idArtikla);
                nazivArtikla.val(result.naziv);
                opis.val(result.opis);
                cena.val(result.cena);

            },
            error : function(e){
                alert('Doslo je do neke greške!')
                console.log("ERROR: ", e);
            }
        });
    }
    prikaziArtikalProdavca();

}

function submitUpdateArtikalProdavac(id){ //pritiskom na izmenu se dobija ovo, nalazi se u index.html kod forme za dodavanje

    var id = $("#idArtikla").val();
    var naziv = $("#nazivArtikla").val();
    var opis = $("#opis").val();
    var cena = $("#cena").val();
    var prodavac = $("#inputProdavac").val();

    var formData = {
        "naziv" : naziv,
        "opis" : opis,
        "cena" : cena,
        "idProdavac" : prodavac
    }

    $.ajax({

        url: "http://localhost:8080/api/artikal/" + id,
        type: "PUT",
        contentType: 'application/json; charset=utf-8',
        data : JSON.stringify(formData),
        success: function(result){
            alert('Artikal je uspesno izmenjen!');
            $('#dodajArtikal').hide();
            PrikazSvihArtikalaProdavaca();
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });

}

function deleteArtikalProdavac(id){ //brisanje
    $.ajax({
        url:'http://localhost:8080/api/artikal/' + id,
        type: 'DELETE',
        contentType: 'application/json; charset=utf-8',
        success: function(result){
            alert("Artikal je obrisan!");
            PrikazSvihArtikalaProdavaca();
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });
}

function dajProdavce(id){ //ubacivanje prodavca u listu za izmenu i dodavanje
    var prodavac1 = "";
    var prodavac2 = "";
    var selectProdavce = $("#selectProdavce");
    selectProdavce.empty();
    var html = '<label>Izaberite Prodavnicu:';
    html += '<select class="form-control" id="inputProdavac">';
    $.ajax({
        type: "GET",
        contentType : 'application/json; charset=utf-8',
        url : "http://localhost:8080/api/prodavac",
        success : function(result){
            if(id != undefined){
                result.forEach(prodavac => {
                    if(id == prodavac.idProdavac){
                    	prodavac1 = '<option value="' + prodavac.idProdavac + '">' + prodavac.nazivProdavca + '</option>';
                    }else{
                    	prodavac2 += '<option value="' + prodavac.idProdavac + '">' + prodavac.nazivProdavca + '</option>';
                    }
                });
                html += prodavac1;
                html += prodavac2;
            }
            else{
                result.forEach(prodavac => {
                    html += '<option value="' + prodavac.idProdavac + '">' + prodavac.nazivProdavca + '</option>';
                }); 
            }
            html += '</select>';
            html += '</label>';
            selectProdavce.append(html);
        },
        error :function(e){
            console.log("Greska!!!");
        }
    });
}