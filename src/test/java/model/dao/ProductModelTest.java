package model.dao;

import model.bean.Prodotto;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProductModelTest {

	@Test
	void doRetrieveByKey() {
		ProductModel productModel = new ProductModel();
		String nome = "NomeProdotto";
		String birrificio = "BirrificioProdotto";

		try {
			Prodotto prodotto = productModel.doRetrieveByKey(nome, birrificio);

			// Assert specific conditions for the retrieved product
			Assert.assertNotNull(prodotto);
			// Add more assertions for other properties if needed
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}

	@Test
	void doDelete() {
		ProductModel productModel = new ProductModel();
		Prodotto prodotto = new Prodotto();
		// Set properties for the product

		try {
			productModel.doDelete(prodotto);

			// Assert that the product was successfully deleted
			// You can add more assertions if needed
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}

	@Test
	void doRetrieveAll() {
		ProductModel productModel = new ProductModel();

		try {
			ArrayList<Prodotto> prodotti = productModel.doRetrieveAll();

			// Assert specific conditions for the retrieved products
			Assert.assertNotNull(prodotti);
			// Assert the size of the products list, and other conditions if needed
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}

	@Test
	void doRetrieveProducts() {
		ProductModel productModel = new ProductModel();
		String name = "Product";

		try {
			ArrayList<Prodotto> prodotti = productModel.doRetrieveProducts(name);

			// Assert specific conditions for the retrieved products
			Assert.assertNotNull(prodotti);
			// Assert the size of the products list, and other conditions if needed
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}
}