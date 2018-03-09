package ust.gas.controller;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ust.gasoline.exception.InvalidCreditCardException;
import ust.gas.model.CustomerTransaction;

import javax.servlet.*;


public class BuyStocksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//create instance variables that will contain values from the web.xml
	private double UNLEADED;
	private double DIESEL;
	private double PREMIUM;
	private double VAT;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		UNLEADED = Double.parseDouble(config.getInitParameter("UNLEADED"));
		DIESEL = Double.parseDouble(config.getInitParameter("DIESEL"));
		PREMIUM = Double.parseDouble(config.getInitParameter("PREMIUM"));
		VAT = Double.parseDouble(config.getInitParameter("VAT"));
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String gasoline = request.getParameter("gasoline");
		int liters = Integer.parseInt(request.getParameter("liters"));
		String payment = request.getParameter("payment");
		String creditCard = request.getParameter("creditCard");
		
		//creditCard.equals("0000000000000000")
		if(creditCard.equals("0") || creditCard.equals("00")){
			try {
				throw new InvalidCreditCardException();
			} catch(InvalidCreditCardException iccne) {
				getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
			}
		} 
		
		CustomerTransaction customer = new CustomerTransaction(fname, lname, gasoline, liters, payment, creditCard, UNLEADED, DIESEL, PREMIUM, VAT, getServletContext());
		customer.processGasoline();	
		
		if(customer.checkCreditCard()){
			customer.insertRecords();
			customer.insertAuditTrail();
			getServletContext().log("Successful purchase");
			request.setAttribute("customer", customer);
			getServletContext().getRequestDispatcher("/display.jsp").forward(request, response);
		} else {
				try {
					throw new InvalidCreditCardException();
				} catch(InvalidCreditCardException iccne) {
					getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
				}
				
		}
			
		
		
	}
}	
