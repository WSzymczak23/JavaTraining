package com.fdmgroup.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.fdmgroup.entities.Product;

public class ProductDAO {

	private static EntityManager entityManager;
	//private List<Product> listOfProducts = new ArrayList<Product>();

	public void setEntityManager(EntityManager entityManager) {
		ProductDAO.entityManager = entityManager;
	}

	public List<Product> listProducts() {
		TypedQuery<Product> queryProduct = entityManager.createQuery("Select p from Product p", Product.class);
		List<Product> listOfProducts = queryProduct.getResultList();
		return listOfProducts;
	}

	public void addProduct(Product newProduct) {
		//listOfProducts.add(newProduct);
		EntityTransaction  entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(newProduct);
        entityTransaction.commit();
	}

	public Product getProduct(int productId) {
		Product product = entityManager.find(Product.class, productId);
		return product;
	}

	public void removeProduct(int productId) {
		Product product = entityManager.find(Product.class, productId);
		EntityTransaction  entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.remove(product);
        entityTransaction.commit();
	}

	public void updateProduct(Product newProduct) {
		EntityTransaction  entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.merge(newProduct);
        entityTransaction.commit();
		
	}

}