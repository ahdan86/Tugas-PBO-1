package id.its.ac.axel137;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {	
		Rectangle rec = new Rectangle(getPanjang(), getLebar());
		
		JOptionPane.showMessageDialog(null, "The area of rectangle: " + rec.getArea(), "Rectangle shape", JOptionPane.PLAIN_MESSAGE);
		
		Square sqr = new Square(getSisi());
		
		JOptionPane.showMessageDialog(null, "The area of square: " + sqr.getArea(), "Square shape", JOptionPane.PLAIN_MESSAGE);
	}
	
	public static double getPanjang() {
		String panjang = JOptionPane.showInputDialog("Enter the lenght of rectangle");
		return Double.parseDouble(panjang);
	}
	
	public static double getLebar() {
		String lebar = JOptionPane.showInputDialog("Enter the width of rectangle");
		return Double.parseDouble(lebar);
	}
	
	public static double getSisi() {
		String sisi = JOptionPane.showInputDialog("Enter the side lenght of a square");
		return Double.parseDouble(sisi);
	}
}
