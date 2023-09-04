package model.dao;

import model.bean.Ordine;
import model.bean.Prodotto;
import model.bean.Utente;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrderModelTest {

	@Test
	void aggiuntaOrdine() {
		OrderModel orderModel = new OrderModel();
		Ordine ordine = new Ordine();
		Utente utente = new Utente();
		// Set properties for the order and user

		try {
			Ordine result = orderModel.aggiuntaOrdine(ordine, utente);

			// Assert that the order was successfully added
			Assert.assertNotNull(result);
			// Add more assertions for other properties if needed
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}

	@Test
	void recuperoOrdini() {
		OrderModel orderModel = new OrderModel();
		Utente utente = new Utente();
		// Set properties for the user

		try {
			ArrayList<Ordine> ordini = orderModel.recuperoOrdini(utente);

			// Assert specific conditions for the retrieved orders
			Assert.assertNotNull(ordini);
			// Assert the size of the orders list, and other conditions if needed
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}

	@Test
	void aggiuntaProdottiOrdine() {
		OrderModel orderModel = new OrderModel();
		Prodotto prodottoOrdinato = new Prodotto();
		Ordine ordine = new Ordine();
		// Set properties for the product and order

		try {
			orderModel.aggiuntaProdottiOrdine(prodottoOrdinato, ordine);

			// Assert that the products were successfully added to the order
			// You can add more assertions if needed
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}

	@Test
	void testRecuperoOrdini() {
		OrderModel orderModel = new OrderModel();

		try {
			ArrayList<Ordine> ordini = orderModel.recuperoOrdini();

			// Assert specific conditions for the retrieved orders
			Assert.assertNotNull(ordini);
			// Assert the size of the orders list, and other conditions if needed
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}

	@Test
	void recuperoProdotti() {
		OrderModel orderModel = new OrderModel();
		Ordine ordine = new Ordine();
		// Set properties for the order

		try {
			ArrayList<Prodotto> prodotti = orderModel.recuperoProdotti(ordine);

			// Assert specific conditions for the retrieved products
			Assert.assertNotNull(prodotti);
			// Assert the size of the products list, and other conditions if needed
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}

	@Test
	void updateState() {
		OrderModel orderModel = new OrderModel();
		Ordine ordine = new Ordine();
		String stato = "Shipped";

		try {
			orderModel.updateState(stato, ordine);

			// Assert that the order state was successfully updated
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}
}