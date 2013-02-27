package jdbc.filter;

import java.sql.SQLException;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.Predicate;

import utils.PropReader;

import com.mysql.jdbc.DatabaseMetaData;
import com.sun.rowset.FilteredRowSetImpl;

public class FoodFilter implements Predicate{
	
	private String columnName;
	
	public FoodFilter( String columnName) {
		this.columnName = columnName;
	}
	@Override
	public boolean evaluate(RowSet rs) {
		try{
			CachedRowSet crs = (CachedRowSet)rs;
			String obj = crs.getString(columnName);
			if(obj != null && (!obj.equalsIgnoreCase("햄버거") )){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			
		}
		return false;
	}
  
	public static void main(String ar[]){
		   System.out.println( PropReader.getInstance().getProperty("username") );
		   System.out.println( PropReader.getInstance().getProperty("url") );
		   
		   String url 		= PropReader.getInstance().getProperty("url");
		   String userId 	= PropReader.getInstance().getProperty("username");
		   String password 	= PropReader.getInstance().getProperty("password");
		   
	
		
		try{
			FilteredRowSet  frs = new FilteredRowSetImpl();
			frs.setUsername(userId);
			frs.setPassword(password);
			frs.setUrl(url);
			frs.setCommand("SELECT * FROM FOOD");
			frs.setFilter(new FoodFilter("FOOD_NAME"));
			frs.execute();
			
			while(frs.next()){
				System.out.println("food_name:" + frs.getString("FOOD_NAME"));
			}
			
		}catch(Exception e){
			
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public boolean evaluate(Object value, int column) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean evaluate(Object value, String columnName)
			throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}


}
