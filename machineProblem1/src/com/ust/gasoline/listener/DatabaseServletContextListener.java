package com.ust.gasoline.listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.*;

@WebListener
public class DatabaseServletContextListener implements ServletContextListener {

	private Connection connection = null;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("inside ci");
		
		try {
			ServletContext context = sce.getServletContext();
			Class.forName(context.getInitParameter("jdbcDriver"));
		    connection = DriverManager.getConnection
					(context.getInitParameter(("jdbcUrl")), context.getInitParameter(("dbUserName")),context.getInitParameter(("dbPassword")));
			
			System.out.println("Driver: " + context.getInitParameter(("jdbcDriver")));
			System.out.println("JDBC URL: " + context.getInitParameter(("jdbcUrl")));
			System.out.println("DB Username: " + context.getInitParameter(("dbUserName")));
			System.out.println("DB Password: " + context.getInitParameter(("dbPassword")));
			
			context.setAttribute("dbconn", connection);
			if (connection !=null ){
				System.out.println("Connection is initialized");
				;
			} else {
				System.out.println("Connection is null");
			}
		} catch (ClassNotFoundException cfne){
			System.err.println(cfne.getMessage());
			
		} catch (SQLException sqle){
			System.err.println(sqle.getMessage());
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		if(connection != null){
			try {
				connection.close();
				//connection = null;
				System.out.println("Connection object destroyed.");
			} catch (SQLException sqle){
				System.err.println(sqle.getMessage());
			}
		}
	}
}
