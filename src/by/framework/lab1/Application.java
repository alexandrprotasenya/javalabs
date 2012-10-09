package by.framework.lab1;

import static java.lang.System.out;
import java.lang.reflect.*;

import java.util.Arrays;
import java.util.Comparator;

public class Application {

	private static final int SIZE = 20;
	
	private static Food[] breakfast = new Food[SIZE];
	private static boolean sort = false;
	private static boolean calcCalories = false;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
				if(count == SIZE) continue;
				String[] item = arg.split("/");
				Object food = null;
				try {
					Class productClass = 
							Class.forName("by.framework.lab1.Products." + item[0]);
					Constructor constructor = null;
					
					try {
						constructor = productClass.getConstructor(String.class);
						food = constructor.newInstance(item[1]);
					} catch (NoSuchMethodException e1) {
						constructor = null;
						try {
							constructor = productClass.getConstructor(
									String.class, String.class, String.class);
							String[] fillings = item[1].split(",");
							food = constructor.newInstance(
									item[0],fillings[0], fillings[1]);
						} catch (NoSuchMethodException e2) {
							constructor = null;
							try {
								constructor = productClass.getConstructor();
								food = constructor.newInstance();
							} catch (NoSuchMethodException e3) {
								constructor = null;
							}
						}
					}
					
					if(food != null)
						breakfast[count++] = (Food) food;
				} catch (ClassNotFoundException e) {
					out.println(arg + " не найдено в меню.");
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
			}
		}
		
		sortBreakfast();
		calulate();
		showBreakfast();	
		counter();
	}
	
	private static void counter() {
		out.println("\n\nПодсчитаем продукты: ");
		boolean[] flags = new boolean[SIZE];
		for(int i = 0; i < SIZE; i++) {
			flags[i] = false;
		}
		for(int i = 0; i < SIZE; i++) {
			if(flags[i]) continue;
			Food tmp = breakfast[i];
			if(tmp == null) break;
			int count = 1;
			for(int j = i+1; j < SIZE; j++) {
				if(flags[j]) continue;
				Food tmp2 = breakfast[j];
				if(tmp2 == null) break;
				if(tmp.equals(tmp2)) {
					++count;
					flags[j] = true;
				}
			}
			flags[i] = true;
			out.println(tmp + ": " + count);
		}
	}
	
	private static void sortBreakfast() {
		if(sort) {
			Arrays.sort((Food[])breakfast, new Comparator<Food>() {

				@Override
				public int compare(Food o1, Food o2) {
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
				out.println((Food)sandwich);
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
