package com.fdmgroup.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SCRunner {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soloprojectweek6");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		EntityTransaction entityTransaction = entityManager.getTransaction();

		Customer customer1 = new Customer();
		customer1.setUsername("xoxoxo13");
		customer1.setFirstName("Kasia");
		customer1.setLastName("Kowalska");
		customer1.setEmail("K.Kowalska@buziaczek.onet.pl");
		customer1.setPhoneNumber("0048666666666");
		customer1.setPassword("******");

		Customer customer2 = new Customer();
		customer2.setUsername("jestemuzytkownikiem");
		customer2.setFirstName("Jan");
		customer2.setLastName("Nowak");
		customer2.setEmail("Nowak@poczta.interia.pl");
		customer2.setPhoneNumber("004869084082");
		customer2.setPassword("haseleczko1");

		Customer customer3 = new Customer();
		customer3.setUsername("user");
		customer3.setFirstName("Name");
		customer3.setLastName("Lastname");
		customer3.setEmail("name@mail.com");
		customer3.setPhoneNumber("0048000111222");
		customer3.setPassword("password");
		
		Customer customer4 = new Customer();
		customer4.setUsername("u");
		customer4.setFirstName("N");
		customer4.setLastName("L");
		customer4.setEmail("n@m.c");
		customer4.setPhoneNumber("0048000111222");
		customer4.setPassword("p");
		
		List<Customer> mylist = new ArrayList<Customer> ();
		mylist.add(customer1);
		mylist.add(customer2);
		System.out.println("this is my list/n" + mylist);
		
		
		Order order1 = new Order();
		order1.setPrice(480.88);
		order1.setOrderDate(LocalDate.now());
		order1.setCustomer(customer1);

		Order order2 = new Order();
		order2.setPrice(5.46);
		order2.setOrderDate(LocalDate.now());
		order2.setCustomer(customer3);

		Payment payment1 = new Payment();
		payment1.setPaymentId(1);
		payment1.setAmount(4.88);
		payment1.setOrder(order1);

		Payment payment2 = new Payment();
		payment2.setPaymentId(2);
		payment2.setAmount(0.00);
		payment2.setOrder(order2);

		Product product1 = new Product();
		product1.setPrice(480.80);
		product1.setProductId(1);
		product1.setProductName("Chierowski - 366");

		Product product2 = new Product();
		product2.setPrice(300);
		product2.setProductId(2);
		product2.setProductName("Halas");

		
		
		entityTransaction.begin();

		
		Query deletePayment = entityManager.createQuery("Delete from Payment");
		deletePayment.executeUpdate();
		
		Query deleteOrder = entityManager.createQuery("Delete from Order");
		deleteOrder.executeUpdate();
		
		Query deleteCustomer = entityManager.createQuery("Delete from Customer");
		deleteCustomer.executeUpdate();
		
		Query deleteProduct = entityManager.createQuery("Delete from Product");
		deleteProduct.executeUpdate();

		entityManager.persist(product1);
		entityManager.persist(product2);

		entityManager.persist(customer1);
		entityManager.persist(customer2);
		entityManager.persist(customer3);
		entityManager.persist(customer4);
		
		entityManager.merge(order1);
		entityManager.merge(order2);
		
		entityManager.merge(payment1);
		entityManager.merge(payment2);
		

		entityTransaction.commit();

		TypedQuery<Customer> queryCustomer = entityManager.createQuery("Select c from Customer c", Customer.class);
		List<Customer> listOfCustomers = queryCustomer.getResultList();

		for (Customer eachCustomer : listOfCustomers) {
			System.out.println("Customers List" + eachCustomer.getUsername() + ", " + eachCustomer.getEmail());
		}

		TypedQuery<Payment> queryPayment = entityManager.createQuery("Select p from Payment p", Payment.class);
		List<Payment> listOfPayments = queryPayment.getResultList();

		for (Payment eachPayment : listOfPayments) {

			System.out.println("Payments List" + eachPayment.getPaymentId() + ", " + eachPayment.getAmount() + ", "
					+ eachPayment.getOrder().getOrderId());
		}

		TypedQuery<Order> queryOrder = entityManager.createQuery("Select o from Order o", Order.class);
		List<Order> listOfOrders = queryOrder.getResultList();

		for (Order eachOrder : listOfOrders) {

			System.out.println("Orders Lists" + eachOrder.getOrderId() + ", " + eachOrder.getOrderDate() + ", "
					+ eachOrder.getCustomer().getUsername());
		}

		TypedQuery<Product> queryProduct = entityManager.createQuery("Select pr from Product pr", Product.class);
		List<Product> listOfProducts = queryProduct.getResultList();

		for (Product eachProduct : listOfProducts) {

			System.out.println(eachProduct.getProductName() + ", " + eachProduct.getPrice());
		}

		entityManager.close();
		
	}

}
