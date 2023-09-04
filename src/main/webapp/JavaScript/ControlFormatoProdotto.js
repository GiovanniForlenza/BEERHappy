function controlLetteralName(string) {
    let message = document.getElementById("messageLetter");
    string = string.replace("'", "");
    //let regexp = /^[a-zA-Z0-9]+$/;

    if (string === " " || string.length === 0)
    {
        document.getElementById("name").style.borderColor = "red";
        document.getElementById("messageLetter").style.color = "red";
        message.innerText = "questo campo può essere solo di tipo alphanumerico";
        return false;
    }
    else {
        message.innerText = "";
        document.getElementById("name").style.borderColor = "green";
        return true;
    }
    return false;
}
function controlLetteralBirrificio(string) {
    let message = document.getElementById("messageBirrificio");
    string = string.replace("'", "");
    //let regexp = /\d/;

    if (string === " " || string.length === 0)
    {
        document.getElementById("birrificio").style.borderColor = "red";
        document.getElementById("messageBirrificio").style.color = "red";
        message.innerText = "questo campo può essere solo di tipo letterale";
        return false;
    }
    else {
        document.getElementById("birrificio").style.borderColor = "green";
        message.innerText = "";
        return true;
    }
    return false;
}

function controlFormato(string) {
    //let message = document.getElementById("messageFormato");

    if (string === " " || string.length === 0){
        document.getElementById("formato").style.borderColor = "red";
        return false;
    }
    else{
        document.getElementById("formato").style.border = "green";
        return true;
    }
}

function controlDescrizione(string) {
    let message = document.getElementById("messageDescrizione");

    if (string.length > 0 && string.length <= 1000 && string.localeCompare("")!=0 ) {
        document.getElementById("descrizione").style.borderColor = "green";
        message.innerText = "";
        return true;
    } else {
        document.getElementById("descrizione").style.borderColor = "red";
        document.getElementById("messageDescrizione").style.color = "red";
        message.innerText = "La lunghezza della descrizione non deve superare i 1000 caratteri";
        return false;
    }
    return false;
}

function controlQuantita(string) {
    let regexp = /^[0-9]+$/;

    if(string === " "  || string.length === 0 ) {
        document.getElementById("quantita").style.borderColor = "red";
        document.getElementById("messageQuantita").style.color = "red";
        return false
    }
    else if (!regexp.test(string) && string.localeCompare("")!=0) {
        document.getElementById("quantita").style.borderColor = "red";
        document.getElementById("messageQuantita").style.color = "red";
        document.getElementById("messageQuantita" ).innerText = "Questo è un campo di tipo numerico";
        return false;
    } else {
        document.getElementById("quantita").style.borderColor = "green";
        document.getElementById("messageQuantita" ).innerText = "";
        return true;
    }
    return false;
}

function controlPrice(string) {
    string = string.replace(",", "");
    let regexp = /^\d+(\.\d{1,2})?$/;

    if(regexp.test(string)){
        console.log("Il campo è un prezzo valido");
        document.getElementById("prezzo").style.borderColor = "green";
        document.getElementById("messagePrice").innerText = "";
        return true;
    }else{
        console.log("Il campo non è un prezzo valido");
        document.getElementById("prezzo").style.borderColor = "red";
        document.getElementById("messagePrice").style.color = "red";
        document.getElementById("messagePrice").innerText = "Il prezzo può contenere solo numeri che possono essere separati da un .";
        return false;
    }
    return false;
}


function controlURL(string) {
    let regexp = /\.jpg$/;

    if (regexp.test(string)){
        document.getElementById("image").style.borderColor = "red";
        document.getElementById("messageURL").style.color = "red";
        document.getElementById("messageURL").innerText = "Error URL";
        return false;
    }else{
        document.getElementById("image").style.borderColor = "green";
        document.getElementById("messageURL").innerText = "";
        return true;
    }
    return false;
}