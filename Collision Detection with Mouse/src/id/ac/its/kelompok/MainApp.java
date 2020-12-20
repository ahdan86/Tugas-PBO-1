package id.ac.its.kelompok;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class MainApp extends JFrame {

    public MainApp() {

        initUI();
    }

    private void initUI() {

        add(new Board());

        setTitle("Moving sprite");
        setSize(400, 300);

        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            MainApp ex = new MainApp();
            ex.setVisible(true);
        });
    }
}
