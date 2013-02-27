package Connections;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLWarning;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.ConnectionEvent;
import javax.sql.ConnectionEventListener;
import javax.sql.DataSource;
import javax.sql.PooledConnection;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import utils.PropReader;

import jdbc.listener.ConnectEventListener;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

public class PoolConnection {
	Connection conn = null;
	//OracleConnectionPoolDataSource ds = new OracleConnectionPoolDataSource();
	
	public static void main(String[] args){
		   System.out.println( PropReader.getInstance().getProperty("username") );
		   System.out.println( PropReader.getInstance().getProperty("url") );
		   String url 		= PropReader.getInstance().getProperty("url");
		   String userId 	= PropReader.getInstance().getProperty("username");
		   String password 	= PropReader.getInstance().getProperty("password");
		   
		
		
		PooledConnection conn = null;
		MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
		
		ds.setServerName("mysql_db");
		ds.setUrl(url);
		ds.setUser(userId);
		ds.setPassword(password);
		try{
			conn = ds.getPooledConnection();
			
		}catch(SQLException ex ){
			  ex.getStackTrace();
		}
		finally{
			try {
				conn.close();
				ConnectionEvent conEvent = new ConnectionEvent(conn);
				ConnectionEventListener listener = new ConnectEventListener();
				listener.connectionClosed(conEvent);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
