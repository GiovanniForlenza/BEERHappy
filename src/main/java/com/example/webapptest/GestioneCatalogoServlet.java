package com.example.webapptest;

import entity.Prodotto;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.CatalogoModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "GestioneCatalogoServlet", value = "/GestioneCatalogoServlet")
public class GestioneCatalogoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CatalogoModel cm=new CatalogoModel();
        ArrayList<Prodotto> prodotti=new ArrayList<Prodotto>();
        try {
            prodotti=cm.recuperoProdotti();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.getSession().setAttribute("prodotti", prodotti);
    }
}
