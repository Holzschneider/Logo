package de.dualuse.util.logging;

import java.io.UnsupportedEncodingException;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

class FakeRootLogger extends Logger {

	public FakeRootLogger() {
		super("", null);
		
		addHandler(new StreamHandler(System.out, new SubtleLogFormatter()));
		
		
	}
	
	
	
}

public class LoggerTest {
	
	static Logger logA = Logger.getLogger("hallo");
	static Logger logAB = Logger.getLogger("hallo.welt");
	static Logger logAC = Logger.getLogger("hallo.welten");
	
	public static void main(String[] args) throws SecurityException, UnsupportedEncodingException {
//		System.setProperty("jansi.passthrough", "true");
//		System.out.println( logAB.getParent() == logA );

//		Logger root = Logger.getLogger("");
//		root.removeHandler( root.getHandlers()[0] );
		StreamHandler sh = new StreamHandler(System.out, new SubtleLogFormatter());
//		sh.setEncoding(Charset.defaultCharset().toString());
//		sh.setEncoding("ISO_8859_1");
//		root.addHandler(sh);

		System.out.println("hallo "+SubtleLogFormatter.RED+" welt");
		System.out.println( "encoding "+sh.getEncoding() );
		
//		Logger.getLogger("").getHandlers()[0].setFormatter(new SubtleLogFormatter());
//		((ConsoleHandler)Logger.getLogger("").getHandlers()[0])
		
		logA.setParent(new FakeRootLogger());
		logA.setUseParentHandlers(false);
		logA.addHandler(sh);
		
		System.out.println(Logger.getLogger("").getHandlers().length);
		for (Handler h : logA.getHandlers()) {
			System.out.println(h);
		}
		
		Logger.getLogger("hallo.heile,welt").info("pups");;
		
		logA.info("eins");
		logAB.info("zwei");
		logAC.info("drei");
		
	}
}
