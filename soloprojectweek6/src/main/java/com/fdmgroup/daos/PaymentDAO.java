package com.fdmgroup.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.fdmgroup.entities.Payment;

public class PaymentDAO {

	private static EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		PaymentDAO.entityManager = entityManager;
	}

	public List<Payment> listPayments() {
		TypedQuery<Payment> queryPayment = entityManager.createQuery("Select pay from Payment pay", Payment.class);
		List<Payment> listOfPayments = queryPayment.getResultList();
		return listOfPayments;
	}

	public void addPayment(Payment newPayment) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(newPayment);
		entityTransaction.commit();
	}

	public Payment getPayment(int paymentId) {
		Payment payment = entityManager.find(Payment.class, paymentId);
		return payment;
	}

	public void removePayment(int paymentId) {
		Payment payment = entityManager.find(Payment.class, paymentId);
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.remove(payment);
		entityTransaction.commit();
	}

	public void updatePayment(int paymentId) {
		Payment payment = entityManager.find(Payment.class, paymentId);
		EntityTransaction  entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.merge(payment);
        entityTransaction.commit();
	}

}
