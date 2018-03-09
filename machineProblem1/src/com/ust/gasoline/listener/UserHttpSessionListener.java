package com.ust.gasoline.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class UserHttpSessionListener implements HttpSessionListener {

	public static int userCount = 0;
	
	@Override
	public void sessionCreated(HttpSessionEvent hse) {
		if (hse.getSession().isNew()) {
			System.out.println("No session has been defined");
		} 
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent hse) {
		System.out.println("A session has been destroyed.");
		UserHttpSessionListener.userCount--;
		System.out.println("Total session count: " + UserHttpSessionListener.userCount);
	}
}
