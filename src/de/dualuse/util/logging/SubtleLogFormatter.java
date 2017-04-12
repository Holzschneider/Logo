package de.dualuse.util.logging;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class SubtleLogFormatter extends Formatter {
	public static final String ITALIC = "\u001B[3m";
	public static final String RED = "\u001B[31m";

	DateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm.SSS");
	
	
	@Override
	public String format(LogRecord record) {
		
		
		return format.format(record.getMillis())+": "+record.getMessage()+"     \t("+record.getSourceClassName()+"."+record.getSourceMethodName()+")"+"\n";
	}

}
