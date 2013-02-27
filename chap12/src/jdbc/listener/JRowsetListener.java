package jdbc.listener;

import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;

public class JRowsetListener implements RowSetListener{

	@Override
	public void rowSetChanged(RowSetEvent event) {
		System.out.println("rowsetchange");
		
	}

	@Override
	public void rowChanged(RowSetEvent event) {
		System.out.println("rowchanged");
		
	}

	@Override
	public void cursorMoved(RowSetEvent event) {
		System.out.println("이벤트체크 cursormoved" ); 
		
	}



}
