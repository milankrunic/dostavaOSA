var prikaziArtikle = false;
var dodajArtikle = false;

function pritisni(){
    $('#prijava').show();
    alert('Dobro si pritiso')
    pritisni();
}

function odrediPrikaz(id){
    prikaziArtikle = false;

    if(id === "sviArtikli"){
        prikaziArtikle = true;
    }

    prikazi();
}

function prikazi(){
    
    var artikliTable = $("#artikliTable");

    artikliTable.hide();

    if(prikaziArtikle){   
        PrikazSvihArtikala();
    }
}