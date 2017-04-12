package de.dualuse.util.logging;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public interface Logged {
	
	public interface Fine extends Logged { }
	public interface Info extends Logged { }
	public interface Warn extends Logged { }
	public interface Error extends Logged { }
	
	
	public static class Alert {
		Alert message(String regex) { return this; }
		Alert path(String path) { return this; }
		Alert level(Level severity) { return this; }
	}
	
	public static class Filter {
		Filter path(String path) { return this; }
		Filter message(String regex) { return this; }
		Filter level(Level severity) { return this; }
	}
	
	
	@Deprecated
	public static class System extends ShadowSystem {
		
		static public final InputStream in = java.lang.System.in;
		static public final PrintStream err = java.lang.System.err;
		static public final PrintStream out = new PrintStream(java.lang.System.out) {
			public void println(String x) {
//				LogRecord lr = new LogRecord(Level.INFO, x);
//				lr.setSourceClassName(Logged.class.getName());
//				l.log(lr);
//				l.info(x);
			};
		};
		
	}
	
	
	
}
