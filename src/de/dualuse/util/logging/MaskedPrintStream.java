package de.dualuse.util.logging;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Locale;


public abstract class MaskedPrintStream extends PrintStream {
	private boolean closed = false;
	private StringBuilder line = new StringBuilder();
	final private PrintStream pass;

	protected boolean accept(String line) {
		return false;
	}
	
	public MaskedPrintStream(PrintStream pass) {
		super(pass);
		this.pass = pass;
	}

	@Override
	public void write(int b) {
		if (closed) return;
		line.append((char)b);
	}

	@Override
	public void write(byte[] buf, int off, int len) {
		if (closed) return;
		line.append( new String(buf,off,len) );
	}

	@Override
	public void print(boolean b) {
		if (closed) return;
		line.append(b);
	}

	@Override
	public void print(char c) {
		if (closed) return;
		line.append(c);
	}

	@Override
	public void print(int i) {
		if (closed) return;
		line.append(i);
	}

	@Override
	public void print(long l) {
		if (closed) return;
		line.append(l);
	}

	@Override
	public void print(float f) {
		if (closed) return;
		line.append(f);
	}

	@Override
	public void print(double d) {
		if (closed) return;
		line.append(d);
	}

	@Override
	public void print(char[] s) {
		if (closed) return;
		line.append(s);
	}

	@Override
	public void print(String s) {
		if (closed) return;
		line.append(s);
	}

	@Override
	public void print(Object obj) {
		if (closed) return;
		line.append(obj);
	}

	@Override
	public void println() {
		if (closed) return;
		String lineString = line.toString();
		if (accept(lineString)) {
			pass.println(lineString);
		}
		line.setLength(0);
	}

	@Override
	public void println(boolean x) {
		if (closed) return;
		print(x);
		println();
	}

	@Override
	public void println(char x) {
		if (closed) return;
		print(x);
		println();
	}

	@Override
	public void println(int x) {
		if (closed) return;
		print(x);
		println();
	}

	@Override
	public void println(long x) {
		if (closed) return;
		print(x);
		println();
	}

	@Override
	public void println(float x) {
		if (closed) return;
		print(x);
		println();
	}

	@Override
	public void println(double x) {
		if (closed) return;
		print(x);
		println();
	}

	@Override
	public void println(char[] x) {
		if (closed) return;
		print(x);
		println();
	}

	@Override
	public void println(String x) {
		if (closed) return;
		print(x);
		println();
	}

	@Override
	public void println(Object x) {
		if (closed) return;
		print(x);
		println();
	}

	@Override
	public PrintStream printf(String format, Object... args) {
		if (closed) return this;
		print(line.append(String.format(format, args)));
		return this;
	}

	@Override
	public PrintStream printf(Locale l, String format, Object... args) {
		if (closed) return this;
		print(line.append(String.format(l, format, args)));
		return this;
	}

	@Override
	public PrintStream format(String format, Object... args) {
		if (closed) return this;
		print(line.append(String.format(format, args)));
		return this;
	}

	@Override
	public PrintStream format(Locale l, String format, Object... args) {
		if (closed) return this;
		print(line.append(String.format(l, format, args)));
		return this;
	}

	@Override
	public PrintStream append(CharSequence csq) {
		if (closed) return this;
		line.append(csq);
		return this;
	}

	@Override
	public PrintStream append(CharSequence csq, int start, int end) {
		if (closed) return this;
		line.append(csq, start, end);
		return this;
	}

	@Override
	public PrintStream append(char c) {
		if (closed) return this;
		line.append(c);
		return this;
	}

	@Override
	public void write(byte[] b) throws IOException {
		if (closed) return;
		line.append(b);
	}

	@Override
	public void flush() {
		String lineString = line.toString();
		if (accept(lineString)) {
			pass.print(lineString);
			pass.flush();
		}
		line.setLength(0);
	}

	@Override
	public void close() {
		super.close();
		flush();
		closed = true;
	}
	
	
}
