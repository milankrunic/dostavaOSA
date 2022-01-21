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