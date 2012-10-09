package by.framework.lab1.Products;

import by.framework.lab1.Food;
import by.framework.lab1.Nutritious;

public class Cheese extends Food implements Nutritious {
	
	public Cheese() {
		super("Сыр");
	}
	
	public void consume() {
		System.out.println(this + " съеден");
	}
	
	@Override
	public int calculateCalories() {
		return getName().length();
	}
}
