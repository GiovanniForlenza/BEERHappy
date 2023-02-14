<%--
  Created by IntelliJ IDEA.
  User: jhon
  Date: 16/12/2022
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login PAGE</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/loginpage.css" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

</head>
<body>


    <div class="container">
        <div id="cover">
            <h1 class="sign-up">
                Hello Friend!</h1>
            <p class="sign-up">
                Enter your Personal details<br>and start a journey with us</p>
            <a class="button sign-up" href="#cover">Sign Up</a>
            <h1 class="sign-in">
                Welcome Back!</h1>
            <p class="sign-in">
                To keep connected with us please<br>login with your personal info</p>
            <br>
            <a class="button sub sign-in" href="#">Sign In</a>
        </div>
        <div class="login">
            <h1>
                Sign In</h1>
            <form method="POST" action="AccedereServlet">
                <input type="email" name="e-mail" placeholder="Email" class="input-field"><br>
                <input type="password" name="password" placeholder="Password" class="input-field"><br>

                <a id="forgot-pass" href="recuperoPassword.jsp">Forgot your password</a><br>
                <input type="submit" type="submit" value="Sign In" class="submit-btn">
            </form>

        </div>
        <div class="register">
            <h1>
                Create Account</h1>
            <form method="POST" action="RegistrazioneServlet">
                <input type="text" placeholder="Name" name="nome" class="input-field" autocomplete="off" required><br>
                <input type="text" placeholder="Cognome" name="cognome" class="input-field" autocomplete="off" required><br>
                <input type="email" placeholder="Email" name="e-mail" class="input-field" required><br>
                <input type="password" placeholder="Password" name="password" class="input-field" required><br>
                <input type="password" placeholder="Password" name="repassword" class="input-field" required><br>
                <input class="submit-btn" type="submit" value="Sign up">
            </form>
        </div>
    </div>

</body>
</html>
