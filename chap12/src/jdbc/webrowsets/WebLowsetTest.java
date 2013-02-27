package jdbc.webrowsets;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.rowset.WebRowSet;

import utils.PropReader;

import jdbc.listener.JRowsetListener;

import Connections.PoolConnection;

import com.sun.rowset.WebRowSetImpl;

public class WebLowsetTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		 System.out.println( PropReader.getInstance().getProperty("username") );
		 System.out.println( PropReader.getInstance().getProperty("url") );
		   
		   String url 		= PropReader.getInstance().getProperty("url");
		   String userId 	= PropReader.getInstance().getProperty("username");
		   String password 	= PropReader.getInstance().getProperty("password");
	
	
		Connection conn = null;
		//PoolConnection jinconn = new PoolConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String FoodListFrm = "foodList.xml";
		int[] keycols = {1};
		String sql = "SELECT * from food";

		
		WebRowSet wrs = new WebRowSetImpl();
		wrs.setReadOnly(false);
		wrs.setUsername(userId);
		wrs.setPassword(password);
		wrs.setUrl(url);
		wrs.setCommand(sql);
		wrs.setKeyColumns(keycols);
		wrs.execute(conn);
		

		wrs.moveToInsertRow();
		wrs.updateString("food_NAME", "감자");
		wrs.updateInt("PRICE", 15000);
		wrs.insertRow();
		wrs.moveToCurrentRow();
		
		wrs.beforeFirst();
		while(wrs.next()){
			if( wrs.getString(2).equalsIgnoreCase("닭가슴살")){
	    		wrs.updateInt(3, 33000);
	    		wrs.updateRow();
	    		break;
	    	}
		}

	
	    

	    wrs.beforeFirst();
	    while( wrs.next() ){
	    	if( wrs.getString(2).equalsIgnoreCase("햄버거")){
	    		wrs.deleteRow();
	    		break;
	    	}
	    }

	
	    
	   
		try{
			wrs.writeXml( new FileOutputStream(FoodListFrm) );
			
		
			wrs.close();
			//conn.close();
		}catch(FileNotFoundException fe ){
			fe.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

}
