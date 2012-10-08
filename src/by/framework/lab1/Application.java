package by.framework.lab1;

import static java.lang.System.out;
import java.lang.reflect.*;

import java.util.Arrays;
import java.util.Comparator;

import by.framework.lab1.Sandwiches.Impressions;
import by.framework.lab1.Sandwiches.Magic;
import by.framework.lab1.Sandwiches.Witchcraft;

public class Application {

	private static Object[] breakfast = new Sandwich[20];
	private static boolean sort = false;
	private static boolean calcCalories = false;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		int count = 0;
		for(String arg : args) {
			if(arg.equals("-sort")) {
				sort = true;
			}
			else if(arg.equals("-calories")) {
				calcCalories = true;
			}
			else {
				if(count == 20) continue;
				String[] item = arg.split("/");
				try {
					Class<Sandwich> o = (Class<Sandwich>) Class.forName("by.framework.lab1.Sandwiches." 
				                    + item[1]);
					breakfast[count++] = o.newInstance();
				} catch (ClassNotFoundException e) {
					out.println(arg + " is illegal.");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		sortBreakfast();
		calulate();
		showBreakfast();	
		counter();
	}
	
	private static void counter() {
		int countImpression = 0, countMagic = 0, countWichcraft = 0;
		for(Object o : breakfast) {
			if(o instanceof Impressions) ++countImpression;
			else if(o instanceof Magic) ++countMagic;
			else if(o instanceof Witchcraft) ++countWichcraft;
		}
		out.println(
				"\nImpressions sandwich: " + countImpression + 
				"\nMagic sandwich:  " + countMagic + 
				"\nWichcraft: " + countWichcraft);
	}
	
	private static void sortBreakfast() {
		if(sort) {
			Arrays.sort((Sandwich[])breakfast, new Comparator<Sandwich>() {

				@Override
				public int compare(Sandwich o1, Sandwich o2) {
					if(o1 == null) return 1;
					if(o2 == null) return -1;
					if(o1.getName().length() == o2.getName().length()) {
						return 0;
					} else if(o1.getName().length() > o2.getName().length()) {
						return 1;
					} else {
						return -1;
					}
				}
			});
		}
	}
	
	private static void showBreakfast() {
		for(Object sandwich : breakfast) {
			if(sandwich != null) {
				out.println((Sandwich)sandwich);
			} else break;
		}
	}
	
	private static void calulate() {
		if(calcCalories) {
			int calories = 0;
			for(Object object : breakfast) {
				if(object != null) {
					Class<? extends Object> c = object.getClass();
					try {
						Method method = c.getMethod("calculateCalories", null);
						calories += (int)method.invoke(object, null);
					} catch (NoSuchMethodException e) {
						out.println("Can't get calories in one of the instances");
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					break;
				}
			}
			out.println("Calories in you breakfast: " + calories);
		}
	}
	
}
