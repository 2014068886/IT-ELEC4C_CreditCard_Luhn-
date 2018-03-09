package ust.gas.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ust.gasoline.exception.*;

import ust.session.utility.Security;

import javax.servlet.ServletContext;

public class CustomerTransaction {

	private String firstName, lastName, gasoline, creditCardNumber, payment, last4Digits, tempPayment;
	
	public String getLast4Digits() {
		return last4Digits;
	}

	public void setLast4Digits(String last4Digits) {
		this.last4Digits = last4Digits;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	private int liters;
	private double price, totalAmount, purchaseAmount;
	private double UNLEADED;
	private double DIESEL;
    private double PREMIUM;
    private double VAT;
    private double priceVAT;
    
    public double getPriceVAT() {
		return priceVAT;
	}

	public void setPriceVAT(double priceVAT) {
		this.priceVAT = priceVAT;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public double getVAT() {
		return VAT;
	}

	public void setVAT(double vAT) {
		VAT = vAT;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(double purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public double getUNLEADED() {
		return UNLEADED;
	}

	public void setUNLEADED(double uNLEADED) {
		UNLEADED = uNLEADED;
	}

	public double getDIESEL() {
		return DIESEL;
	}

	public void setDIESEL(double dIESEL) {
		DIESEL = dIESEL;
	}

	public double getPREMIUM() {
		return PREMIUM;
	}

	public void setPREMIUM(double pREMIUM) {
		PREMIUM = pREMIUM;
	}

	private ServletContext getServletContext;
	
	public CustomerTransaction() {
		
	}
	
	public CustomerTransaction(String fname, String lname, String gas, int litro, String bayad, String creditCardNumber, double unleaded, double diesel, double premium, double vat, ServletContext servletContext) {
		this.firstName = fname;
		this.lastName = lname;
		this.gasoline = gas;
		this.payment = bayad;
		this.liters = litro;
		this.creditCardNumber = creditCardNumber;
		this.UNLEADED = unleaded;
		this.DIESEL = diesel;
		this.PREMIUM = premium;
		this.VAT = vat;
		this.getServletContext = servletContext;
	}
	
	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGasoline() {
		return gasoline;
	}
	public void setGasoline(String gasoline) {
		this.gasoline = gasoline;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public int getLiters() {
		return liters;
	}
	public void setLiters(int liters) {
		this.liters = liters;
	}
	
	public void processGasoline(){
		
		getServletContext.log("Buying Gasoline");
		switch(this.gasoline){
			case "UNLEADED":
				this.price = this.UNLEADED;
				this.purchaseAmount = this.UNLEADED * this.liters;
				this.priceVAT = this.purchaseAmount * this.VAT;
				this.totalAmount = this.purchaseAmount + this.priceVAT;
				
				getServletContext.log("Liters:" + this.liters);
				getServletContext.log("Purchase Amount:" + this.purchaseAmount);
				getServletContext.log("VAT:" + this.priceVAT);
				getServletContext.log("Total Amount:" + this.totalAmount);
				break;
			case "DIESEL":
				this.price = this.DIESEL;
				this.purchaseAmount = this.DIESEL * this.liters;
				this.priceVAT = this.purchaseAmount * this.VAT;
				this.totalAmount = this.purchaseAmount + this.priceVAT;
				
				getServletContext.log("Liters:" + this.liters);
				getServletContext.log("Purchase Amount:" + this.purchaseAmount);
				getServletContext.log("VAT:" + this.priceVAT);
				getServletContext.log("Total Amount:" + this.totalAmount);
				
				break;
			case "PREMIUM":
				this.price = this.PREMIUM;
				this.purchaseAmount = this.PREMIUM * this.liters;
				this.priceVAT = this.purchaseAmount * this.VAT;
				this.totalAmount = this.purchaseAmount + this.priceVAT;
				
				getServletContext.log("Liters:" + this.liters);
				getServletContext.log("Purchase Amount:" + this.PREMIUM * this.liters);
				getServletContext.log("VAT:" + this.purchaseAmount * this.VAT);
				getServletContext.log("Total Amount:" + this.purchaseAmount + this.priceVAT);
				break;	
		}
	}
	
	public boolean checkCreditCard() {
		if(luhnTest(this.creditCardNumber) == true) {
			this.last4Digits = this.creditCardNumber.replaceAll("\\w(?=\\w{4})", "*");
			//this.last4Digits = getLastCharacters(this.creditCardNumber, 4);
			getServletContext.log("Last4Digits:" + last4Digits);
			return true;
		} else if(luhnTest(this.creditCardNumber) == false) {
			getServletContext.log("Error");
			return false;
		} else {
			return false;
		}
	
	}
	

	public String getTempPayment() {
		return tempPayment;
	}

	public void setTempPayment(String tempPayment) {
		this.tempPayment = tempPayment;
	}
   
	public void insertRecords() {
	
		String amountTotal = String.valueOf(this.totalAmount);
		try {
			Connection connection = (Connection) this.getServletContext.getAttribute("dbconn");
			
			if (connection !=null){
				String sql = "insert into customerpurchase" + "(name, liters, price, gas, purchaseAmount, vat, totalAmount, payment, creditCardNum)" +
			"values (?,?,?,?,?,?,?,?,?)";
				PreparedStatement pstmt = connection.prepareStatement(sql);
				
				pstmt.setString(1, Security.encrypt(this.firstName + " " + this.lastName));
				pstmt.setInt(2, this.liters);
				pstmt.setDouble(3, this.price);
				pstmt.setString(4, this.gasoline);
				pstmt.setDouble(5, this.purchaseAmount);
				pstmt.setDouble(6, this.priceVAT);
				pstmt.setString(7, amountTotal);
				pstmt.setString(8, this.payment);
				pstmt.setString(9, Security.encrypt(this.creditCardNumber));
				
				pstmt.executeUpdate();
				
				getServletContext.log("record successfully inserted");
			} else {
				System.err.println("connection is null");
			}
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
		}
	}
	
	public void insertAuditTrail(){
		try {
		Connection connection = (Connection) this.getServletContext.getAttribute("dbconn");
		
			if (connection !=null){
				String sql = "insert into logaudit (event, eventDateTime) values (?,?)";
				PreparedStatement pstmt = connection.prepareStatement(sql);
				
				String message = this.firstName + " " + "buys" + " " + this.gasoline;
				pstmt.setString(1, Security.encrypt(message));
				
				 
				pstmt.setString(2, Security.encrypt(new java.util.Date().toString()));
			
				pstmt.executeUpdate();
			
				getServletContext.log("record successfully inserted");
			
				} else {
					System.err.println("Connection is null");
				}	
		} catch (SQLException sqle){
			System.err.println(sqle.getMessage());
		}
	}
	
	public static boolean luhnTest(String number){
        int s1 = 0, s2 = 0;
        String reverse = new StringBuffer(number).reverse().toString();
        for(int i = 0 ;i < reverse.length();i++){
            int digit = Character.digit(reverse.charAt(i), 10);
            if(i % 2 == 0) {
                s1 += digit;
            } else {
                s2 += 2 * digit;
                if(digit >= 5){
                    s2 -= 9;
                }
            }
        }
        return (s1 + s2) % 10 == 0;
    }
	
	public String getLastCharacters(String inputString, int subStringLength) {
		int length = inputString.length();
		if (length <= subStringLength) {
			return inputString;
		}

		int startIndex = length - subStringLength;
		return inputString.substring(startIndex);
	}
	
	private static String getDigitsOnly(String s) {
		StringBuffer digitsOnly = new StringBuffer();
		char c;
		for (int i = 0; i < s.length(); i++) {
			c = s.charAt(i); // returns the char value
			if (Character.isDigit(c)) // this checks if a character has a digit
										// value
			{
				digitsOnly.append(c); // it outputs all the digits
			}
		}
		return digitsOnly.toString();
	}
	
}
