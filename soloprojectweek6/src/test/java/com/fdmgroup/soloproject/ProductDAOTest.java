package com.fdmgroup.soloproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fdmgroup.soloprojectDAO.ProductDAO;

public class ProductDAOTest {
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpaemployeeexample");
	private EntityManager entityManager = entityManagerFactory.createEntityManager();

	@BeforeEach
	public void setUp() {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Query deleteQuery = entityManager.createQuery("Delete from Payment");
		deleteQuery.executeUpdate();
		Query deleteQuery1 = entityManager.createQuery("Delete from Order");
		deleteQuery1.executeUpdate();
		Query deleteQuery2 = entityManager.createQuery("Delete from Customer");
		deleteQuery2.executeUpdate();
		Query deleteQuery3 = entityManager.createQuery("Delete from Product");
		deleteQuery3.executeUpdate();
		entityTransaction.commit();
	}

	@Test // 1
	public void testThatWhenICallTheListProductsMethodInProductsDAOItReturnsAnEmptyList() {
		ProductDAO productDAO = new ProductDAO();
		productDAO.setEntityManager(entityManager);

		List<Product> listProducts = productDAO.listProducts();
		int size = listProducts.size();
		assertEquals(0, size);

	}

	@Test // 2
	public void testThatWhenIAddProductToTheListThatTheListHasOneProduct() {
		ProductDAO productDAO = new ProductDAO();
		productDAO.setEntityManager(entityManager);

		Product product = new Product();
		product.setProductId(1);
		productDAO.addProduct(product);

		List<Product> listProducts = productDAO.listProducts();
		int size = listProducts.size();
		assertEquals(1, size);

	}

	@Test // 3
	public void testThatWhenIAddProductToTheListThatWhenICallGetMethodItReturnsTheProduct() {
		ProductDAO productDAO = new ProductDAO();
		productDAO.setEntityManager(entityManager);

		Product product = new Product();
		product.setProductId(1);
		productDAO.addProduct(product);

		Product productInDB = productDAO.getProduct(1);
		int productIdInDB = productInDB.getProductId();
		assertEquals(1, productIdInDB);

	}

	@Test // 4
	public void testThatWhenIAddProductThenRemoveTheProductTheListIsEmpty() {
		ProductDAO productDAO = new ProductDAO();
		productDAO.setEntityManager(entityManager);

		Product product = new Product();
		product.setProductId(101);
		productDAO.addProduct(product);
		productDAO.removeProduct(101);

		List<Product> listProducts = productDAO.listProducts();
		int size = listProducts.size();
		assertEquals(0, size);

	}

	@Test // 5
	public void testThatWhenIAddAProductToListThenUpdateTheProductThatTheProductHasBeenUpdated() {
		ProductDAO productDAO = new ProductDAO();
		productDAO.setEntityManager(entityManager);

		Product product = new Product();
		product.setProductId(101);
		product.setProductName("Chierowski 366");
		product.setPrice(889.90);
		productDAO.addProduct(product);

		Product newProduct = new Product();
		newProduct.setProductId(101);
		newProduct.setProductName("Hałas");
		newProduct.setPrice(790.99);
		productDAO.updateProduct(newProduct);

		Product productInDB = productDAO.getProduct(101);
		double price = productInDB.getPrice();
		String name = productInDB.getProductName();
		assertEquals(790.99, price);
		assertEquals("Hałas", name);
	}

	@Test // 6
	public void testThatWhenIAddProductWithoutNameToTheListOfProductsTheValueOfProductNameIsNull() {

		ProductDAO productDAO = new ProductDAO();
		productDAO.setEntityManager(entityManager);

		Product product = new Product();
		product.setProductId(101);
		product.setPrice(889.90);
		productDAO.addProduct(product);

		Product productInDB = productDAO.getProduct(101);
		String name = productInDB.getProductName();
		assertNull(name);

	}
}
