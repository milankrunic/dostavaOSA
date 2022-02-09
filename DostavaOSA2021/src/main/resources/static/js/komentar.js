function PrikazSvihKomentara(){

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
                    +'<td align="center">'+result[komentar].prihvacen+'</td>'
                    
    				+'<td align="center">' 
    			        +'<form method="post" action="/api/komentar/odobrenje">'
	    			        +'<input type="hidden" name="id" value="'+result[komentar].idKomentar+'"/>'
	    			        +'<input class="btn btn-success" type="submit" value="DOZVOLI">'
    			        +'</form>'
    			    +'</td>'

    			    +'<td>'
    			        +'<form method="post" action="/api/komentar/zabrani">'
	    			        +'<input type="hidden" name="id" value="'+result[komentar].idKomentar+'"/>'
	    			        +'<input class="btn btn-danger" type="submit" value="ZABRANI">'
    			        +'</form>'
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
                	if(result[komentar].prihvacen === true)
                	tbodyKomentar.append(
                    '<tr>'
        			
	                    +'<td align="center">'+result[komentar].idKomentar+'</td>'
	                    +'<td align="center">'+result[komentar].tekst+'</td>'
	                    +'<td align="center">'+result[komentar].artikal+'</a>'+'</td>'
	                    +'<td align="center">'+result[komentar].kupac+'</td>'
	                    +'<td align="center">'+result[komentar].ocena+'</td>'
	                    +'<td>'

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

function ZatvoriKomentareKodProdavca(){
	$('#komentariTableProdavac').hide();
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
    artikalInput = $("#artikal").val();
    kupacInput = $("#kupac").val();
    
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
            "idArtikla" : artikalInput,
            "idKupac" : kupacInput,

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

function PrikazSvihKomentaraArtiklaKodPordavca(id){

    var tabelaKomentar = $("#komentariTableProdavac").slideDown();;
    var tbodyKomentar = $("#tbodyKomentareProdavac");
    
    function prikaziKomentareArtikla(){

        $.ajax({

            type: "GET",
            contentType : 'application/json; charset=utf-8',
            url : "http://localhost:8080/api/artikal/" + id + "/komentari",
            success : function(result){
            	tabelaKomentar.show();
            	tbodyKomentar.empty();
                for(komentar in result){
                	if(result[komentar].arhiviran === false)   //AKO HOCEMO DA PRODAVAC SVIMA ZABRANI KOMENTAR STAVIMO PRIHVACEN UMESTO ARHIVIRAN
                	tbodyKomentar.append(
                    '<tr>'
        			
	                    +'<td align="center">'+result[komentar].idKomentar+'</td>'
	                    +'<td align="center">'+result[komentar].tekst+'</td>'
	                    +'<td align="center">'+result[komentar].artikal+'</a>'+'</td>'
	                    +'<td align="center">'+result[komentar].kupac+'</td>'
	                    +'<td align="center">'+result[komentar].ocena+'</td>'
	                    +'<td align="center">'+result[komentar].arhiviran+'</td>'
	                    +'<td>'
	                    
	    			        +'<form method="post" action="/api/komentar/arhivirajProdavac">'
		    			        +'<input type="hidden" name="id" value="'+result[komentar].idKomentar+'"/>'
		    			        +'<input class="btn btn-danger" type="submit" value="ARHIVIRAJ">'
	    			        +'</form>'

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