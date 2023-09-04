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

@WebServlet(name = "AggiuntaUtenteBOServlet", value = "/AggiuntaUtenteBOServlet")
public class AggiuntaUtenteBOServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] ruoli=request.getParameterValues("ruolo");
        UtenteBO utenteBO=new UtenteBO();
        utenteBO.setEmail(request.getParameter("email"));
        utenteBO.setPassword(request.getParameter("password"));
        ArrayList<UtenteBO> utenti=(ArrayList<UtenteBO>) request.getSession().getAttribute("utenti");

        if(ruoli.length==0){
            request.getSession().setAttribute("oldUser", utenteBO);
        } else if(ruoli.length==1){
            if(ruoli[0].equals("Gestore catalogo"))
                utenteBO.setRuolo(1);
            else utenteBO.setRuolo(2);
        } else {
            utenteBO.setRuolo(3);
        }

        UserModel mc=new UserModel();
        try {
            if(mc.cercaUtenteBO(utenteBO.getEmail())){
                request.getSession().setAttribute("error", "E-mail già presente");
                response.sendRedirect("http://localhost:8080/webAppTest_war/aggiuntaUtente.jsp");
            }else{
                if(mc.addUserBO(utenteBO)) {
                    utenti.add(utenteBO);
                    request.getSession().removeAttribute("utenti");
                    request.getSession().setAttribute("utenti", utenti);
                    response.sendRedirect("http://localhost:8080/webAppTest_war/gestioneUtenti.jsp");
                }
                else
                    response.sendRedirect("http://localhost:8080/webAppTest_war/aggiuntaUtente.jsp");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}