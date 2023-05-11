package com.fdmgroup.soloproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fdmgroup.soloprojectDAO.CustomerDAO;
import com.fdmgroup.soloprojectDAO.OrderDAO;

public class OrderDAOtest {

	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpaemployeeexample");
	private EntityManager entityManager = entityManagerFactory.createEntityManager();
	
	@BeforeEach
	public void setUp() {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Query deleteQuery = entityManager.createQuery("Delete from Order");
		deleteQuery.executeUpdate();
		Query deleteQuery1 = entityManager.createQuery("Delete from Customer");
		deleteQuery1.executeUpdate();
		entityTransaction.commit();
	}
	
	@Test //1
	public void testThatWhenICallTheListOrdersMethodInOrderDAOItReturnsAnEmptyList() {
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.setEntityManager(entityManager);
		
		List<Order> listOrders = OrderDAO.listOrders();
		int size = listOrders.size();
		assertEquals(0, size);

	}
	
	@Test //2
	public void testThatWhenIAddOrderToTheListThatTheListHasOneOrder() {
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.setEntityManager(entityManager);
		
		Order order = new Order();
		order.setOrderId(101);
		orderDAO.addOrder(order);
			
		List<Order> listOrders = OrderDAO.listOrders();
		int size = listOrders.size();
		assertEquals(1, size);
	}
	
	@Test //3
	public void testThatWhenIAddOrderToTheListThatWhenICallGetMethodItReturnsTheOrder() {
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.setEntityManager(entityManager);
		
		Order order = new Order();
		order.setOrderId(101);
		orderDAO.addOrder(order);
		
		
		Order orderInDB = orderDAO.getOrder(101);
		int orderIdInDB = orderInDB.getOrderId();
		assertEquals(101, orderIdInDB);
		
	}
	
	@Test //4
	public void testThatWhenIAddOrderThenRemoveTheOrderTheListIsEmpty() {
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.setEntityManager(entityManager);
		
		Order order = new Order();
		order.setOrderId(101);
		orderDAO.addOrder(order);
		orderDAO.removeOrder(101);
		
		List<Order> listOrders = OrderDAO.listOrders();
		int size = listOrders.size();
		assertEquals(0, size);
	}

	@Test //5 
	public void testThatWhenIAddAOrderToListThenUpdateTheOrderThatTheOrderHasBeenUpdatedWithNewCustomerUsername() {
		OrderDAO orderDAO = new OrderDAO();
		CustomerDAO customerDAO = new CustomerDAO();
		orderDAO.setEntityManager(entityManager);
		customerDAO.setEntityManager(entityManager);
		
		
		
		Customer customer = new Customer();
		customer.setUsername("user1");
		customerDAO.addCustomer(customer);
		
		Order order = new Order();
		order.setCustomer(customer);
		order.setOrderId(101);
		orderDAO.addOrder(order);
		
		
		Customer newCustomer = new Customer();
		newCustomer.setUsername("userone");
		customerDAO.addCustomer(newCustomer);
		
		Order newOrder = new Order();
		order.setCustomer(newCustomer);
		newOrder.setOrderId(101);
		orderDAO.addOrder(order);
		
		
		
		Order orderInDB = orderDAO.getOrder(101);
		String username = orderInDB.getCustomer().getUsername();
		assertEquals("userone", username);
	}
	
	
	/*@Test //6
	public void testThat() {
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.setEntityManager(entityManager);
		
		Order marchOrder = new Order();
		marchOrder.setOrderId(101);
		
		orderDAO.addOrder(marchOrder);
		
		Order aprilOrder = new Order();
		aprilOrder.setOrderId(102);
		aprilOrder.setOrderDate(LocalDate.of(2020, 4, 17));
		orderDAO.addOrder(aprilOrder);
		
		Order mayOrder = new Order();
		mayOrder.setOrderId(103);
		mayOrder.setOrderDate(LocalDate.of(2020, 4, 17));
		orderDAO.addOrder(mayOrder);
		
		List<Order> listOfOrders = OrderDAO.listOrdersFromDateRnage(LocalDate.of(2020, 3, 1),LocalDate.of(2020, 4 , 30));
		int size = listOfOrders.size();
		assertEquals(2, size);
	
	}*/
	
	
	@Test
	public void testThatWhenIAddOrderAndUseListOrdersByUsernameThanAllOrdersFromCustomerAreReturned() {
		OrderDAO orderDAO = new OrderDAO();
		CustomerDAO customerDAO = new CustomerDAO();
		orderDAO.setEntityManager(entityManager);
		customerDAO.setEntityManager(entityManager);
		
		
		
		Customer customer = new Customer();
		customer.setUsername("user1");
		customerDAO.addCustomer(customer);
		
		Order order = new Order();
		order.setCustomer(customer);
		order.setOrderId(101);
		orderDAO.addOrder(order);
		
		
		Customer newCustomer = new Customer();
		newCustomer.setUsername("userone");
		customerDAO.addCustomer(newCustomer);
		
		Order newOrder = new Order();
		newOrder.setCustomer(newCustomer);
		newOrder.setOrderId(102);
		orderDAO.addOrder(newOrder);
		
		Order newOrder2 = new Order();
		newOrder2.setCustomer(newCustomer);
		newOrder2.setOrderId(103);
		orderDAO.addOrder(newOrder2);
		
		
		List<Order> listOfOrders = OrderDAO.listOrdersByUsername("userone");
		int size = listOfOrders.size();
		
		
		assertEquals(2, size);
	}
}

