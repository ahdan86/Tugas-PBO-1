package id.ac.its.kelompok;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MainMenu extends JPanel {

    private int areaWidth;
    private int areaHeight;
    private final JButton playClassic = new JButton("Classic");
    private final JButton playZen = new JButton("Zen");
    private final JButton credits = new JButton("Credits");
    private final JButton exit = new JButton("Exit");
    private final int wButton;
    private final int hButton;
    private BufferedImage title;
    private int highScoreClassic;
    private int highScoreZen;
    private String highScoreNameZen;
    private String highScoreNameClassic;

    public MainMenu(int width, int height) {
        this.areaWidth = width;
        this.areaHeight = height;
        wButton = 100;
        hButton = 50;
        this.setPreferredSize(new Dimension(areaWidth, areaHeight));
        setLayout(null);

        title = ImageLoader.loadImage("/tetris.png");
        
        if(ReadSerialScoreClassic.openFile())
        {
            ReadSerialScoreClassic.readRecords();
            highScoreClassic = ReadSerialScoreClassic.getScore();
            highScoreNameClassic = ReadSerialScoreClassic.getNama();
        }
        else {
            highScoreClassic = 0;
            highScoreNameClassic = "-";
        }

        if(ReadSerialScoreZen.openFile()) {
            ReadSerialScoreZen.readRecords();
            highScoreZen = ReadSerialScoreZen.getScore();
            highScoreNameZen = ReadSerialScoreZen.getNama();
        } else {
            highScoreZen = 0;
            highScoreNameZen = "-";
        }

        playClassic.setBounds(((areaWidth/2) - (wButton/2) - 230), 413, wButton, hButton);
        playZen.setBounds(((areaWidth/2) - (wButton/2) - 80), 413, wButton, hButton);
        credits.setBounds(((areaWidth/2) - (wButton/2) + 70), 413, wButton, hButton);
        exit.setBounds(((areaWidth/2) - (wButton/2) + 220), 413, wButton, hButton);

        this.add(playClassic);
        this.add(playZen);
        this.add(credits);
        this.add(exit);

        // adding action listener
        ButtonHandler handler = new ButtonHandler();
        playClassic.addActionListener(handler);
        playZen.addActionListener(handler);
        credits.addActionListener(handler);
        exit.addActionListener(handler);

        setHover(playClassic);
        setHover(playZen);
        setHover(credits);
        setHover(exit);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(title, WindowGame.WIDTH/2 - 135 ,150,null);
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("Georgia", Font.BOLD, 30));
        g.drawString("HighScore Classic Mode: " + highScoreNameClassic + " " + highScoreClassic, 20, 550);
        g.drawString("HighScore Zen Mode: " + highScoreNameZen + " " + highScoreZen, 20, 600);
    }

    public class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Classic": {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(playClassic.getParent());
                    frame.setContentPane(new BoardClassic(frame));
                    frame.setFocusable(true);
                    frame.revalidate();
                    frame.getContentPane().requestFocus();
                    frame.getContentPane().setFocusable(true);
                    break;
                }
                case "Zen": {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(playZen.getParent());

                    frame.setContentPane(new BoardZen(frame));
                    frame.setFocusable(true);
                    frame.revalidate();
                    frame.getContentPane().requestFocus();
                    frame.getContentPane().setFocusable(true);
                    break;
                }
                case "Credits": {
                    //show Credits
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(credits.getParent());
                    frame.setContentPane(new CreditScene(frame));
                    frame.setFocusable(true);
                    frame.revalidate();
                    frame.getContentPane().requestFocus();
                    frame.getContentPane().setFocusable(true);
                    break;
                }
                case "Exit": {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(exit.getParent());
                    int choose = JOptionPane.showConfirmDialog(frame, "Do you really want to exit the application?",
                            "Confirm Close", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    if (choose == JOptionPane.YES_OPTION) {
                        frame.dispose();
                    } else {
                        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    }
                    break;
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
