function controlMail(mail) {
    let message = document.getElementById("messageEmail");

    let regexp = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    if (!regexp.test(mail) && mail.localeCompare("")!=0)
    {
        document.getElementById("email").style.borderColor = "red";
        document.getElementById("messageEmail").style.color = "red";
        message.innerText = "L'email deve rispettare il seguente formato example@example.it";
        return false;
    } else {
        document.getElementById("email").style.borderColor = "green";
        message.innerText = "";
        return true;
    }
    return false;
}

function controlLetteralName(string) {
    let message = document.getElementById("messageLetter");
    string = string.replace("'", "");
    let regexp = /\d/;

    if(string === ""){
        document.getElementById("name").style.borderColor = "red";
        return false;
    }
    else if (regexp.test(string) && string.localeCompare("")!=0)
    {
        document.getElementById("name").style.borderColor = "red";
        document.getElementById("messageLetter").style.color = "red";
        message.innerText = "questo campo può essere solo di tipo letterale";
        return false;
    }
    else {
        document.getElementById("name").style.borderColor = "green";
        message.innerText = "";
        return true;
    }
    return false;
}

function controlLetteralCognome(string) {
    let message = document.getElementById("messageLetter");
    string = string.replace("'", "");
    let regexp = /\d/;

    if(string === ""){
        document.getElementById("cognome").style.borderColor = "red";
        return false;
    }
    else if (regexp.test(string) && string.localeCompare("")!=0 )
    {
        document.getElementById("cognome").style.borderColor = "red";
        document.getElementById("messageLetter").style.color = "red";
        message.innerText = "questo campo può essere solo di tipo letterale";
        return false;
    }
    else {
        document.getElementById("cognome").style.borderColor = "green";
        message.innerText = "";
        return true;
    }
    return false;
}

function isPassword() {

    let password = document.getElementById("password").value;
    let confPassword = document.getElementById("confirm_password").value;
    let message = document.getElementById("messagePassword");

    if (password.length >= 8 && password.length <= 30 && password.localeCompare("")!=0 ) {
        if(password != null && confPassword != null && password.value != '' && confPassword.value != '') {
            if (password == confPassword) {
                document.getElementById("password").style.borderColor = "green";
                document.getElementById("confirm_password").style.borderColor = "green";
                message.innerText = "";
                return true;
            }
            else{
                document.getElementById("confirm_password").style.borderColor = "red";
                document.getElementById("messagePassword").style.color = "red";
                
                return false;
            }
        }
    }
    else {
        document.getElementById("password").style.borderColor = "red";
        document.getElementById("messagePassword").style.color = "red";

        message.innerText = "La lunghezza della password deve essere compresa tra 8 e 30 caratteri";
        return false;
    }
}

function checkPassword(str){
    if(str === "") {
        document.getElementById("confirm_password").style.borderColor = "red";
        return false;
    }
    else if (document.getElementById('password').value ===
        document.getElementById('confirm_password').value) {
        document.getElementById("confirm_password").style.borderColor = "red";
        return true;
    } else {
        return false;
    }
}

function controlLetteralVIA(string) {
    let message = document.getElementById("messageLetterVia");
    string = string.replace("'", "");
    let regexp = /\d/;

    if (regexp.test(string) && string.localeCompare("")!=0 )
    {
        document.getElementById("inputAddress").style.borderColor = "red";
        document.getElementById("messageLetterVia").style.color = "red";
        message.innerText = "questo campo può essere solo di tipo letterale";
        return false;
    }
    else {
        document.getElementById("inputAddress").style.borderColor = "green";
        message.innerText = "";
        return true;
    }
    return false;
}

function controlLetteralCITTA(string) {
    let message = document.getElementById("messageLetterCITTA");
    string = string.replace("'", "");
    let regexp = /\d/;

    if (regexp.test(string) && string.localeCompare("")!=0 )
    {
        document.getElementById("inputCity").style.borderColor = "red";
        document.getElementById("messageLetterCITTA").style.color = "red";
        message.innerText = "questo campo può essere solo di tipo letterale";
        return false;
    }
    else {
        document.getElementById("inputCity").style.borderColor = "green";
        message.innerText = "";
        return true;
    }
    return false;
}

function controlCAP(string) {
    string = string.replace(",", "");
    let regexp = /^\d{5}$/;

    if (!regexp.test(string) && string > -1) {
        document.getElementById("inputCap").style.borderColor = "red";
        document.getElementById("messageCAP").style.color = "red";
        document.getElementById("messageCAP").innerText = "il CAP è di 5 numeri";
        return false;
    }else{
        document.getElementById("inputCap").style.borderColor = "green";
        document.getElementById("messageCAP").innerText = "";
        return true;
    }
    return false;

}
function controlCIVICO(string) {
    let regexp = /^[0-9]+$/;

    if (!regexp.test(string) && string.localeCompare("")!=0){
        document.getElementById("inputCIVICO").style.borderColor = "red";
        document.getElementById("messageCIVICO").style.color = "red";
        document.getElementById("messageCIVICO").innerText = "Il numero civico puo essere composto da solo numeri";
        return false;
    }else if(string.length > 3){
        document.getElementById("inputCIVICO").style.borderColor = "red";
        document.getElementById("messageCIVICO").style.color = "red";
        document.getElementById("messageCIVICO").innerText = "Il numero civico puo essere composto da massimo 3 numeri";
        return false;
    }
    else{
        document.getElementById("inputCIVICO").style.borderColor = "green";
        document.getElementById("messageCIVICO").innerText = "";
        return true;
    }
    return false;
}
function controlCell(string) {

    let regexp = /^[0-9]+$/;

    if (!regexp.test(string) && string.localeCompare("")!=0){
        document.getElementById("inputTelefono").style.borderColor = "red";
        document.getElementById("messageCell").style.color = "red";
        document.getElementById("messageCell").innerText = "Il numero si telefono puo contenere solo numeri";
        return false;
    }else if(string.length < 9 || string.length > 10){
        document.getElementById("inputTelefono").style.borderColor = "red";
        document.getElementById("messageCell").style.color = "red";
        document.getElementById("messageCell").innerText = "Controllare il numero di telefono, non rispetta la lunghezza";
        return false;
    }
    else{
        document.getElementById("inputTelefono").style.borderColor = "green";
        document.getElementById("messageCell").innerText = "";
        return true;
    }
    return false;
}


// - CARD
function controlCard(string) {
    string = string.replace(",", "");
    let regexp = /^\d{16}$/;
    let num = /^[0-9]+$/;

    if (!num.test(string) && string.localeCompare("")!=0){
        document.getElementById("inputCardNumber").style.borderColor = "red";
        document.getElementById("messageCard").style.color = "red";
        document.getElementById("messageCard").innerText = "Il numero carta puo essere composto da solo numeri";
        return false;
    }else if (!regexp.test(string) && string > -1)
    {
        document.getElementById("inputCardNumber").style.borderColor = "red";
        document.getElementById("messageCard").style.color = "red";
        document.getElementById("messageCard").innerText = "La carta deve avere 16 numeri";
        return false;
    }
    else{
        document.getElementById("inputCardNumber").style.borderColor = "green";
        document.getElementById("messageCard").innerText = "";
        return true;
    }
    return false;
}

function controlDate(string) {
    let regexp = /\d{4}\-\d{1,2}\-\d{1,2}$/;
    if (!regexp.test(string) && string.localeCompare("")!=0)
    {
        document.getElementById("inputExpiry").style.borderColor = "red";
        document.getElementById("messageDate").style.color = "red";
        document.getElementById("messageDate").innerText = "Inserire dati validi nei campi data";
        return false;
    }else{
        document.getElementById("inputExpiry").style.borderColor = "green";
        document.getElementById("messageDate").innerText = "";
        return true;
    }
    return false;
}

function controlCVC(string) {
    let message = document.getElementById("messageCVC");
    string = string.replace(",", "");
    let regexp = /^\d{3}$/;

    let num = /^[0-9]+$/;

    if (!num.test(string) && string.localeCompare("")!=0){
        document.getElementById("inputCVC").style.borderColor = "red";
        document.getElementById("messageCVC").style.color = "red";
        document.getElementById("messageCVC").innerText = "Il numero cvc puo essere composto da solo numeri";
        return false;
    }else if (!regexp.test(string) && string > -1) {
        document.getElementById("inputCVC").style.borderColor = "red";
        document.getElementById("messageCVC").style.color = "red";
        message.innerText = "il cvv è di 3 numeri";
        return false;
    }else{
        document.getElementById("inputCVC").style.borderColor = "green";
        message.innerText = "";
        return true;
    }
    return true;
}


function emailBO(string){
    //let regexp = /^\w+([\.-]?\w+)*@\w+(beerhappy\w+)*(\.\w{2,3})+$/;
    let regexp = /@beerhappy\.it$/;

    if(string === "" || string.length === 0){
        document.getElementById("email").style.borderColor = "red";
        document.getElementById("messageMail").style.color = "red";
        document.getElementById("messageMail").innerText = "La meil non puo essere vuota";
        return false;
    }
    else if (!regexp.test(string) && string.localeCompare("")!=0) {
        document.getElementById("email").style.borderColor = "red";
        document.getElementById("messageMail").style.color = "red";
        document.getElementById("messageMail").innerText = "La meil deve terminare @beerhappy.it";
        return false;
    }else{
        document.getElementById("email").style.borderColor = "green";
        document.getElementById("messageMail").innerText = "";
        return true;
    }
}