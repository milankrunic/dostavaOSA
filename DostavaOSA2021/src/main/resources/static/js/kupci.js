function PrikazSvihKupaca(){

    var tabelaKupca = $("#kupciTable");
    var tbodyKupac = $("#tbodyKupac");

    function prikaziKupce(){
    	$('#dodavanje').hide();
    	$('#prijava').hide();
    	$('#btnLogin').hide();
    	$('#prikazDugicaSvihKorisnika').hide();
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
	$('#DugmePrikazLogiina').show();
	$('#DugmePrikazArtikala').show();
	$('#DugmePrikazKorisnike').show();
}

function vratiSaDodavanjaIizmeneKupac(){
	$('#kupciTable').show();
	$('#dodajKupca').hide();
	$('#dodavanjeKupca').show();
}