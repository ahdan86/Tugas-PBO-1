package id.ac.its.ahdan0197;

public abstract class Shape {
	
	protected String color;
	
	public Shape() {
		this.color = "Blue";
	}
	
	public Shape(String color) {
		this.color = color;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public abstract double getArea();

	public abstract double getCircumference();
	
}
