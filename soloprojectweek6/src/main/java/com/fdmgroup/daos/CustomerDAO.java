package com.fdmgroup.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.fdmgroup.entities.Customer;

public class CustomerDAO {


	private EntityManager entityManager;
	//List<Customer> listOfCustomers = new ArrayList<Customer>();
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<Customer> listCustomers() {
		TypedQuery<Customer> queryCustomer = entityManager.createQuery("Select c from Customer c", Customer.class);
		List<Customer> listOfCustomers = queryCustomer.getResultList();
		return listOfCustomers;
	}

	public void addCustomer(Customer customer) {
		EntityTransaction  entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(customer);
        entityTransaction.commit();
      // listOfCustomers.add(newCustomer);
	}

	public Customer getCustomer(String username) {
		Customer customer = entityManager.find(Customer.class, username);
		return customer;
	}


	public void updateCustomer(Customer newCustomer) {
		EntityTransaction  entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.merge(newCustomer);
        entityTransaction.commit();
		
	}
	
	public void removeCustomer(String username) {
		Customer customer = entityManager.find(Customer.class, username);
        EntityTransaction  entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.remove(customer);
        entityTransaction.commit();
        
        //do to know how to pass in a value that refers to another class
        /*Customer customer = Order.getCustomer();
        Order order = entityManager.find(Order.class, customer);
        EntityTransaction  entityTransaction1 = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.remove(order);
        entityTransaction.commit();*/

	}
}
