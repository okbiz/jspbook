package jdbc.statements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementEx1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection conn = null;
		String url="jdbc:mysql://192.168.219.114:3306/jspbook";
		String user="root";
		String password="okpass";
		String sb = null;
		try{
			//sb = new StringBuffer();
			
			conn = DriverManager.getConnection(url, user, password);
			System.out.println( "접속성공: " + conn.isValid(1000) );
			Statement stmt = conn.createStatement();
		}catch(SQLException se){
			System.out.println( se.getSQLState()  + " : "  + se.getMessage() );
		}


	}

}
