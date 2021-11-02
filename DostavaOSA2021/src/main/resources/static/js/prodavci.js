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
                        +'<td>'
	                        +'<button type="submit" class="btn btn-warning" style="margin-right: 5%;" onclick="editProdavac('+result[prodavac].idProdavac+')">IZMENI</button>'
	                        +'<button type="submit" class="btn btn-danger" onclick="deleteProdavac('+result[prodavac].idProdavac+')">OBRIŠI</button>'
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

function vratiNaPocetnuProdavci(){
	$('#prodavciTable').hide();
	$('#prijava').hide();
	$('#DugmePrikazLogiina').show();
	$('#DugmePrikazArtikala').show();
	$('#DugmePrikazKorisnike').show();
}

function vratiSaDodavanjaIizmeneProdavci(){
	$('#prodavciTable').show();
	$('#dodajProdavca').hide();
	$('#dodavanjeProdavca').show();
}