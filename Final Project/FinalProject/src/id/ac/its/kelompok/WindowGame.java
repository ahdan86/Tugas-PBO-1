package id.ac.its.kelompok;

import java.awt.Component;

import javax.swing.*;

public class WindowGame {
	public static final int WIDTH = 750, HEIGHT = 700;

    private BoardClassic boardClassic;
    private Component title;

    public WindowGame() {


    }

    public void startTetris() {
//        window.remove(title);
//        window.addMouseMotionListener(board);
//        window.addMouseListener(board);
//        window.add(board);
//        board.startGame();
//        window.revalidate();
    }

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame window = new JFrame("Tetris");
                window.setSize(WIDTH, HEIGHT);
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setLocationRelativeTo(null);
                window.setResizable(false);
                window.setContentPane(new MainMenu(WIDTH, HEIGHT));
//        window.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                int choose = JOptionPane.showConfirmDialog(window, "Do you really want to exit the application?",
//                        "Confirm Close", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
//                if(choose == JOptionPane.YES_OPTION) {
//                    e.getWindow().dispose();
//
//                    // check if board class is already made
//                    try {
//                        Board panel = (Board) window.getContentPane();
//                    }
//                    catch (Exception ex) {} // do nothing
//                } else {
//                    window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//                }
//            }
//        });

                window.setVisible(true);
            }
        });
    }
}
