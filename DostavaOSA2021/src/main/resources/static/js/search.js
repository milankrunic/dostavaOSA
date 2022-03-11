$(document).ready(function () {
    
    $("#btnSubmitLuceneQueryLanguage").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        searchLuceneQueryLanguage();

    });

});

function searchLuceneQueryLanguage() {

    var value = $('#luceneQueryLanguage input[name=value]').val();
    var data = JSON.stringify({"value":value});
    $("#btnSubmitLuceneQueryLanguage").prop("disabled", true);

    $.ajax({
        type: "POST",
        url: "api/search/queryParser",
        data: data,
        contentType: 'application/json',
        success: function (data) {
        	$('#result').empty();
            for(index = 0; index < data.length; index++){
                var result = data[index]
                $.each(result, function(key, value) {
                  $('#result').append('<li>' + key + ': ' + value + '</li>');
                });
            }
            console.log("SUCCESS : ", data);
            $("#btnSubmitLuceneQueryLanguage").prop("disabled", false);

        },
        error: function (e) {
        	$('#result').empty();
            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnSubmitLuceneQueryLanguage").prop("disabled", false);

        }
    });

}