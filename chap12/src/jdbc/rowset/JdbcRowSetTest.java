package jdbc.rowset;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.rowset.JdbcRowSet;

import utils.PropReader;

import com.sun.rowset.JdbcRowSetImpl;

public class JdbcRowSetTest {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws SQLException {
	   System.out.println( PropReader.getInstance().getProperty("username") );
	   System.out.println( PropReader.getInstance().getProperty("url") );
	   
	   String url 		= PropReader.getInstance().getProperty("url");
	   String userId 	= PropReader.getInstance().getProperty("username");
	   String password 	= PropReader.getInstance().getProperty("password");
	   
	   Statement stmt = null;
	   Connection conn = DriverManager.getConnection( url, userId, password );
	   stmt = conn.createStatement();

	   JdbcRowSet rowset = new JdbcRowSetImpl();
		rowset.setUrl(url);
        rowset.setUsername(userId);
        rowset.setPassword(password);
        rowset.setCommand( "SELECT * FROM FOOD ");
        rowset.execute();
       
        while(rowset.next()){
        	System.out.println(  
        	 rowset.getRow() +"" 
        	+rowset.getInt("FOOD_ID") + " : " + 
        	 rowset.getString("food_name") + " : " +
        	 rowset.getString("price") );
        }
        
		/*
		DatabaseMetaData dbm = conn.getMetaData();
		
		stmt = conn.createStatement();
		conn.setAutoCommit(false);
		
		
		
		JdbcRowSet rowset = new JdbcRowSetImpl();
		rowset.setUrl(url);
        rowset.setUsername(userId);
        rowset.setPassword(password);
        rowset.setCommand( "SELECT * FROM FOOD ");
        rowset.execute();
        
        
        while(rowset.next()){
        	System.out.println(  
        	 rowset.getRow() +"" 
        	+rowset.getInt("FOOD_ID") + " : " + 
        	 rowset.getString("food_name") + " : " +
        	 rowset.getString("price") );
        }
        
        
    	//updater
        rowset.clearWarnings();
        rowset.beforeFirst();
        rowset.absolute(4);
        rowset.updateInt("PRICE", 3200);
        rowset.updateRow();
        
        rowset.commit();
        
        while(rowset.next()){
        	System.out.println(  
        	 rowset.getRow() +"" 
        	+rowset.getInt("FOOD_ID") + " : " + 
        	 rowset.getString("food_name") + " : " +
        	 rowset.getString("price") );

        }
        */
	}
	

	}


