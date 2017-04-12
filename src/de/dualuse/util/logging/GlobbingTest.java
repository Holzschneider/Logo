package de.dualuse.util.logging;

public class GlobbingTest {

	public static void main(String[] args) {
		
		String[]  names = {
				"hallo01.jpg",
				"hallo02.blub.bla.jpg",
				"hallo002.jpg",
		};
		
		
		String expr = Filtered.System.PathFilter.globbingExpression("hallo**blub**.jpg");
		
		System.out.println(expr);
		
		for (String name: names)
			if (name.matches(expr))
				System.out.println("OK: "+name);
			else
				System.out.println("NO: "+name);
	}
}
