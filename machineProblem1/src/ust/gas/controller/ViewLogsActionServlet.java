package ust.gas.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ust.gas.model.CustomerTransaction;
import ust.gas.model.ViewLogs;

/**
 * Servlet implementation class ViewLogsActionServlet
 */
@WebServlet("/viewlogs.action")
public class ViewLogsActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	Connection connection;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.connection = (Connection) getServletContext().getAttribute("dbconn");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResultSet logRecords = new ViewLogs(this.connection, getServletConfig(), "", "").getActivityLogs();
		
		request.setAttribute("logRecord", logRecords);
		
		request.getRequestDispatcher("viewlogs.jsp").forward(request, response);
	}

}
