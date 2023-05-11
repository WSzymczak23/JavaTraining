package com.fdmgroup.soloproject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.daos.CustomerDAO;
import com.fdmgroup.entities.Customer;




public class CustomerDAOtest {

	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soloprojectweek6");
	private EntityManager entityManager = entityManagerFactory.createEntityManager();
	
	@Before
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
	
	@Test //1
	public void testThatWhenICallTheListCustomersMethodInCustomerDAOItReturnsAnEmptyList() {
		CustomerDAO customerDAO = new CustomerDAO();
		customerDAO.setEntityManager(entityManager);
		
		List<Customer> listCustomers = customerDAO.listCustomers();
		int size = listCustomers.size();
		assertEquals(0, size);

	}
	
	@Test //2
	public void testThatWhenIAddCustomerToTheListThatTheListHasOneCustomer() {
		CustomerDAO customerDAO = new CustomerDAO();
		customerDAO.setEntityManager(entityManager);
		
		Customer customer = new Customer();
		customer.setUsername("user1");
		customerDAO.addCustomer(customer);
			
		List<Customer> listCustomers = customerDAO.listCustomers();
		int size = listCustomers.size();
		assertEquals(1, size);
	}
	
	@Test //3
	public void testThatWhenIAddCustomerToTheListThatWhenICallGetMethodItReturnsTheCustomer() {
		CustomerDAO customerDAO = new CustomerDAO();
		customerDAO.setEntityManager(entityManager);
		
		Customer customer = new Customer();
		customer.setUsername("user1");
		customerDAO.addCustomer(customer);
		
		
		Customer customerInDB = customerDAO.getCustomer("user1");
		String usernameInDB = customerInDB.getUsername();
		assertEquals("user1", usernameInDB);
		
	}
	
	@Test //4
	public void testThatWhenIAddCustomerThenRemoveTheCustomerTheListIsEmpty() {
		CustomerDAO customerDAO = new CustomerDAO();
		customerDAO.setEntityManager(entityManager);
		
		Customer customer = new Customer();
		customer.setUsername("user1");
		customerDAO.addCustomer(customer);
		customerDAO.removeCustomer("user1");
		
		List<Customer> listCustomers = customerDAO.listCustomers();
		int size = listCustomers.size();
		assertEquals(0, size);
	}

	@Test //5
	public void testThatWhenIAddACustomerToListThenUpdateTheCustomerThatTheCustomerHasBeenUpdated() {
		CustomerDAO customerDAO = new CustomerDAO();
		customerDAO.setEntityManager(entityManager);
		
		Customer customer = new Customer();
		customer.setUsername("user1");
		customer.setPassword("000");
		customerDAO.addCustomer(customer);
		
		Customer newCustomer = new Customer();
		newCustomer.setUsername("user1");
		newCustomer.setPassword("new password");
		customerDAO.updateCustomer(newCustomer);
		
		Customer customerInDB = customerDAO.getCustomer("user1");
		String password = customerInDB.getPassword();
		assertEquals("new password", password);
	}
	
	
	/*@Test //6
	public void testThatWhenIAddCustomerAndOrderAndAssignCustomerToThisOrderAndThanRemoveCustomerFromCustomersListThatHeIsOrdersAreRemoved() {
		
		//should I remove just an assignment?
		CustomerDAO customerDAO = new CustomerDAO();
		OrderDAO orderDAO = new OrderDAO();
		customerDAO.setEntityManager(entityManager);
		orderDAO.setEntityManager(entityManager);
		
		
		Customer customer = new Customer();
		customer.setUsername("user1");
		customerDAO.addCustomer(customer);
		
		Order order = new Order ();
		order.setOrderId(101);
		order.setCustomer(customer);
		orderDAO.addOrder(order);
		
		customerDAO.removeCustomer("user1");
		
		Order orderForCustomer = orderDAO.getOrder(101);
		 
		assertNull(orderForCustomer);
}*/
	
	@Test 
	public void testThatWhenIAddCustomerAndUpdateCustomerwithNewDataTheValuesThatWhereCreatedInFirstPlaceButNotUpdatedLaterAreNull() {
		//I want to change update function so it uses the old data
		CustomerDAO customerDAO = new CustomerDAO();
		customerDAO.setEntityManager(entityManager);
		
		Customer customer = new Customer();
		customer.setUsername("user1");
		customer.setPassword("000");
		customer.setEmail("w.s@gmail.com");
		customerDAO.addCustomer(customer);
		
		Customer newCustomer = new Customer();
		newCustomer.setUsername("user1");
		newCustomer.setPassword("new password");
		customerDAO.updateCustomer(newCustomer);
		
		Customer customerInDB = customerDAO.getCustomer("user1");
		String password = customerInDB.getPassword();
		String email = customerInDB.getEmail();
		assertEquals("new password", password);
		assertNull(email);
	}
	
}
