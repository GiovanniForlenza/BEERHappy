package controller;

import model.bean.UtenteBO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.UserModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ModificaRuoliUtenteBOServlet", value = "/ModificaRuoliUtenteBOServlet")
public class ModificaRuoliUtenteBOServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] ruoli=request.getParameterValues("ruolo");
        UtenteBO utenteBO =(UtenteBO) request.getSession().getAttribute("utente");
        int ruolo=0;

        UserModel ms=new UserModel();
        if(ruoli.length==0){
            request.getSession().setAttribute("oldUser", utenteBO);
        } else if(ruoli.length==1){
            if(ruoli[0].equals("Gestore catalogo"))
                ruolo=1;
            else ruolo=2;
        } else if(ruoli.length==2){
            ruolo=3;
        }

        try {
            ms.updateRoles(utenteBO, ruolo);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<UtenteBO> utenti=(ArrayList<UtenteBO>) request.getSession().getAttribute("utenti");
        utenteBO.setRuolo(ruolo);
        utenti=UtenteBO.remove(utenti, utenteBO);
        utenti.add(utenteBO);
        request.getSession().removeAttribute("utenti");
        request.getSession().setAttribute("utenti", utenti);
        response.sendRedirect("http://localhost:8080/webAppTest_war/gestioneUtenti.jsp");
    }
}