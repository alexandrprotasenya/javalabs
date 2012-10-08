package by.framework.lab1;

import static java.lang.System.out;

public class Sandwich extends Food implements Nutritious {

	private Filling filling1;
	private Filling filling2;
	
	public Sandwich(String name, Filling filling1, Filling filling2) {
		super(name);
		this.filling1 = filling1;
		this.filling2 = filling2;
	}

	@Override
	public void consume() {
		out.println(name);
	}
	
	public String toString() {
		return 
				name  + " sandwich with "+ 
				filling1.toString().toLowerCase() + " and " + 
				filling2.toString().toLowerCase() + ".";
	}
	
	@Override
	public boolean equals(Object otherObject) {
		if(this == otherObject) return true;
		if(otherObject == null) return false;
		if(getClass() != otherObject.getClass()) return false;
		Sandwich other = (Sandwich) otherObject;
		if(hashCode() != other.hashCode()) return false;
		if(filling1 == other.filling1 && filling2 == other.filling2)
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
		return filling1.getColories() + filling2.getColories();
	}
}
