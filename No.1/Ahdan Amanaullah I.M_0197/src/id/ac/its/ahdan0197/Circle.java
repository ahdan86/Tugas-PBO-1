package id.ac.its.ahdan0197;

public class Circle extends Shape {
	protected double radius;
	
	public Circle()
	{
		super();
		this.radius=0.0;
	}
	
	public Circle(double radius)
	{
		super();
		this.radius=radius;
	}
	
	public Circle(String color,double radius)
	{
		super(color);
		this.radius=radius;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	@Override
	public double getArea() {
		return 3.14*this.radius*this.radius;
	}
	
	@Override
	public double getCircumference() {
		return 2*3.14*radius;
	}
}
