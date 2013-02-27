package jdbc.listener;

import javax.sql.ConnectionEvent;
import javax.sql.ConnectionEventListener;

public class ConnectEventListener implements ConnectionEventListener{
	
	public ConnectEventListener() {	
	}
	@Override
	public void connectionClosed(ConnectionEvent event) {
		System.out.println("conn close event check");
	}
	@Override
	public void connectionErrorOccurred(ConnectionEvent event) {
		System.out.println("conn error event check");
		
	}

}
