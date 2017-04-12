package de.dualuse.util.logging;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;

public interface Filtered {
	
	public final static Allow allow = new Allow();
	public final static Deny deny = new Deny();
	
	
	public static class Allow {
		System.LineFilter last = null;
		
		public Allow path(String path) {
			System.LineFilter next = new System.PathFilter(path);
			if (last!=null && System.filters.size()>0 && last == System.filters.get(System.filters.size()-1))
				System.filters.set(System.filters.size()-1, new System.OrFilter(System.filters.get(System.filters.size()-1),next));
			else
				System.filters.add(next);
			
			last = next;
			return this; 
		}
		public Allow message(String regex) { System.filters.add(new System.MessageFilter(regex)); return this; }
		public final Deny deny = denyStatic;
		
		private final static Deny denyStatic = new Deny();
	}
	
	public static class Deny {
		public Deny path(String path) { System.filters.add(new System.NotFilter(new System.PathFilter(path))); return this; }
		public Deny message(String regex) { System.filters.add(new System.NotFilter(new System.MessageFilter(regex))); return this; }
		public final Allow allow = new Allow();
	}
	
	
	@Deprecated
	public static class System extends ShadowSystem {
		static public final InputStream in = java.lang.System.in;
		static public final PrintStream err = new FilteredPrintStream(java.lang.System.err);
		static public final PrintStream out = new FilteredPrintStream(java.lang.System.out);
		
		private static CopyOnWriteArrayList<LineFilter> filters = new CopyOnWriteArrayList<LineFilter>(); 
		private static interface LineFilter {
			public boolean accept(String callerPath, String line);
		}

		private static class OrFilter implements LineFilter {
			final LineFilter a, b;
			public OrFilter(System.LineFilter a, System.LineFilter b) {
				this.a = a; 
				this.b = b;
			}
			
			@Override
			public boolean accept(String callerPath, String line) {
				return a.accept(callerPath, line) || b.accept(callerPath, line);
			}
		}
		
		private static class NotFilter implements LineFilter {
			final LineFilter inner;
			public NotFilter(LineFilter inner) {
				this.inner = inner;
			}
			
			@Override
			public boolean accept(String callerPath, String line) {
				return !inner.accept(callerPath, line);
			}
			
		}
		
		static class MessageFilter implements LineFilter {
			Pattern messagePattern;
			public MessageFilter(String messageExpression) {
				messagePattern = Pattern.compile(messageExpression);
			}
			
			@Override
			public boolean accept(String callerPath, String message) {
				return messagePattern.matcher(message).matches();
			}			
		}
		static class PathFilter implements LineFilter {
			Pattern pathPattern;
			public PathFilter(String path) {
				pathPattern = Pattern.compile( "^"+globbingExpression(path)+"$" );
			}
			
			@Override
			public boolean accept(String callerPath, String line) {
				return pathPattern.matcher(callerPath).matches();
			}

			static public String globbingExpression(String globbingPattern) {
				StringBuilder expression = new StringBuilder(globbingPattern.length()*2);
				int i=0,j=0, I=globbingPattern.length();
				for (;i<I;i++)
					switch (globbingPattern.charAt(i)) {
					case '*':
						expression.append(Pattern.quote(globbingPattern.substring(j, i)));
						if (i<I-1 && globbingPattern.charAt(i+1)=='*') {
							expression.append(".*");
							i+=1;
						} else {
							expression.append("[^.^/]*");
//							i+=0;
						}
						j=i+1;
						break;
					case '?': 
						if (j<i)
							expression.append(Pattern.quote(globbingPattern.substring(j, i)));
						expression.append(".");
//						i++;
						j=i+1;
						break;
					default:
					}
				
				if(j<i)
					expression.append(Pattern.quote(globbingPattern.substring(j,i)));
				
				
				return expression.toString();
			}

		}
		
		static String base = MaskedPrintStream.class.getName();
		
		private static class FilteredPrintStream extends MaskedPrintStream {
			@Override
			protected boolean accept(String line) {
				
				StackTraceElement ste[] = new Throwable().getStackTrace();
				String callerPath = "";
				
				String prevName = ste[0].getClassName();
				for (int i=1,I=ste.length;i<I;i++) {
					String curName = ste[i].getClassName();
					if (prevName.startsWith(base) && !curName.startsWith(base)) {
						callerPath = ste[i].getClassName()+"."+ste[i].getMethodName();
						break;
					}
					prevName = curName;
				}
				
				boolean lastCall = true;
				for (LineFilter f: filters)
					lastCall = f.accept(callerPath, line);
				
				return lastCall;
			}
			
			public FilteredPrintStream(PrintStream pass) {
				super(pass);
			}
		}
		
	}
}
