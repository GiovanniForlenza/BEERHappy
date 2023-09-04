package model.dao;

import model.bean.Prodotto;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CatalogoModelTest {

	@Test
	void recuperoProdotti() {
		CatalogoModel catalogoModel = new CatalogoModel();

		try {
			ArrayList<Prodotto> prodotti = catalogoModel.recuperoProdotti();

			// Assert specific conditions for the retrieved products
			Assert.assertNotNull(prodotti);
			// Assert the size of the products list, and other conditions if needed
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}

	@Test
	void addProduct() {
		CatalogoModel catalogoModel = new CatalogoModel();
		Prodotto prodotto = new Prodotto();
		// Set properties for the new product

		prodotto.setNome("Gouden Carolus");
		prodotto.setBirrificio("Het Anker");
		prodotto.setDescrizione("E' una birra belga dal bellissimo colore ambrato con profumo di torbatura, fumo e carbone. Al palato sono presenti note di sottobosco di frutti rossi, aroma di tostatura, sapore fruttato e speziato caratteristico del lievito belga.");
		prodotto.setFormato("Bottiglia 75cl");
		prodotto.setPrezzo(19.50F);
		prodotto.setQuantitaDisp(20);
		prodotto.setPathImage("https://www.beer-shop.it/uploads/items/2374/4691-1024.jpg?=1669660702");

		try {
			boolean result = catalogoModel.addProduct(prodotto);

			// Assert that the new product was successfully added
			Assert.assertTrue(result);
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}

	@Test
	void updateProduct() {
		CatalogoModel catalogoModel = new CatalogoModel();
		Prodotto oldProduct = new Prodotto();
		Prodotto newProduct = new Prodotto();
		// Set properties for the old and new products

		try {
			boolean result = catalogoModel.updateProduct(oldProduct, newProduct);

			// Assert that the update was successful
			Assert.assertTrue(result);
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}

	@Test
	void searchProductByKey() {
		CatalogoModel catalogoModel = new CatalogoModel();
		String nome = "Breakfast Porter";
		String birrificio = "Impavida";

		try {
			boolean result = catalogoModel.searchProductByKey(nome, birrificio);

			// Assert that the product with the specified name and brewery was found
			Assert.assertTrue(result);
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("Exception occurred");
		}
	}
}