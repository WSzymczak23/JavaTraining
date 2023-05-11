package com.fdmgroup.soloproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fdmgroup.soloprojectDAO.OrderDAO;
import com.fdmgroup.soloprojectDAO.PaymentDAO;

public class PaymentDAOtest {

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
	public void testThatWhenICallTheListPaymentsMethodInPaymentDAOItReturnsAnEmptyList() {
		PaymentDAO paymentDAO = new PaymentDAO();
		paymentDAO.setEntityManager(entityManager);

		List<Payment> listPayments = paymentDAO.listPayments();
		int size = listPayments.size();
		assertEquals(0, size);
	}

	@Test // 2
	public void testThatWhenIAddPaymentToTheListThatTheListHasOnePayment() {
		PaymentDAO paymentDAO = new PaymentDAO();
		paymentDAO.setEntityManager(entityManager);

		Payment payment = new Payment();
		payment.setPaymentId(101);
		paymentDAO.addPayment(payment);

		List<Payment> listPayments = paymentDAO.listPayments();
		int size = listPayments.size();
		assertEquals(1, size);

	}

	@Test // 3
	public void testThatWhenIAddPaymentToTheListThatWhenICallGetMethodItReturnsThePayment() {
		PaymentDAO paymentDAO = new PaymentDAO();
		paymentDAO.setEntityManager(entityManager);

		Payment payment = new Payment();
		payment.setPaymentId(101);
		paymentDAO.addPayment(payment);

		Payment paymentInDB = paymentDAO.getPayment(101);
		int paymentIdInDB = paymentInDB.getPaymentId();
		assertEquals(101, paymentIdInDB);
	}

	@Test // 4
	public void testThatWhenIAddPaymentThenRemoveThePaymentTheListIsEmpty() {
		PaymentDAO paymentDAO = new PaymentDAO();
		paymentDAO.setEntityManager(entityManager);

		Payment payment = new Payment();
		payment.setPaymentId(101);
		paymentDAO.addPayment(payment);
		paymentDAO.removePayment(101);

		List<Payment> listPayments = paymentDAO.listPayments();
		int size = listPayments.size();
		assertEquals(0, size);
	}

	@Test // 5
	public void testThatWhenIAddAPaymentToListThenUpdateThePaymentThatThePaymentHasBeenUpdated() {
		PaymentDAO paymentDAO = new PaymentDAO();
		paymentDAO.setEntityManager(entityManager);
		
		Payment payment = new Payment();
		payment.setPaymentId(101);
		payment.setAmount(500.00);
		paymentDAO.addPayment(payment);

		Payment newPayment = new Payment();
		newPayment.setPaymentId(101);
		newPayment.setAmount(1500.00);
		paymentDAO.updatePayment(101);

		Payment paymentInDB = paymentDAO.getPayment(101);
		int paymentIdInDB = paymentInDB.getPaymentId();
		assertEquals(101, paymentIdInDB);
	}

	@Test // 6
	public void testThatWhenICreatePaymentAndOrderAssignedToItThenTheCorrectOrdeIdIsDisplayed() {
		PaymentDAO paymentDAO = new PaymentDAO();
		paymentDAO.setEntityManager(entityManager);
		
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.setEntityManager(entityManager);
		
		
		Order order = new Order();
		order.setOrderId(101);
		orderDAO.addOrder(order);
		
		Payment payment = new Payment();
		payment.setPaymentId(101);
		payment.setAmount(500.00);
		payment.setOrder(order);
		paymentDAO.addPayment(payment);
		
		int orderIdForPaymentInDB = payment.getOrder().getOrderId();
		
		assertEquals(101, orderIdForPaymentInDB);
		
	}
}
