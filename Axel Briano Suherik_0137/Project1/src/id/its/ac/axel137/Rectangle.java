package id.its.ac.axel137;

public class Rectangle implements ShapeArea {
	private double panjang;
	private double lebar;
	
	
//	default constructor
	public Rectangle() {
		setPanjang(0.0);
		setLebar(0.0);
	}
	
	
//	constructor with param
	public Rectangle(double panjang, double lebar) {
		setPanjang(panjang);
		setLebar(lebar);
	}
	
	@Override
	public double getArea() {
		return panjang*lebar;
	}

	public Double getPanjang() {
		return panjang;
	}

	public void setPanjang(Double panjang) {
		if(panjang < 0) {
			throw new IllegalArgumentException("invalid value of panjang");
		}else {
			this.panjang = panjang;
		}
	}

	public Double getLebar() {
		return lebar;
	}

	public void setLebar(Double lebar) {
		if(panjang < 0) {
			throw new IllegalArgumentException("invalid value of lebar");
		}else {
			this.lebar = lebar;
		}
	}
	
	
	
}
