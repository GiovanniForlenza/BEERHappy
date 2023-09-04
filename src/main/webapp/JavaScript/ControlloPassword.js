var check = function() {

    if(document.getElementById('password').value === "" ||
        document.getElementById('confirm_password').value === ""){

        document.getElementById('password').style.borderColor = 'red';
        document.getElementById('confirm_password').style.borderColor = 'red';
    }
    else if (document.getElementById('password').value ===
        document.getElementById('confirm_password').value) {
        document.getElementById('message').style.color = 'green';
        document.getElementById('message').innerHTML = 'matching';
    } else {
        document.getElementById('message').style.color = 'red';
        document.getElementById('message').innerHTML = 'Le password non coincidono';
    }
}