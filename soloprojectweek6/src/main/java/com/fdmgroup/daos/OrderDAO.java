package com.fdmgroup.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.fdmgroup.entities.Order;

public class OrderDAO {

	private static EntityManager entityManager;
	// List<Order> listOfOrders = new ArrayList<Order>();

	public void setEntityManager(EntityManager entityManager) {
		OrderDAO.entityManager = entityManager;
	}

	public List<Order> listOrders() {
		TypedQuery<Order> queryOrder = entityManager.createQuery("Select o from Order o", Order.class);
		List<Order> listOfOrders = queryOrder.getResultList();
		return listOfOrders;
	}

	/*
	 * public static List<Order> filterByDateRange(LocalDate startDate, LocalDate
	 * endDate){ //modify Query from above to list orders in date range (needed date
	 * format conversion
	 * 
	 * List<Order> listOfOrdersFromDateRnage = null; //OR create a loop to remove
	 * items from list of products that are not in range -not effective return
	 * listOfOrdersFromDateRnage; }
	 */

	public static List<Order> listOrdersByUsername(String username) {
		String jpql = "Select o from Order o where o.customer.username=:username";
		TypedQuery<Order> queryOrders = entityManager.createQuery(jpql, Order.class);
		queryOrders.setParameter("username", username);
		List<Order> listOrders = queryOrders.getResultList();
		return listOrders;
	}

	public void addOrder(Order newOrder) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.merge(newOrder);
		entityTransaction.commit();
	}

	public Order getOrder(int orderId) {
		Order order = entityManager.find(Order.class, orderId);
		return order;
	}

	public void removeOrder(int orderId) {
		Order order = entityManager.find(Order.class, orderId);
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.remove(order);
		entityTransaction.commit();
	}

}
