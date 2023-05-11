package com.fdmgroup.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.daos.CustomerDAO;
import com.fdmgroup.daos.OrderDAO;
import com.fdmgroup.daos.PaymentDAO;
import com.fdmgroup.daos.ProductDAO;
import com.fdmgroup.entities.Customer;
import com.fdmgroup.entities.Order;
import com.fdmgroup.entities.Payment;
import com.fdmgroup.entities.Product;

@Controller
public class HomeController {

	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soloprojectweek6");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();

	@RequestMapping(value = "/")
	public String homePageHandler(Model model) {

		return "welcome";
	}

	@RequestMapping(value = "welcome")
	public String welcomePageHandler(Model model) {

		return "welcome";
	}

	@RequestMapping(value = "register")
	public String registrationHandler(Model model) {

		model.addAttribute("customer", new Customer());

		return "register";
	}

	@RequestMapping(value = "customerDetails")
	public String customerDetailsPageHandler(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		model.addAttribute("customer", new Customer());
		
		if (null == session.getAttribute("username")) {
			request.setAttribute("message3", "You need to log in to see your account details");
			return "login";
		}
		String username = (String) session.getAttribute("username");
		CustomerDAO customerDAO = new CustomerDAO();
		customerDAO.setEntityManager(entityManager);
		model.addAttribute("customer", customerDAO.getCustomer(username));

		return "customerDetailsPage";

	}

	@RequestMapping(value = "submitRegistration", method = POST)
	public String submitRegistrationHandler(Model model, Customer customer, HttpServletRequest request) {

		model.addAttribute("customer", customer);

		CustomerDAO customerDAO = new CustomerDAO();
		customerDAO.setEntityManager(entityManager);
		String username = customer.getUsername();

		Customer customerInDB = customerDAO.getCustomer(username);

		if (customerInDB != null) {

			model.addAttribute("customer", new Customer());
			request.setAttribute("message1", "Username already in use. Try different username or go to login page");
			return "register";
		}

		customerDAO.addCustomer(customer);
		model.addAttribute("customer", new Customer());
		request.setAttribute("message2", "registration succesfull");

		return "login";
	}

	@RequestMapping(value = "logout")
	public String logout(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		session.invalidate();
		model.addAttribute("customer", new Customer());
		return "login";
	}

	@RequestMapping(value = "login")
	public String goToLoginPage(Model model, HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession(false);
		model.addAttribute("customer", new Customer());

		if (null != session.getAttribute("username")) {
			return "login";
		}
		return "login";
	}

	@RequestMapping(value = "submitLogin", method = POST)
	public String loginSubmitHandler(Model model, Customer customer, HttpServletRequest request) {

		String username = customer.getUsername();
		String password = customer.getPassword();
		CustomerDAO customerDAO = new CustomerDAO();
		customerDAO.setEntityManager(entityManager);
		Customer customerInDB = customerDAO.getCustomer(username);

		if (customerInDB == null) {

			model.addAttribute("customer", new Customer());
			request.setAttribute("message1", "Wrong login");
			return "login";
		}

		String passwordInDB = customerInDB.getPassword();

		if (!passwordInDB.equals(password)) {

			model.addAttribute("customer", new Customer());
			request.setAttribute("message2", "Wrong password");
			return "login";

		} else {
			HttpSession session = request.getSession(true);
			session.setAttribute("username", username);
			request.setAttribute("message", "Now you are loged in correctly");
			
			
			
			return "customerDetailsPage";
		}
	}

	@RequestMapping(value = "listOfCustomers")
	public String listCustomers(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);

		if (null == session.getAttribute("username")) {
			request.setAttribute("message3", "You need to log in to see your account details");
			return "login";
		}

		CustomerDAO customerDAO = new CustomerDAO();
		customerDAO.setEntityManager(entityManager);
		String username = (String) (session.getAttribute("username"));

		Customer customer = customerDAO.getCustomer(username);

		List<Customer> listOfCustomerDetails = customerDAO.listCustomers();

		String jpql = "Select c from Customer c where c.username=:username";
		TypedQuery<Customer> queryCustomers = entityManager.createQuery(jpql, Customer.class);
		queryCustomers.setParameter("username", username);
		listOfCustomerDetails = queryCustomers.getResultList();
		model.addAttribute("listOfCustomerDetails", listOfCustomerDetails);
		model.addAttribute("customer", customer);

		return "listOfCustomersPage";
	}

	@RequestMapping(value = "listOfProducts")
	public String listProducts(Model model) {

		ProductDAO productDAO = new ProductDAO();
		productDAO.setEntityManager(entityManager);

		List<Product> listOfProducts = productDAO.listProducts();
		model.addAttribute("listOfProducts", listOfProducts);

		return "listOfProductsPage";
	}

	private HashMap<Product, Integer> basket = new HashMap<>();

	@RequestMapping("basket")
	public String basketHandler(Model model) {
		model.addAttribute("basket", basket);
		return "basket";
	}

	@RequestMapping(value = "addProductToBasket/{productId}")
	public String addProduct(@PathVariable int productId, Model model, HttpServletRequest request) {


		ProductDAO productDAO = new ProductDAO();
		productDAO.setEntityManager(entityManager);
		Product productInDB = productDAO.getProduct(productId);
		String nameOfProductInDB = productInDB.getProductName();
		boolean exists = basket.containsKey(productInDB);

		if (exists == true) {
			int quantity = basket.get(productInDB);
			basket.put(productInDB, quantity + 1);
		} else {
			basket.put(productInDB, 1);
		}
		request.setAttribute("message", nameOfProductInDB + "  was added to basket");
		model.addAttribute("basket", basket);

		return "basket";
	}

	@RequestMapping(value = "clearTheBasket", method = POST)
	public String clearBasket(Model model) {

		basket.clear();
		model.addAttribute("basket", basket);
		return "basket";
	}

	@RequestMapping(value = "placeOrder")
	public String placeOrderHandler(HttpSession session) {

		String username = (String) session.getAttribute("username");
		CustomerDAO customerDAO = new CustomerDAO();
		customerDAO.setEntityManager(entityManager);

		OrderDAO orderDAO = new OrderDAO();
		orderDAO.setEntityManager(entityManager);
		Order order = new Order();

		order.setCustomer(customerDAO.getCustomer(username));
		order.setOrderDate(LocalDate.now());

		orderDAO.addOrder(order);

		basket.clear();

		return "orderConfirmed";

	}

	@RequestMapping(value = "clearBasket")
	public String clearBasket() {

		basket.clear();

		return "basket";

	}

	@RequestMapping(value = "listOfOrders")
	public String listOrders(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		model.addAttribute("customer", new Customer());

		if (null == session.getAttribute("username")) {
			request.setAttribute("message3", "You need to log in to see orders history");
			System.out.println("you need to log in ");
			return "login";
		}

		else {

			OrderDAO orderDAO = new OrderDAO();
			orderDAO.setEntityManager(entityManager);
			String username = (String) session.getAttribute("username");
			List<Order> listOrdersByUsername = orderDAO.listOrders();
			String jpql = "Select o from Order o where o.customer.username=:username";

			TypedQuery<Order> queryOrders = entityManager.createQuery(jpql, Order.class);
			queryOrders.setParameter("username", username);
			listOrdersByUsername = queryOrders.getResultList();
			model.addAttribute("listOfCustomerOrders", listOrdersByUsername);
		}

		return "listOfOrdersPage";
	}

	@RequestMapping(value = "listOfPayments")
	public String listPayments(Model model, HttpServletRequest request) {


		HttpSession session = request.getSession(false);
		model.addAttribute("customer", new Customer());

		if (null == session.getAttribute("username")) {
			request.setAttribute("message3", "You need to log in to see payments history");
			return "login";
		}

		else {

			PaymentDAO paymentDAO = new PaymentDAO();
			paymentDAO.setEntityManager(entityManager);
			String username = (String) session.getAttribute("username");
			List<Payment> listOfCustomerPayments = paymentDAO.listPayments();
			String jpql = "Select p from Payment p where p.order.customer.username=:username";

			TypedQuery<Payment> queryPayments = entityManager.createQuery(jpql, Payment.class);
			queryPayments.setParameter("username", username);
			listOfCustomerPayments = queryPayments.getResultList();
			model.addAttribute("listOfCustomerPayments", listOfCustomerPayments);
			System.out.println("you are loged in. check your payments history ");
		}

		return "listOfPaymentsPage";
	}
}
