package id.ac.its.ahdan0197;

import javax.swing.JOptionPane;

public class MainApp {

	public static void main(String[] args) {
		String radiusNumber = JOptionPane.showInputDialog("Enter the radius number");
		double radius1 = Double.parseDouble(radiusNumber);
		
		Circle li01 = new Circle(radius1);
		
		JOptionPane.showMessageDialog(null, "The area of circle is : " + li01.getArea(),
				"Area of circle", JOptionPane.PLAIN_MESSAGE);
		
		JOptionPane.showMessageDialog(null, "The Circumference of circle is : " + li01.getCircumference(),
				"Circumference of circle", JOptionPane.PLAIN_MESSAGE);
	}
}
