package com.example.webapptest;

import entity.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CardModel;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RimozioneCartaServlet", value = "/RimozioneCartaServlet")
public class RimozioneCartaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String s=req.getParameter("cartaID");
        System.out.println(s);
        int cartaID;
        System.out.println(s);
        cartaID=Integer.parseInt(s);
        CardModel am=new CardModel();
        try {
            am.doDelete(cartaID);
        }catch (SQLException e){
            e.printStackTrace();
        }
        Utente utente=(Utente)req.getSession().getAttribute("utente");
        utente.removeCarta(cartaID);
        req.removeAttribute("utente");
        req.setAttribute("utente", utente);

        resp.sendRedirect("http://localhost:8080/webAppTest_war/profilo.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
