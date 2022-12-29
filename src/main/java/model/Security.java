package model;

import entity.Utente;

import java.sql.SQLException;

public interface Security{
	public void addUser (Utente utente) throws SQLException;
	public boolean controlloAccesso (String email, String password) throws SQLException;
}
