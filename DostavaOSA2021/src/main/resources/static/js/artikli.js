function PrikazSvihArtikala(){ //prikaz svih artikala na pocetnoj strani

    var tabelaArtikla = $("#artikliTable");
    var tbodyArtikla = $("#tbodyArtikla");

    function prikaziArtikle(){
    	$('#dodavanje').show();
    	$('#prijava').hide();
    	$('#btnLogin').hide();
    	$('#DugmePrikazLogiina').hide();


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
                        +'<td>'
                        	+'<button type="submit" class="btn btn-success" style="margin-right: 5%;" onclick="">KOMENTARI</button>'
	                        +'<button type="submit" class="btn btn-warning" style="margin-right: 5%;" onclick="editArtikal('+result[artikal].idArtikla+')">IZMENI</button>'
	                        +'<button type="submit" class="btn btn-danger" onclick="deleteArtikal('+result[artikal].idArtikla+')">OBRIŠI</button>'
                        +'</td>'
                    +'</tr>'
                    
                    )};
                    
            },
            error :function(e){
                alert('Doslo je do neke greške!');
            }


        });
    }
    prikaziArtikle();

}

function submitArtikal(){ //dodavanje artikala nalazi se u index.html kod forme za dodavanje

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
                odrediPrikaz('sviArtikli');
            },
            error : function(e){
                alert('Doslo je do neke greške!');
                console.log("ERROR: ", e);
            }
        });
    }

}

function editArtikal(id){ //samo prikaz stranice kod izmene (editArtikal je u PrikazSvihArtikala gore)

	$('#artikliTable').hide();
	$('#dodajArtikal').show();
	$('#izmeniArtikal').show();
	$('#btnDodajArtikal').hide();
	
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

function submitUpdateArtikal(){ //pritiskom na izmenu se dobija ovo, nalazi se u index.html kod forme za dodavanje

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
            odrediPrikaz('sviArtikli');
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });

}

function deleteArtikal(id){ //brisanje
    $.ajax({
        url:'http://localhost:8080/api/artikal/' + id,
        type: 'DELETE',
        contentType: 'application/json; charset=utf-8',
        success: function(result){
            alert("Artikal je obrisan!");
            odrediPrikaz('sviArtikli');
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

function vratiNaPocetnu(){
	$('#artikliTable').hide();
	$('#prijava').hide();
	$('#DugmePrikazLogiina').show();
	$('#DugmePrikazArtikala').show();
	$('#DugmePrikazKorisnike').show();
}

function vratiSaDodavanjaIizmene(){
	$('#artikliTable').show();
	$('#dodajArtikal').hide();
	$('#dodavanje').show();
}