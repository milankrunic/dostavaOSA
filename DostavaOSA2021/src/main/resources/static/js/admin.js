function PrikazSvihAdministratora(){

    var tabelaAdmin = $("#adminTable");
    var tbodyAdmin = $("#tbodyAdmin");

    function prikaziAdministratore(){
    	$('#prikazDugicaSvihKorisnika').hide();
        $.ajax({

            type: "GET",
            contentType : 'application/json; charset=utf-8',
            url : "http://localhost:8080/api/admin",
            success : function(result){
            	tabelaAdmin.show();
            	tbodyAdmin.empty();
                for(admin in result){
                	tbodyAdmin.append(
                    '<tr>'
                			
                        +'<td align="center">'+result[admin].idAdmin+'</td>'
                        +'<td align="center">'+result[admin].imeAdmina+'</td>'
                        +'<td align="center">'+result[admin].prezimeAdmina+'</a>'+'</td>'
                        +'<td align="center">'+result[admin].korImeAdmina+'</td>'
                        +'<td align="center">'+result[admin].blokiran+'</td>'
                        
                        +'<td>'
                        	+'<button type="submit" class="btn btn-dark" style="margin-right: 5%;" onclick="blokiraj('+result[admin].idAdmin+')">BLOK</button>'
	                        +'<button type="submit" class="btn btn-warning" style="margin-right: 5%;" onclick="editAdmin('+result[admin].idAdmin+')">IZMENI</button>'
	                        +'<button type="submit" class="btn btn-danger" onclick="deleteAdmin('+result[admin].idAdmin+')">OBRIŠI</button>'
                        +'</td>'
                    +'</tr>'
                    
                    )};
                    
            },
            error :function(e){
                alert('Doslo je do neke greške!');
            }


        });
    }
    prikaziAdministratore();

}

function submitAdmin(){
	
    var greska = "";
    var imeInput = "";
    var prezimeInput = "";
    var korisnickoImeInput = "";
    var lozinkaInput = "";
    
    imeInput = $("#imeAdmina").val();
    prezimeInput = $("#prezimeAdmina").val();
    korisnickoImeInput = $("#korImeAdmina").val();
    lozinkaInput = $("#lozinkaAdmina").val();

    var imeGreska;
    var prezimeGreska;
    var korImeGreska;
    var lozinkaGreska;
    
    if(imeInput === ""){
    	imeGreska = true;
        greska += "\nMorate uneti ime administratora!";
    }
    if(prezimeInput === ""){
    	prezimeGreska = true;
        greska += "\nMorate uneti prezime administratora!";
    }
    if(korisnickoImeInput === ""){
    	korImeGreska = true;
        greska += "\nMorate uneti korisnicko ime administratora!";
    }
    if(lozinkaInput === ""){
    	lozinkaGreska = true;
        greska += "\nMorate uneti lozinku administratora!";
    }

    if(imeGreska || prezimeGreska || korImeGreska || lozinkaGreska){
        alert(greska);
    }
    else{
        var formData = {
            "imeAdmina" : imeInput,
            "prezimeAdmina" : prezimeInput,
            "korImeAdmina" : korisnickoImeInput,
            "lozinkaAdmina" : lozinkaInput,
        }

        $.ajax({
            url : "http://localhost:8080/api/admin",
            type : "POST",
            contentType: 'application/json; charset=utf-8',
            data : JSON.stringify(formData),
            success: function(){
                alert('Administrator je uspesno dodat!');
                odrediPrikaz('sviAdministratori');
            },
            error : function(e){
                alert('Doslo je do neke greške!');
                console.log("ERROR: ", e);
            }
        });
    }

}

function editAdmin(id){

	$('#adminTable').hide();
	$('#dodajAdmina').show();
	$('#izmeniAdmina').show();
	$('#btnDodajAdmina').hide();
	
    function prikaziAdmina(){
        $.ajax({
            type: "GET",
            contentType : 'application/json; charset=utf-8',
            url : "http://localhost:8080/api/admin/" + id,

            success: function(result){
            	
                var idAdmin = $("#idAdmin");
                var ime = $("#imeAdmina");
                var prezime = $("#prezimeAdmina");
                var korisnickoIme = $("#korImeAdmina");
                var lozinka = $("#lozinkaAdmina");
                               
                idAdmin.val(result.idAdmin);
                ime.val(result.imeAdmina);
                prezime.val(result.prezimeAdmina);
                korisnickoIme.val(result.korImeAdmina);
                lozinka.val(result.lozinkaAdmina);

            },
            error : function(e){
                alert('Doslo je do neke greške!')
                console.log("ERROR: ", e);
            }
        });
    }
    prikaziAdmina();

}

function submitUpdateAdmin(){
    
    var id = $("#idAdmin").val();
    var ime = $("#imeAdmina").val();
    var prezime = $("#prezimeAdmina").val();
    var korisnickoIme = $("#korImeAdmina").val();
    var lozinka = $("#lozinkaAdmina").val();
    
    var formData = {
        "imeAdmina" : ime,
        "prezimeAdmina" : prezime,
        "korImeAdmina" : korisnickoIme,
        "lozinkaAdmina" : lozinka
    }

    $.ajax({
    
    	url: "http://localhost:8080/api/admin/" + id,
        type: "PUT",
        contentType: 'application/json; charset=utf-8',
        data : JSON.stringify(formData),
        success: function(result){
            alert('Administrator ' + result.korImeAdmina + ' je uspesno izmenjen!');
            odrediPrikaz('sviAdministratori');
        },
        error : function(e){
            alert('Doslo je do neke greške!');
            console.log("ERROR: ", e);
        }
    });

}

function deleteAdmin(id){
    $.ajax({
        url:'http://localhost:8080/api/admin/' + id,
        type: 'DELETE',
        contentType: 'application/json; charset=utf-8',
        success: function(result){
            alert("Administrator je obrisan!");
            odrediPrikaz('sviAdministratori');
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });
}

function blokiraj(id){
    $.ajax({
        url:'http://localhost:8080/api/admin/' + id + '/blokiranje',
        type: 'POST',
        contentType: 'application/json; charset=utf-8',
        success: function(result){
        	if(result.blokiran){
        		alert("Administrator je blokiran!");
        	}else{
        		alert("Administrator je odblokiran!");
        	}
            odrediPrikaz('sviAdministratori');
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });
}

function odjava(){
	window.location.href = "index.html";
}

function vratiNaPocetnuAdmin(){
	$('#adminTable').hide();
	$('#DugmeOdjava').show();
	$('#DugmePrikazArtikala').show();
	$('#DugmePrikazKorisnike').show();
}

function vratiSaDodavanjaIizmeneAdmin(){
	$('#adminTable').show();
	$('#dodajAdmina').hide();
}