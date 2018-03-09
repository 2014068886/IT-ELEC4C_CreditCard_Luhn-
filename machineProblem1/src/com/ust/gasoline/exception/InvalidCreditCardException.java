package com.ust.gasoline.exception;

public class InvalidCreditCardException extends Exception implements CreditCardMessage {

	public InvalidCreditCardException()
	{
		System.err.println(INVALID_CREDITCARD);
	}
}
