package id.ac.its.kelompok;

import javax.swing.*;
import java.awt.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class CreditScene extends JPanel {
    private int areaWidth = 750;
    private int areaHeight = 700;
    private final int wButton;
    private final int hButton;
    private final JButton back = new JButton("Back");
    private BufferedImage foto1;
    private BufferedImage foto2;

    public CreditScene(JFrame frame) {
        wButton = 100;
        hButton = 50;
        this.setPreferredSize(new Dimension(areaWidth, areaHeight));
        setLayout(null);

        foto1 = ImageLoader.loadImage("/ahdan.png");
        foto2 = ImageLoader.loadImage("/axel.png");
        back.setBounds(((areaWidth-600) - (wButton)), 550, wButton, hButton);

        this.add(back);

        // adding action listener
        ButtonHandler handler = new ButtonHandler();
        back.addActionListener(handler);
        setHover(back);
    }



	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.drawImage(foto1, WindowGame.WIDTH/2-200,
        10,null);

        g.setColor(Color.white);
        g.setFont(new Font("Georgia", Font.BOLD, 20));
        g.drawString("Nama : Ahdan Amanullah I.M",  WindowGame.WIDTH/2 - 25, 40);
        g.drawString("NRP : 05111940000197",  WindowGame.WIDTH/2 - 25, 60);

        g.drawImage(foto2, WindowGame.WIDTH/2 - 200,
        250,null);

        g.setColor(Color.white);
        g.setFont(new Font("Georgia", Font.BOLD, 20));
        g.drawString("Nama : Axel Briano Suherik",  WindowGame.WIDTH/2 - 25, 280);
        g.drawString("NRP : 05111940000137",  WindowGame.WIDTH/2 - 25, 300);
        

        g.setColor(Color.white);
        g.setFont(new Font("Georgia", Font.BOLD, 100));
        g.drawString("CREDIT",  WindowGame.WIDTH/2 - 75, 650);

    }

    public class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Back": {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(back.getParent());
                    frame.setContentPane(new MainMenu(WindowGame.WIDTH, WindowGame.HEIGHT));
                    frame.invalidate();
                    frame.validate();
                }
            }
        }
    }

    private void setHover(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
//				Main.sfx.cursor.playbackMusic();
                button.setBackground(new Color(244, 179, 80));
            }
            public void mouseClicked(MouseEvent e) {
                button.setBackground(new Color(244, 179, 80));
            }
            public void mousePressed(MouseEvent e) {
                button.setBackground(new Color(244, 179, 80));
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(UIManager.getColor("control"));
            }
        });
    }

}
