package id.ac.its.axel137;

import java.awt.FlowLayout;

import javax.swing.*;

public class Frame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 100L;
	private final JLabel data;
	private final Icon foto;
	
	
	
	public Frame(){
		super("Info Mahasiswa");
		setLayout(new FlowLayout());
		
		foto = new ImageIcon("biru.jpg");
		data = new JLabel();
		data.setText("<html>05111940000137<br>Axel Briano Suherik</html>");
		data.setIcon(foto);
		data.setHorizontalTextPosition(SwingConstants.CENTER);
		data.setVerticalTextPosition(SwingConstants.BOTTOM);
		add(data);
	}
	
}
