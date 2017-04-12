package de.dualuse.util.logging;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public interface Silenced {

	@Deprecated
	public static class System extends ShadowSystem {
		@Deprecated
		static final private OutputStream silentStream = new OutputStream() {
			StringBuilder sb = new StringBuilder();
			public void write(int b) throws IOException { }
			public void write(byte[] b) throws IOException {  }
			public void write(byte[] b, int off, int len) throws IOException {  }
		};
		
		@Deprecated
		static public final PrintStream out = new PrintStream(silentStream,true);
		@Deprecated
		static public final PrintStream err = new PrintStream(silentStream,true);

		@Deprecated
		static public final InputStream in = System.in;
	}
	
	
	@Deprecated
	public static class Log extends ShadowLog {
		
	}
	
}
