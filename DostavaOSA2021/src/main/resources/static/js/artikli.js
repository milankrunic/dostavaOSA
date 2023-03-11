function PrikazSvihArtikala(){ 

    var tabelaArtikla = $("#artikliTable");
    var tbodyArtikla = $("#tbodyArtikla");

    function prikaziArtikle(){
    	$('#prijava').hide();
    	$('#btnLogin').hide();
    	$('#DugmePrikazLogiina').hide();
    	$('#pretragaArtikla').hide();
    	$('#pretragaArtiklaDole').hide();

        $.ajax({

            type: "GET",
            contentType : 'application/json; charset=utf-8',
            url : "http://localhost:8080/api/artikal",
            success : function(result){
                tabelaArtikla.show();
                tbodyArtikla.empty();
                for(artikal in result){
                    tbodyArtikla.append(
                    '<tr>'
                			
                        +'<td align="center">'+result[artikal].idArtikla+'</td>'
                        +'<td align="center">'+result[artikal].naziv+'</td>'
                        +'<td align="center">'+result[artikal].opis+'</a>'+'</td>'
                        +'<td align="center">'+result[artikal].cena+'</td>'
                        +'<td align="center">'+result[artikal].prodavac+'</td>'
                        +'<td align="center"><a href="http://localhost:8080/api/artikal/downloadFile/'+result[artikal].nazivFajla+'">'+result[artikal].nazivFajla+'</a></td>'
                        +'<td>'
                        	+'<button type="submit" class="btn btn-success" style="margin-right: 5%;" onclick="PrikazSvihKomentaraArtikla('+result[artikal].idArtikla+')">KOMENTARI</button>'
	                        +'<button type="submit" class="btn btn-warning" style="margin-right: 5%;" onclick="editArtikal('+result[artikal].idArtikla+')">IZMENI</button>'
	                        +'<button type="submit" class="btn btn-danger" onclick="deleteArtikal('+result[artikal].idArtikla+')">OBRIŠI</button>'
                        +'</td>'
                    +'</tr>'
                    
                    )};               
            },
            error :function(e){
            	
            }
        });
    }
    prikaziArtikle();
}

function DodavanjeArtikla(){
	$('#artikliTableProdavac').hide();
    $('#artikliTable').hide();
    $('#dodajArtikal').show();
    dajProdavce();
}

function SubmitArtikal(){

    var greska = "";
    var nazivInput = "";
    var opisInput = "";
    var cenaInput = "";
    var prodavacInput = "";
    var opisFileInput = "";

    nazivInput = $("#nazivArtikla").val();
    opisInput = $("#opis").val();
    cenaInput = $("#cena").val();
    prodavacInput = $("#inputProdavac").val();
    opisFileInput = $("#opisFile").val();

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
    }
    else{
        var formData = new FormData();    
        formData.append( 'naziv', nazivInput );
        formData.append( 'opis', opisInput );
        formData.append( 'cena', cenaInput );
        formData.append( 'file', $('#opisFile')[0].files[0] );
        formData.append( 'idProdavac', prodavacInput );

        $.ajax({
        	url : "http://localhost:8080/api/artikal",
            type : "POST",
            data : formData,
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false, 
            success: function(data){
                alert('Artikal je uspesno dodat!');
                PrikazSvihArtikala();
                $('#dodajArtikal').hide();
            },
            error : function(e){
                alert('Doslo je do neke greške!');
                console.log("ERROR: ", e);
            }
        });
    }
}

function editArtikal(id){

	$('#artikliTable').hide();
	$('#dodajArtikal').show();
	$('#izmeniArtikal').show();
	$('#btnDodajArtikal').hide();
	$('#pretragaArtikla').hide();
	$('#pretragaArtiklaDole').hide();
	
    function prikaziArtikal(){
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
    prikaziArtikal();
}

function SubmitUpdateArtikal(){

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
            PrikazSvihArtikala();
            $('#dodajArtikal').hide();
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });

}

function deleteArtikal(id){ 
    $.ajax({
        url:'http://localhost:8080/api/artikal/' + id,
        type: 'DELETE',
        contentType: 'application/json; charset=utf-8',
        success: function(result){
            alert("Artikal je obrisan!");
            PrikazSvihArtikala();
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });
}

function dajProdavce(id){
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

function VratiNaPocetnu(){
	$('#artikliTableProdavac').hide();
	$('#artikliTable').hide();
	$('#prijava').hide();
	$('#DugmeOdjava').show();
	$('#DugmePrikazLogiina').show();
	$('#DugmePrikazArtikala').show();
	$('#DugmePrikazKorisnike').show();
	$('#pretragaArtikla').show();
	$('#pretragaArtiklaDole').show();
	$('#DugmePrikazKomentare').show();
	$('#DugmePrikazPretrageArtikla').show();
	$('#DugmePrikazPretragePorudzbine').show();
}

function VratiSaDodavanjaIizmene(){
	$('#artikliTable').show();
	$('#dodajArtikal').hide();
	$('#dodavanje').show();
}