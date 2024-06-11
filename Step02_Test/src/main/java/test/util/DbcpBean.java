package test.util;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DbcpBean {
	private Connection conn;
	
	public DbcpBean() {
		try {
			
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
			conn = ds.getConnection();
			
			System.out.println("Connection 성공");
			
		} catch (Exception e) {
			System.out.println("ERROR");
			e.printStackTrace();
		}
	}
	
	public Connection getConn() {
		System.out.println(":)");
		return conn;
	}
}
