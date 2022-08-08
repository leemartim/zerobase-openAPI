package util;

import java.sql.Connection;

public class JDBCTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Connection test");
		Connection con = JDBCUtil.getConnection();
		System.out.println(con + "con");
		JDBCUtil.close(con, null, null);
		
	}
}
