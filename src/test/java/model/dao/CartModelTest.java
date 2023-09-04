package model.dao;

import model.bean.Carrello;
import model.bean.Prodotto;
import model.bean.Utente;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CartModelTest {

	@Test
	void salvaCarrello() {
		CartModel cartModel = new CartModel();
		String email = "test@example.com";
		Prodotto prodotto = new Prodotto();
		// Set properties for the product

		try {
			cartModel.salvaCarrello(email, prodotto);

			// Assert that the product was successfully saved in the cart
			// You can add more assertions if needed
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}

	@Test
	void svuotaCarrello() {
		CartModel cartModel = new CartModel();
		Utente utente = new Utente();
		utente.setEmail("test@example.com");

		try {
			cartModel.svuotaCarrello(utente);

			// Assert that the cart was successfully emptied
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}

	@Test
	void recuperoCarrello() {
		CartModel cartModel = new CartModel();
		Utente utente = new Utente();
		utente.setEmail("test@example.com");
		Carrello cartSession = new Carrello();

		try {
			Carrello carrello = cartModel.recuperoCarrello(utente, cartSession);

			// Assert specific conditions for the retrieved cart
			Assert.assertNotNull(carrello);
			// Add more assertions for other properties if needed
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}

	@Test
	void doUpdate() {
		CartModel cartModel = new CartModel();
		Prodotto prodotto = new Prodotto();
		// Set properties for the product

		try {
			boolean result = cartModel.doUpdate(prodotto);

			// Assert that the update was successful
			Assert.assertTrue(result);
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}
}