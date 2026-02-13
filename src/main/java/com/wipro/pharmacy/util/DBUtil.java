package com.wipro.pharmacy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	public static Connection getDBConnection() {
		Connection conn = null;
        try {
        	Class.forName("oracle.jdbc.OracleDriver");
        	String url  = "jdbc:oracle:thin:@localhost:1521:XE";
        	String user = "system";
        	String pass = "newpass";
        	Connection con = DriverManager.getConnection(url, user, pass);
            return con;
        }catch (ClassNotFoundException|SQLException e){
        	e.printStackTrace();
        	return null;
        }
	}
}
