package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Properties;

public class PropReader {

	private static Properties dbPropReader = null;
	
	private PropReader() {}
	
	static{
		if( dbPropReader == null ){
			dbPropReader = new Properties();
			InputStream in = Properties.class.getResourceAsStream("/mysql.properties");
			try{
				dbPropReader.load(in);
				in.close();
			}catch(IOException e){
				System.out.println( "Fail" + e.getMessage() );
			}
		}
	}
	
	private static Hashtable<String, String> init()throws FileNotFoundException{
		System.out.println("load database info");
		Hashtable<String, String> env = new Hashtable<>();
			env.put("username", 	dbPropReader.getProperty("username") );
			env.put("password",   	dbPropReader.getProperty("password") );
			env.put("url"     ,		dbPropReader.getProperty("url") );
		
		return env;
	}

	public static Properties getInstance(){
		if(dbPropReader == null){
			try{
				init();
			}catch(FileNotFoundException fe){
				fe.getMessage();
			}
		}
		return dbPropReader;
	}

}




