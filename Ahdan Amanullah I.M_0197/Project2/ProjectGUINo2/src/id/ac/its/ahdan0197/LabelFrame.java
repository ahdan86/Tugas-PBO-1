package id.ac.its.ahdan0197;

import java.awt.FlowLayout;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class LabelFrame extends JFrame {
	private final JLabel label1;
	
	public LabelFrame()
	{
		super("Label Identitas Mahasiswa");
		setLayout(new FlowLayout());
		
		label1=new JLabel();
		label1.setText("<html>" + "Nama mahasiswa: Ahdan Amanullah Irfan Muzhaffar<br/>"
				+ "NRP: 05111940000197" + "</html>");
		
		ImageIcon img1= new ImageIcon(getClass().getResource("FotoAhdan.jpg"));
		Image i = img1.getImage();
		Image new_img = i.getScaledInstance(480,640, Image.SCALE_SMOOTH);
		img1 = new ImageIcon(new_img);
		
		label1.setIcon(img1);
		label1.setHorizontalTextPosition(SwingConstants.CENTER);
		label1.setVerticalTextPosition(SwingConstants.BOTTOM);
		label1.setToolTipText("Gambar Mahasiswa");
		add(label1);
	}
}
