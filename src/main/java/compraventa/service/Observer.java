package compraventa.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Observer {
	List<Listener> listeners = new ArrayList<Listener>();
	
	
	public void listen(Listener listener) {
		listeners.add(listener);
	}
	
	public void emit() {
		for(Listener l: listeners) {
			l.handleEvent();
		}
	}
	
	
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("y-M-d");
		try {
			System.out.println(sdf.parse("2017-03-05"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Observer observer = new Observer();
		
		observer.listen(new Listener() {
			
			@Override
			public void handleEvent() {
				System.out.println("listener numero 1");				
			}
		});
		
		observer.listen(new Listener() {
			
			@Override
			public void handleEvent() {
				System.out.println("listener numero 2");				
			}
		});
		
		observer.emit();
	}
}
