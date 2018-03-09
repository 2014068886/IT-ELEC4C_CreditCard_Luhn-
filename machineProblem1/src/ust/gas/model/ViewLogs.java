package ust.gas.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;

public class ViewLogs {

	private Connection connection;
	private ServletConfig config;
	
	public ViewLogs(){
		
	}
	
	public ViewLogs(Connection connection, ServletConfig config,
			String ... value){
		this.connection = connection;
		this.config = config;
		
	}
	
	public ResultSet getActivityLogs() {
		ResultSet logs = null;
		
		String sql = "select * from logaudit";
		
		try {
			PreparedStatement pstmnt = this.connection.prepareStatement(sql);
			logs = pstmnt.executeQuery();
			
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
		}
		
		return logs;
	}

}
