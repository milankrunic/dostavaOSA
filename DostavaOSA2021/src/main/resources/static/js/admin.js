function PrikazSvihAdministratora(){

    var tabelaAdmin = $("#adminTable");
    var tbodyAdmin = $("#tbodyAdmin");

    function prikaziAdministratore(){
    	$('#prikazDugicaSvihKorisnika').hide();
    	$('#pretragaArtikla').hide();
    	$('#dodajAdmina').hide();
    	$('#pretragaArtiklaDole').hide();
    	$('#DugmePrikazPretragePorudzbine').hide();
        $.ajax({

            type: "GET",
            contentType : 'application/json; charset=utf-8',
            url : "http://localhost:8080/api/admin",
            success : function(result){
            	tabelaAdmin.show();
            	tbodyAdmin.empty();
                for(admin in result){
                	if(result[admin].blokiran){
                    	tbodyAdmin.append(
                            '<tr>'
                        			
                                +'<td align="center">'+result[admin].idAdmin+'</td>'
                                +'<td align="center">'+result[admin].ime+'</td>'
                                +'<td align="center">'+result[admin].prezime+'</a>'+'</td>'
                                +'<td align="center">'+result[admin].korIme+'</td>'
                                +'<td align="center">'+'JESTE'+'</td>'
                                
                                +'<td>'
                                	+'<button type="submit" class="btn btn-dark" style="margin-right: 5%;" onclick="Blokiraj('+result[admin].idAdmin+')">BLOK</button>'
        	                        +'<button type="submit" class="btn btn-warning" style="margin-right: 5%;" onclick="EditAdmin('+result[admin].idAdmin+')">IZMENI</button>'
        	                        +'<button type="submit" class="btn btn-danger" onclick="DeleteAdmin('+result[admin].idAdmin+')">OBRIŠI</button>'
                                +'</td>'
                            +'</tr>'
                            
                            )
                	}else{
                    	tbodyAdmin.append(
                            '<tr>'
                        			
                                +'<td align="center">'+result[admin].idAdmin+'</td>'
                                +'<td align="center">'+result[admin].ime+'</td>'
                                +'<td align="center">'+result[admin].prezime+'</a>'+'</td>'
                                +'<td align="center">'+result[admin].korIme+'</td>'
                                +'<td align="center">'+'NIJE'+'</td>'
                                
                                +'<td>'
                                	+'<button type="submit" class="btn btn-dark" style="margin-right: 5%;" onclick="Blokiraj('+result[admin].idAdmin+')">BLOK</button>'
        	                        +'<button type="submit" class="btn btn-warning" style="margin-right: 5%;" onclick="EditAdmin('+result[admin].idAdmin+')">IZMENI</button>'
        	                        +'<button type="submit" class="btn btn-danger" onclick="DeleteAdmin('+result[admin].idAdmin+')">OBRIŠI</button>'
                                +'</td>'
                            +'</tr>')
                	}};
                    
            },
            error :function(e){
                alert('Doslo je do neke greške!');
            }


        });
    }
    prikaziAdministratore();
}

function SubmitAdmin(){
	
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
            "ime" : imeInput,
            "prezime" : prezimeInput,
            "korIme" : korisnickoImeInput,
            "lozinka" : lozinkaInput,
        }

        $.ajax({
            url : "http://localhost:8080/api/admin",
            type : "POST",
            contentType: 'application/json; charset=utf-8',
            data : JSON.stringify(formData),
            success: function(){
                alert('Administrator je uspesno dodat!');
                PrikazSvihAdministratora();
            },
            error : function(e){
                alert('Doslo je do neke greške!');
                console.log("ERROR: ", e);
            }
        });
    }
}

function EditAdmin(id){

	$('#adminTable').hide();
	$('#dodajAdmina').show();
	$('#izmeniAdmina').show();
	$('#btnDodajAdmina').hide();
	$('#pretragaArtikla').hide();
	$('#pretragaArtiklaDole').hide();
	
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
                ime.val(result.ime);
                prezime.val(result.prezime);
                korisnickoIme.val(result.korIme);
                lozinka.val(result.lozinka);

            },
            error : function(e){
                alert('Doslo je do neke greške!')
                console.log("ERROR: ", e);
            }
        });
    }
    prikaziAdmina();

}

function SubmitUpdateAdmin(){
    
    var id = $("#idAdmin").val();
    var ime = $("#imeAdmina").val();
    var prezime = $("#prezimeAdmina").val();
    var korisnickoIme = $("#korImeAdmina").val();
    var lozinka = $("#lozinkaAdmina").val();
    
    var formData = {
        "ime" : ime,
        "prezime" : prezime,
        "korIme" : korisnickoIme,
        "lozinka" : lozinka
    }

    $.ajax({
    
    	url: "http://localhost:8080/api/admin/" + id,
        type: "PUT",
        contentType: 'application/json; charset=utf-8',
        data : JSON.stringify(formData),
        success: function(result){
            alert('Administrator ' + result.korImeAdmina + ' je uspesno izmenjen!');
            PrikazSvihAdministratora();
        },
        error : function(e){
            alert('Doslo je do neke greške!');
            console.log("ERROR: ", e);
        }
    });

}

function DeleteAdmin(id){
    $.ajax({
        url:'http://localhost:8080/api/korisnik/' + id,
        type: 'DELETE',
        contentType: 'application/json; charset=utf-8',
        success: function(result){
            alert("Administrator je obrisan!");
            PrikazSvihAdministratora();
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });
}

function Blokiraj(id){
    $.ajax({
        url:'http://localhost:8080/api/korisnik/' + id + '/blokiranje',
        type: 'POST',
        contentType: 'application/json; charset=utf-8',
        success: function(result){
        	if(result.blokiran){
        		alert("Administrator je blokiran!");
        	}else{
        		alert("Administrator je odblokiran!");
        	}
        	PrikazSvihAdministratora();
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });
}

function Odjava(){
	window.location.href = "/";
}

function DodavanjeAdmina(){
	$("#adminTable").hide();
	$("#izmeniAdmina").hide();
	$("#btnDodajAdmina").show();
	$("#dodajAdmina").show();
}

function VratiNaPocetnuAdmin(){
	$('#adminTable').hide();
	$('#komentariTable').hide();
	$('#DugmeOdjava').show();
	$('#DugmePrikazArtikala').show();
	$('#DugmePrikazKorisnike').show();
	$('#DugmePrikazKomentare').show();
	$('#DugmePrikazPretrageArtikla').show();
	$('#DugmePrikazPretragePorudzbine').show();
}

function VratiSaDodavanjaIizmeneAdmin(){
	$('#adminTable').show();
	$('#dodajAdmina').hide();
}