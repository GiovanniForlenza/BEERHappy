package com.example.webapptest;

import entity.Carta;
import entity.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CardModel;
import model.ModelSecurity;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AggiuntaCartaServlet", value = "/AggiuntaCartaServlet")
public class AggiuntaCartaServlet extends HttpServlet {

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            ModelSecurity modelSecurity = new ModelSecurity();
            Carta carta = new Carta();

            carta.setIntestatario(request.getParameter("titolare"));
            carta.setDataScadenza(request.getParameter("dataScadenza"));
            carta.setnCata(request.getParameter("numero"));

            Utente utente = (Utente) request.getSession().getAttribute("utente");

            try{
                CardModel cm=new CardModel();
                carta=cm.aggiuntaCarta(carta,utente);
                utente.addCarta(carta);
                request.removeAttribute("utente");
                request.setAttribute("utente", utente);
                response.sendRedirect("http://localhost:8080/webAppTest_war/profilo.jsp");
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

}
