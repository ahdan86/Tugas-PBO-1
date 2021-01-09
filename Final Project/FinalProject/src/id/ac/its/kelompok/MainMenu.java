package id.ac.its.kelompok;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends JPanel {

    private int areaWidth;
    private int areaHeight;
    private final JButton playClassic = new JButton("Classic");
    private final JButton playZen = new JButton("Zen");
    private final JButton playChallenge = new JButton("Challenge");
    private final JButton score = new JButton("Score");
    private final JButton credits = new JButton("Credits");
    private final JButton exit = new JButton("Exit");
    private final int wButton;
    private final int hButton;

    public MainMenu(int width, int height) {
        this.areaWidth = width;
        this.areaHeight = height;
        wButton = 100;
        hButton = 50;
        this.setPreferredSize(new Dimension(areaWidth, areaHeight));
        setLayout(null);

        playClassic.setBounds(((areaWidth/2) - (wButton/2)), 133, wButton, hButton);
        playZen.setBounds(((areaWidth/2) - (wButton/2)), 203, wButton, hButton);
        playChallenge.setBounds(((areaWidth/2) - (wButton/2)), 273, wButton, hButton);
        score.setBounds(((areaWidth/2) - (wButton/2)), 343, wButton, hButton);
        credits.setBounds(((areaWidth/2) - (wButton/2)), 413, wButton, hButton);
        exit.setBounds(((areaWidth/2) - (wButton/2)), 483, wButton, hButton);

        this.add(playClassic);
        this.add(playZen);
        this.add(playChallenge);
        this.add(score);
        this.add(credits);
        this.add(exit);

        // adding action listener
        ButtonHandler handler = new ButtonHandler();
        playClassic.addActionListener(handler);
        score.addActionListener(handler);
        credits.addActionListener(handler);
        exit.addActionListener(handler);

        setHover(playClassic);
        setHover(playZen);
        setHover(playChallenge);
        setHover(score);
        setHover(credits);
        setHover(exit);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
//			Main.sfx.ok.playbackMusic();
            if (e.getActionCommand().equals("Start Game")) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(playClassic.getParent());
                frame.setContentPane(new BoardClassic(frame));
                frame.setFocusable(true);
                frame.revalidate();
                frame.getContentPane().requestFocus();
                frame.getContentPane().setFocusable(true);
            }

            else if(e.getActionCommand().equals("Score")) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(score.getParent());

                frame.setContentPane(new BoardClassic(frame));
                frame.revalidate();
            }

            else if(e.getActionCommand().equals("Credits")) {
                //show Credits
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(credits.getParent());
                JOptionPane.showMessageDialog(frame, "Axel");
            }
            else if(e.getActionCommand().equals("Exit")) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(exit.getParent());
                int choose = JOptionPane.showConfirmDialog(frame, "Do you really want to exit the application?",
                        "Confirm Close", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if(choose == JOptionPane.YES_OPTION) {
                    frame.dispose();
                } else {
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
