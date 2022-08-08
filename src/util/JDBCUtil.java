package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


public class JDBCUtil {

	public static Connection getConnection() {
		// TODO Auto-generated method stub
		Connection con = null;
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/wifi?characterEncoding=utf8&useSSL=false";  
		String user = "root"; 
		String pw = "root"; 
		try {
			Class.forName(driver);
			try {
				con = DriverManager.getConnection(url, user, pw);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		
		
		return con;
	}
	
	public static void close(Connection con, Statement st, ResultSet rs) {
		
		try {
			if(con != null) con.close();
			if(st != null) st.close();
			if(rs != null) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
