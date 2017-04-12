package de.dualuse.util.logging;

public class ShadowLog {
	static int	d(String tag, String msg, Throwable tr) { return 0; }
	static int	d(String tag, String msg) { return 0; }
	static int	e(String tag, String msg) { return 0; }
	static int	e(String tag, String msg, Throwable tr) { return 0; }
	static String	getStackTraceString(Throwable tr) { return ""; }
	static int	i(String tag, String msg, Throwable tr) { return 0; }
	static int	i(String tag, String msg) { return 0; }
	static boolean	isLoggable(String tag, int level) { return false; }
	static int	println(int priority, String tag, String msg) { return 0; }
	static int	v(String tag, String msg) { return 0; }
	static int	v(String tag, String msg, Throwable tr) { return 0; }
	static int	w(String tag, Throwable tr) { return 0; }
	static int	w(String tag, String msg, Throwable tr) { return 0; }
	static int	w(String tag, String msg) { return 0; }
	static int	wtf(String tag, String msg) { return 0; }
	static int	wtf(String tag, Throwable tr) { return 0; }
	static int	wtf(String tag, String msg, Throwable tr) { return 0; }
}
