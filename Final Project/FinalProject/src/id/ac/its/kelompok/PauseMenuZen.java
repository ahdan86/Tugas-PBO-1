package id.ac.its.kelompok;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PauseMenuZen extends JDialog {

    private final int areaWidth = 272;
    private final int areaHeight = 340;
    private final int border = 10;
    private final int wButton = 100;
    private final int hButton = 50;
    private final JButton resume = new JButton("Resume");
    private final JButton restart = new JButton("Restart");
    private final JButton quit = new JButton("Main Menu");
    private final JTextField pauseHeader = new JTextField("Paused");
    private final JButton pauseButton;
    private final JFrame frame;
    private final BoardZen board;

    public PauseMenuZen(JFrame owner, BoardZen board, JButton pauseButton) {
        super(owner, true);
        this.frame = owner;
        this.board = board;
        this.pauseButton = pauseButton;
        this.setPreferredSize(new Dimension(areaWidth, areaHeight));
        this.setLayout(null);
        this.setUndecorated(true);
        this.getContentPane().setBackground(Color.DARK_GRAY);
        getRootPane().setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 10));

        resume.setBounds((areaWidth - wButton - border) / 2, 80, wButton, hButton);
        restart.setBounds((areaWidth - wButton - border) / 2, 160, wButton, hButton);
        quit.setBounds((areaWidth - wButton - border) / 2, 240, wButton, hButton);

        pauseHeader.setBackground(Color.DARK_GRAY);
        pauseHeader.setBorder(null);
        pauseHeader.setFont(new Font("Tahoma", Font.BOLD, 30));
        pauseHeader.setHorizontalAlignment(JTextField.CENTER);
        pauseHeader.setEditable(false);
        pauseHeader.setForeground(Color.WHITE);
        pauseHeader.setBounds(-border / 2, border, areaWidth, hButton - border);

        this.add(resume);
        this.add(restart);
        this.add(quit);
        this.add(pauseHeader);

        ButtonHandler handler1 = new ButtonHandler();
        resume.addActionListener(handler1);
        restart.addActionListener(handler1);
        quit.addActionListener(handler1);

        setHover(resume);
        setHover(restart);
        setHover(quit);
    }

    public void showDialog() {
        this.pack();
        this.setLocationRelativeTo(this.board);

        pauseButton.setBackground(UIManager.getColor("control"));
        board.setEnabled(false);
        this.setVisible(true);
    }

    public void hideDialog() {
        board.setEnabled(true);
        this.setVisible(false);
    }

    private void unpause() {
        board.requestFocusInWindow();
        board.pauseGame();
    }

    public class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
//			Main.sfx.ok.playbackMusic();
            if (e.getActionCommand().equals("Resume")) {
                try {
                    PauseMenuZen dialog = (PauseMenuZen) SwingUtilities.getWindowAncestor(resume.getParent());
                    pauseButton.setText("Pause");

                    dialog.unpause();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            } else if (e.getActionCommand().equals("Restart")) {
                unpause();
                board.startGame();
                board.repaint();

            } else if (e.getActionCommand().equals("Main Menu")) {
                int choose = JOptionPane.showConfirmDialog(frame, "Do you really want to go back to main menu?",
                        "Confirm Back", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (choose == JOptionPane.YES_OPTION) {
                    hideDialog();
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(pauseButton.getParent());
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
