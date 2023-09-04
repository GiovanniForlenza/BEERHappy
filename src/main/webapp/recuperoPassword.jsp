<%--
  Created by IntelliJ IDEA.
  User: jhon
  Date: 14/02/23
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Password Recovery</title>

	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>

<div class="container">
	<div class="row justify-content-center mt-5">
		<div class="col-md-6">
			<div class="card">
				<div class="card-header">
					<h4>Password Recovery</h4>
				</div>
				<div class="card-body">
					<form id="passwordRecoveryForm">
						<div class="form-group">
							<label for="email">Email address</label>
							<input type="email" class="form-control" id="email" name="email" placeholder="Enter email">
						</div>
						<%
							Boolean error = (Boolean) request.getSession().getAttribute("errormessage");
							if(error != null && error == true){
								request.getSession().setAttribute("errormessage", false);
						%>
						<div class="form-group">
							<p>E-mail non presente</p>
						</div>
						<%
							}
						%>
						<button type="button" class="btn btn-primary btn-block" onclick="sendEmail()">Submit</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="https://smtpjs.com/v3/smtp.js"></script>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
    function sendEmail() {
        $.ajax({
            url: "RecuperoPasswordServlet",
            method: 'POST',
            data: {
                email: $("#email").val(),
                randomParam: new Date().getTime()
            },
            success: function (result) {
                if (result.email !== "null" && result.password !== "null") {
					inviaMail(result.email, result.password);
                    window.location.href = "http://localhost:8080/webAppTest_war/login.jsp"
                } else {
                    console.log("Errore: Dati non validi");
                    window.location.href = "http://localhost:8080/webAppTest_war/recuperoPassword.jsp";
                }
            },
            error: function () {
                console.log('Errore durante la chiamata AJAX.');
                window.location.href = "http://localhost:8080/webAppTest_war/recuperoPassword.jsp";
            }
        });
    }
</script>
<script>
    function inviaMail(email, password){

        Email.send({
            SecureToken : "021cc67d-8a74-4d25-8470-b87774185a58",
            To : email,
            From : "beerhappy.smtp@gmail.com",
            Subject : "BEerHAPPY recover password",
            Body : "Your code for your access is: " + password
        }).then(
            message => alert("Ti è stata inviata una mail con la nuova password")
        );
    }
</script>
</body>
</html>

