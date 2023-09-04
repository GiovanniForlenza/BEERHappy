package model.dao;

import model.bean.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserModelTest {

	@Test
	void addUser() {
		UserModel userModel = new UserModel();
		Utente utente = new Utente();

		utente.setEmail("test@example.com");
		utente.setPassword("password");
		utente.setNome("Pinko");
		utente.setCognome("Pallino");

		ArrayList<Carta> carte = new ArrayList<>();
		utente.setCarte(carte);

		ArrayList<Indirizzo> indirizzi = new ArrayList<>();
		utente.setIndirizzi(indirizzi);

		Carrello carrello = new Carrello();
		utente.setCarrello(carrello);

		ArrayList<Ordine> ordini = new ArrayList<>();
		utente.setOrdini(ordini);

		try {
			userModel.addUser(utente);
			// Assert that the user was successfully added
			// You can add more assertions if needed
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}

	@Test
	void loginUtente() {
		UserModel userModel = new UserModel();
		String email = "pinoPaolo@gmail.com";
		String password = "ilPiccoloPino";

		try {
			Utente utente = userModel.loginUtente(email, password);

			// Assert specific conditions for the logged-in user
			Assert.assertNotNull(utente);
			// Add more assertions for other properties if needed
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}

	@Test
	void controlloEmailRegistrazione() {
		UserModel userModel = new UserModel();
		Utente utente = new Utente();
		// Set properties for the user

		utente.setEmail("test2@example.com");
		utente.setPassword("password2");
		utente.setNome("Pinko");
		utente.setCognome("Pallino");

		ArrayList<Carta> carte = new ArrayList<>();
		utente.setCarte(carte);

		ArrayList<Indirizzo> indirizzi = new ArrayList<>();
		utente.setIndirizzi(indirizzi);

		Carrello carrello = new Carrello();
		utente.setCarrello(carrello);

		ArrayList<Ordine> ordini = new ArrayList<>();
		utente.setOrdini(ordini);

		try {
			boolean result = userModel.controlloEmailRegistrazione(utente);

			// Assert specific conditions for the email registration check result
			Assert.assertTrue(result);
			// Add more assertions if needed
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}

	@Test
	void removeUser() {
		UserModel userModel = new UserModel();
		Utente utente = new Utente();
		// Set properties for the user

		try {
			userModel.removeUser(utente);

			// Assert that the user was successfully removed
			// You can add more assertions if needed
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}

	@Test
	void cercaUtente() {
		UserModel userModel = new UserModel();
		String email = "ciropallinopal@beerhappy.it";

		try {
			boolean result = userModel.cercaUtente(email);

			// Assert specific conditions for the search result
			Assert.assertFalse(result);
			// Add more assertions if needed
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}

	@Test
	void recuperoPassword() {
		UserModel userModel = new UserModel();
		String email = "test@example.com";

		try {
			String password = userModel.recuperoPassword(email);

			// Assert specific conditions for the recovered password
			Assert.assertNotNull(password);
			// Add more assertions if needed
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}


	@Test
	void loginAmministratore() {
		UserModel userModel = new UserModel();
		String email = "admin@beerhappy.it";
		String password = "admin";


		try {
			UtenteBO utenteBO = userModel.loginAmministratore(email, password);

			// Assert specific conditions for the logged-in administrator
			Assert.assertNotNull(utenteBO);
			// Add more assertions for other properties if needed
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}

	@Test
	void cercaUtenteBO() {
		UserModel userModel = new UserModel();
		String email = "ciropallinopal@beerhappy.it";

		try {
			boolean result = userModel.cercaUtenteBO(email);

			// Assert specific conditions for the search result
			Assert.assertTrue(result);
			// Add more assertions if needed
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}

	@Test
	void modificaDati() {
		UserModel userModel = new UserModel();
		Utente utente = new Utente();
		String nome = "NuovoNome";
		String cognome = "NuovoCognome";

		try {
			Utente modifiedUser = userModel.modificaDati(utente, nome, cognome);

			// Assert specific conditions for the modified user
			Assert.assertNotNull(modifiedUser);
			// Add more assertions for other properties if needed
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}

	@Test
	void cambioPassword() {
		UserModel userModel = new UserModel();
		Utente utente = new Utente();
		String password = "newpassword";

		try {
			userModel.cambioPassword(utente, password);

			// Assert that the password was successfully changed
			// You can add more assertions if needed
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}

	@Test
	void recuperoUtentiBO() {
		UserModel userModel = new UserModel();

		try {
			ArrayList<UtenteBO> utentiBO = userModel.recuperoUtentiBO();

			// Assert specific conditions for the retrieved users
			Assert.assertNotNull(utentiBO);
			// Assert the size of the users list, and other conditions if needed
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}

	@Test
	void updateRoles() {
		UserModel userModel = new UserModel();
		UtenteBO utenteBO = new UtenteBO();
		int ruolo = 2;

		try {
			userModel.updateRoles(utenteBO, ruolo);

			// Assert that the roles were successfully updated
			// You can add more assertions if needed
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}

	@Test
	void deleteUtenteBO() {
		UserModel userModel = new UserModel();
		UtenteBO utenteBO = new UtenteBO();

		try {
			userModel.deleteUtenteBO(utenteBO);

			// Assert that the user was successfully deleted
			// You can add more assertions if needed
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}

	@Test
	void addUserBO() {
		UserModel userModel = new UserModel();
		UtenteBO utenteBO = new UtenteBO();
		// Set properties for the userBO

		utenteBO.setEmail("testerAdmin@beerhappy.it");
		utenteBO.setPassword("passwordAdmin");
		utenteBO.setRuolo(2);

		try {
			userModel.addUserBO(utenteBO);
			// Assert that the userBO was successfully added
			// You can add more assertions if needed
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}
}