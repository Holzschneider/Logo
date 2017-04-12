package de.dualuse.util.logging;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LoggedTest implements Filtered {

	static Logger l = Logger.getLogger("test");
	static Logger n = Logger.getLogger("test.toast");
	static Logger m = Logger.getLogger("test.tast");

	public static void test() {
		System.out.println("welt");
	}
	
	public static void main(String[] args) {

//		Filtered.allow.path("**").deny.path("**.test").allow.path("**.tes?");
//		Filtered.allow.path("**");
		
//		java.lang.System.out.println("mös");
		System.out.println("hallo");
		test();
		
//		java.util.logging.*

//		LogRecord lr = new LogRecord(Level.INFO, "hallo");
//		String name = lr.getSourceClassName(); 
//		System.out.println( name );
//		
//		l.info("hallo");
		
	}
}
