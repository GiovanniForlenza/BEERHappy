function enable() {

    document.getElementById("name").disabled = false;
    document.getElementById("cognome").disabled = false;

    let btn = document.getElementById("save").hidden = false;
    document.getElementById("button").hidden = true;

    document.getElementById("name").onkeyup = (controlLetteralName);
    document.getElementById("cognome").onkeyup = (controlLetteralCognome);


    btn.onclick = function (){
        document.location.href = "http://localhost:8080/webAppTest_war/ModificaDatiServlet";
    }

}