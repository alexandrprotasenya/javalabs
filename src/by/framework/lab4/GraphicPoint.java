package by.framework.lab4;

public class GraphicPoint {
	
	private double X;
	private double Y;
	public GraphicPoint() {
		this(0,0);
	}
	public GraphicPoint(double x, double y) {
		X = x;
		Y = y;
	}
	public double getX() {
		return X;
	}
	public void setX(double x) {
		X = x;
	}
	public double getY() {
		return Y;
	}
	public void setY(double y) {
		Y = y;
	}

}
