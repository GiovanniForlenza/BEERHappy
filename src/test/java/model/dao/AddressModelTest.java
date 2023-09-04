package model.dao;

import model.bean.Indirizzo;
import model.bean.Utente;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AddressModelTest {

	@Test
	void doRetrieveByKey() {
		AddressModel addressModel = new AddressModel();

		try {
			Indirizzo indirizzo = addressModel.doRetrieveByKey(1);

			// Assert specific conditions for the retrieved address
			Assert.assertNotNull(indirizzo);
			Assert.assertEquals(1, indirizzo.getID());
			// Add more assertions for other properties if needed
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}

	@Test
	void doDelete() {
		AddressModel addressModel = new AddressModel();

		try {
			boolean result = addressModel.doDelete(1);

			// Assert the deletion was successful
			Assert.assertTrue(result);
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}

	@Test
	void recuperoIndirizzo() {
		AddressModel addressModel = new AddressModel();
		Utente utente = new Utente();
		utente.setEmail("test@example.com");

		try {
			ArrayList<Indirizzo> addresses = addressModel.recuperoIndirizzo(utente);

			// Assert specific conditions for the retrieved addresses
			Assert.assertNotNull(addresses);
			// Assert the size of the addresses list, and other conditions if needed
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}

	@Test
	void aggiuntaIndirizzo() {
		AddressModel addressModel = new AddressModel();
		Indirizzo indirizzo = new Indirizzo();
		Utente utente = new Utente();
		utente.setEmail("test@example.com");

		// Set properties for the new address

		try {
			Indirizzo newIndirizzo = addressModel.aggiuntaIndirizzo(indirizzo, utente);

			// Assert the new address was successfully added and has a valid ID
			Assert.assertNotNull(newIndirizzo);
			Assert.assertNotNull(newIndirizzo.getID());
			// Add more assertions for other properties if needed
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}
}