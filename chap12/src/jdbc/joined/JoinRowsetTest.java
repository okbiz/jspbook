package jdbc.joined;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JoinRowSet;

import utils.PropReader;

import com.sun.rowset.CachedRowSetImpl;
import com.sun.rowset.JoinRowSetImpl;

public class JoinRowsetTest {

public static void main(String[] args) throws SQLException{
		System.out.println( PropReader.getInstance().getProperty("username") );
	    System.out.println( PropReader.getInstance().getProperty("url") );
	   
	   String url 		= PropReader.getInstance().getProperty("url");
	   String userId 	= PropReader.getInstance().getProperty("username");
	   String password 	= PropReader.getInstance().getProperty("password");
		String sql1 = "select * from food";
		String sql2 = "select * from SHOP";
		
		
	
			
			CachedRowSet food = new CachedRowSetImpl();
			food.setUsername(userId);
			food.setPassword(password);
			food.setUrl(url);
			food.setCommand( "select * from food2" );
			food.execute();
						
			CachedRowSet shop = new CachedRowSetImpl();
			shop.setUsername(userId);
			shop.setPassword(password);
			shop.setUrl(url);
			shop.setCommand( "select * from SHOP2" );
			shop.execute();
			
			
			JoinRowSet jrs = new JoinRowSetImpl();	
			
			
			
			jrs.addRowSet(food, "FOOD_ID");
			jrs.addRowSet(shop, "FOOD_ID");
		

		while(jrs.next()){
			
			System.out.println( 
					jrs.getString("SHOP_NAME") +"|"  +
					jrs.getString("food_name") + "|" +
					jrs.getInt  ( "price"));  
		}
		jrs.close();

		
	}


}
