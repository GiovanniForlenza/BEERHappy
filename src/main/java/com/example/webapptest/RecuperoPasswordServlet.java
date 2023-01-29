package com.example.webapptest;

import entity.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.ModelSecurity;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

@WebServlet(name = "RecuperoPasswordServlet", value = "/RecuperoPasswordServlet")
public class RecuperoPasswordServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response){
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            ModelSecurity ms = new ModelSecurity();
            String email = req.getParameter("email");
            String messaggio="Le è stata assegnata una nuova password: ";
            String password;

            try {
                if ((password=ms.recuperoPasswordUtenteBO(email))!=null) {
                    SendEmail invioEmail = new SendEmail();
                    messaggio=messaggio+password;
                    invioEmail.SendingEmail(email, messaggio, "Recupero password");
                    resp.sendRedirect("http://localhost:8080/webAppTest_war/accesso.jsp");
                }
                else if((password=ms.recuperoPassword(email))!=null){
                    SendEmail invioEmail = new SendEmail();
                    messaggio=messaggio+password;
                    invioEmail.SendingEmail(email, messaggio, "Recupero password");
                    resp.sendRedirect("http://localhost:8080/webAppTest_war/accesso.jsp");
                }
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
}