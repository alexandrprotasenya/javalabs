package by.framework.lab1.Products;

import static java.lang.System.out;
import by.framework.lab1.Food;
import by.framework.lab1.Nutritious;

public class Sandwich extends Food implements Nutritious {

	private String filling1;
	private String filling2;
	
	public Sandwich(String name, String inFilling1, String filling2) {
		super(name);
		filling1 = inFilling1.toUpperCase();
		this.filling2 = filling2.toUpperCase();
	}

	@Override
	public void consume() {
		out.println(getName());
	}
	
	public String toString() {
		return 
				"Бутерброд с "+ 
				filling1 + " и " + 
				filling2 + ".";
	}
	
	@Override
	public boolean equals(Object otherObject) {
		if(this == otherObject) return true;
		if(otherObject == null) return false;
		if(getClass() != otherObject.getClass()) return false;
		Sandwich other = (Sandwich) otherObject;
		if(hashCode() != other.hashCode()) return false;
		if(filling1.equals(other.filling1) && filling2.equals(other.filling2))
			return true;
		else
			return false;
	}
	
	@Override
	public int hashCode() {
		return calculateCalories();
	}

	@Override
	public int calculateCalories() {
		return filling1.length() + filling2.length();
	}
}
