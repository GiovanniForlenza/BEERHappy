package model.dao;

import model.bean.Carta;
import model.bean.Utente;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CardModelTest {

	@Test
	void doRetrieveByKey() {
		CardModel cardModel = new CardModel();

		try {
			Carta carta = cardModel.doRetrieveByKey(1);

			// Assert specific conditions for the retrieved card
			Assert.assertNotNull(carta);
			Assert.assertEquals(1, carta.getId());
			// Add more assertions for other properties if needed
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}

	@Test
	void recuperoCarte() {
		CardModel cardModel = new CardModel();
		Utente utente = new Utente();
		utente.setEmail("test@example.com");

		try {
			ArrayList<Carta> carte = cardModel.recuperoCarte(utente);

			// Assert specific conditions for the retrieved cards
			Assert.assertNotNull(carte);
			// Assert the size of the cards list, and other conditions if needed
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}

	@Test
	void doDelete() {
		CardModel cardModel = new CardModel();

		try {
			boolean result = cardModel.doDelete(1);

			// Assert the deletion was successful
			Assert.assertTrue(result);
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}

	@Test
	void aggiuntaCarta() {
		CardModel cardModel = new CardModel();
		Carta carta = new Carta("1234123443214321","2026-03-12",111,"Pincko Pallino",22);
		Utente utente = new Utente();
		utente.setEmail("andreaMaglio@gmail.com");

		// Set properties for the new card

		try {
			Carta newCarta = cardModel.aggiuntaCarta(carta, utente);

			// Assert the new card was successfully added and has a valid ID
			Assert.assertNotNull(newCarta);
			Assert.assertNotNull(newCarta.getId());
			// Add more assertions for other properties if needed
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}
}