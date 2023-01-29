package model;

import entity.Prodotto;
import entity.Utente;

import java.sql.SQLException;
import java.util.Collection;

public interface Security{
	public void addUser (Utente utente) throws SQLException;
	public boolean controlloEmailRegistrazione(Utente utente) throws SQLException;
	public Collection<Prodotto> doRetrieveAll() throws SQLException;
}
