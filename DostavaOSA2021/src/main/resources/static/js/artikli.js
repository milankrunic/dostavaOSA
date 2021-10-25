function PrikazSvihArtikala(){

    var tabelaArtikla = $("#artikliTable");
    var tbodyArtikla = $("#tbodyArtikla");

    function prikaziArtikle(){
    	$('#prijava').hide();
    	$('#DugmePrikazArtikala').hide();
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
                			
                        +'<td align="center">'+result[artikal].naziv+'</td>'
                        +'<td align="center">'+result[artikal].opis+'</a>'+'</td>'
                        +'<td align="center">'+result[artikal].cena+'</td>'
                        +'<td align="center">'+result[artikal].prodavac+'</td>'
                        +'<td>'
	                        +'<button type="submit" class="btn btn-warning" style="margin-right: 5%;" onclick="editMagacin('+result[artikal].id+')">IZMENI</button>'
	                        +'<button type="submit" class="btn btn-danger" onclick="deleteMagacin('+result[artikal].id+')">OBRIŠI</button>'
                        +'</td>'
                        
                    +'</tr>'
                    )};
                    
            },
            error :function(e){
                alert('ne valja nesto');
            }


        });
    }
    prikaziArtikle();

}