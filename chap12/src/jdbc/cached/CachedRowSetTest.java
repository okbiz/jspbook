package jdbc.cached;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;



import com.sun.rowset.CachedRowSetImpl;

import utils.PropReader;

public class CachedRowSetTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		   System.out.println( PropReader.getInstance().getProperty("username") );
		   System.out.println( PropReader.getInstance().getProperty("url") );
		   
		   String url 		= PropReader.getInstance().getProperty("url");
		   String userId 	= PropReader.getInstance().getProperty("username");
		   String password 	= PropReader.getInstance().getProperty("password");
		   String sql = "select * from food";
		   Connection conn = null;
		  try{
		    conn = DriverManager.getConnection(url, userId, password);
			conn.setAutoCommit(false);
					
			CachedRowSet crs = new CachedRowSetImpl();
			crs.setUsername(userId);
			crs.setPassword(password);
			crs.setUrl(url);
			crs.setCommand(sql);
			crs.setPageSize( 100 );
			crs.execute();
			crs.addRowSetListener( new jdbc.listener.JRowsetListener() );
			
			
			//insert
			//crs.moveToInsertRow();
			//crs.updateString(3, "콜라");
			//crs.insertRow();
			
			//crs.moveToCurrentRow();
			//crs.acceptChanges(conn);
			
			int i = 1;
			do{
				System.out.println("page num" + 1);
			while( crs.next()){
				System.out.println("foodid :" + 	 crs.getInt("FOOD_ID"));
				System.out.println("foodname" +  	 crs.getString("FOOD_NAME"));
			}
			 i++;
		}while(crs.nextPage());
		}catch(SQLException se){
			
		}

	}

}
