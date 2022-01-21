function PrikazSvihKomentara(){ //prikaz svih komentara kod admina

    var tabelaKomentar = $("#komentariTable");
    var tbodyKomentar = $("#tbodyKomentar");

    function prikaziKomentare(){

        $.ajax({

            type: "GET",
            contentType : 'application/json; charset=utf-8',
            url : "http://localhost:8080/api/komentar",
            success : function(result){
            	tabelaKomentar.show();
            	tbodyKomentar.empty();
                for(komentar in result){
                	tbodyKomentar.append(
                    '<tr>'
                			
                        +'<td align="center">'+result[komentar].idKomentar+'</td>'
                        +'<td align="center">'+result[komentar].tekst+'</td>'
                        +'<td align="center">'+result[komentar].artikal+'</a>'+'</td>'
                        +'<td align="center">'+result[komentar].kupac+'</td>'
                        +'<td align="center">'+result[komentar].ocena+'</td>'
                        +'<td>'
	                        +'<button type="submit" class="btn btn-success" style="margin-right: 5%;" onclick="">DOZVOLI</button>'
	                        +'<button type="submit" class="btn btn-danger" onclick="">ZABRANI</button>'
                        +'</td>'
                    +'</tr>'
                    
                    )};
                    
            },
            error :function(e){
                alert('Doslo je do neke greške!');
            }


        });
    }
    prikaziKomentare();

}

function PrikazSvihKomentaraArtikla(id){

    var tabelaKomentar = $("#komentariTablee").slideDown();;
    var tbodyKomentar = $("#tbodyKomentare");
    
    function prikaziKomentareArtikla(){

        $.ajax({

            type: "GET",
            contentType : 'application/json; charset=utf-8',
            url : "http://localhost:8080/api/artikal/" + id + "/komentari",
            success : function(result){
            	tabelaKomentar.show();
            	tbodyKomentar.empty();
                for(komentar in result){
                	tbodyKomentar.append(
                    '<tr>'
        			
	                    +'<td align="center">'+result[komentar].idKomentar+'</td>'
	                    +'<td align="center">'+result[komentar].tekst+'</td>'
	                    +'<td align="center">'+result[komentar].artikal+'</a>'+'</td>'
	                    +'<td align="center">'+result[komentar].kupac+'</td>'
	                    +'<td align="center">'+result[komentar].ocena+'</td>'
	                    +'<td>'
//	                        +'<button type="submit" class="btn btn-success" style="margin-right: 5%;" onclick="">DOZVOLI</button>'
//	                        +'<button type="submit" class="btn btn-danger" onclick="">ZABRANI</button>'
	                    +'</td>'
                    +'</tr>'
                  
                    )};
                    
            },
            error :function(e){
                alert('Doslo je do neke greške!');
            }

        });
    }
    prikaziKomentareArtikla();
    
}

function ZatvoriKomentare(){
	$('#komentariTablee').hide();
}

function prikaziFormuZaDodavanjeKomentara(){
	var dodajKomentar = $("#dodajKomentar").slideDown();
	dodajKomentar.show();
}

function DodajKomentar(){
	
    var greska = "";
    var tekstInput = "";
    var ocenaInput = "";
    
    tekstInput = $("#tekst").val();
    ocenaInput = $("#ocena").val();
//    kupacInput = $("#kupac").val();
//    artikalInput = $("#artikal").val();
//    artikalNazivInput = $("#artikalNaziv").val();
    
    var tekstGreska;
    var ocenaGreska;
    
    if(tekstInput === ""){
    	tekstGreska = true;
        greska += "\nMorate uneti tekst komentara!";
    }
    if(ocenaInput <= 0 || ocenaInput > 5){
    	ocenaGreska = true;
        greska += "\nOcena mora biti od 1 do 5!";
    }

    if(tekstGreska || ocenaGreska){
        alert(greska);
    }
    else{
        var formData = {
            "tekst" : tekstInput,
            "ocena" : ocenaInput,
//            "kupac" : kupacInput,
//            "idArtikla" : artikalInput,
//            "artikal" : artikalNazivInput,
        }

        $.ajax({
            url : "http://localhost:8080/api/komentar",
            type : "POST",
            contentType: 'application/json; charset=utf-8',
            data : JSON.stringify(formData),
            success: function(){
                alert('Komentar je uspesno poslat, molimo sacekajte da se odobri!');
                PrikazSvihKomentara();
            },
            error : function(e){
                alert('Doslo je do neke greške!');
                console.log("ERROR: ", e);
            }
        });
    }

}