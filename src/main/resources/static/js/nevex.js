

$( document ).ready(function() {

    // get the query params
    var urlParams = new URLSearchParams(window.location.search);

    var errorText = "";

    if ( urlParams.has('error')) {
        errorText = "Login failed"
    } else if ( urlParams.has('logout')) {
        errorText = "You have been logged out";
    }

    $('#errorText').text(errorText);

});