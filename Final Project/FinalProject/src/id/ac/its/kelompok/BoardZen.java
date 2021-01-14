package id.ac.its.kelompok;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class BoardZen extends JPanel implements KeyListener, MouseListener, MouseMotionListener {


    //Assets
    private static final long serialVersionUID = 1L;
    private final int boardHeight = 20, boardWidth = 10;
    public static final int blockSize = 30;
    private Color[][] board = new Color[boardHeight][boardWidth];
    private ShapeZen[] ShapeZens = new ShapeZen[7];
    private static ShapeZen currentShapeZen, nextShapeZen;
    private Timer looper;
    private int FPS = 60;
    private int delay = 1000 / FPS;
    private int mouseX, mouseY;
    private boolean leftClick = false;
    private boolean gamePaused = false;
    private boolean gameOver = false;
    private JButton pauseButton = new JButton("Pause");
    private PauseMenuZen pauseDialog;

    // score
    private int score = 0;
    private int highScore;
    private String highScoreName;

    private Color[] colors = {Color.decode("#ed1c24"), Color.decode("#ff7f27"), Color.decode("#fff200"),
            Color.decode("#22b14c"), Color.decode("#00a2e8"), Color.decode("#a349a4"), Color.decode("#3f48cc")};
    private Random random = new Random();

    public BoardZen(JFrame frame) {
        setLayout(null);
        // create shapes
        ShapeZens[0] = new ShapeZen(new int[][]{
                {1, 1, 1, 1} // I shape;
        }, this, colors[0]);

        ShapeZens[1] = new ShapeZen(new int[][]{
                {1, 1, 1},
                {0, 1, 0}, // T shape;
        }, this, colors[1]);

        ShapeZens[2] = new ShapeZen(new int[][]{
                {1, 1, 1},
                {1, 0, 0}, // L shape;
        }, this, colors[2]);

        ShapeZens[3] = new ShapeZen(new int[][]{
                {1, 1, 1},
                {0, 0, 1}, // J shape;
        }, this, colors[3]);

        ShapeZens[4] = new ShapeZen(new int[][]{
                {0, 1, 1},
                {1, 1, 0}, // S shape;
        }, this, colors[4]);

        ShapeZens[5] = new ShapeZen(new int[][]{
                {1, 1, 0},
                {0, 1, 1}, // Z shape;
        }, this, colors[5]);

        ShapeZens[6] = new ShapeZen(new int[][]{
                {1, 1},
                {1, 1}, // O shape;
        }, this, colors[6]);

        mouseX = 0;
        mouseY = 0;

        pauseButton.setBounds(620, 560, 100, 40);
        setPauseAction(pauseButton);
        this.add(pauseButton);

        pauseDialog = new PauseMenuZen(frame, this, pauseButton);

        if(ReadSerialScoreClassic.openFile()) {
            ReadSerialScoreClassic.readRecords();
            highScore = ReadSerialScoreClassic.getScore();
            highScoreName = ReadSerialScoreClassic.getNama();
        } else {
            highScore = 0;
            highScoreName = "-";
        }
        System.out.print(highScore);

        looper = new Timer(delay, new BoardZen.GameLooper());
        addKeyListener(this);
        startGame();
    }

    private void update() {

        if (gamePaused || gameOver) {
            return;
        }
        currentShapeZen.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
        currentShapeZen.render(g);
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {

                if (board[row][col] != null) {
                    g.setColor(board[row][col]);
                    g.fillRect(col * blockSize, row * blockSize, blockSize, blockSize);
                }

            }
        }
        g.setColor(nextShapeZen.getColor());
        for (int row = 0; row < nextShapeZen.getCoords().length; row++) {
            for (int col = 0; col < nextShapeZen.getCoords()[0].length; col++) {
                if (nextShapeZen.getCoords()[row][col] != 0) {
                    g.fillRect(col * 30 + 320, row * 30 + 50, BoardZen.blockSize, BoardZen.blockSize);
                }
            }
        }

        if (gameOver) {
            String gameOverString = "GAME OVER";
            g.setColor(Color.WHITE);
            g.setFont(new Font("Georgia", Font.BOLD, 30));
            g.drawString(gameOverString, 50, WindowGame.HEIGHT / 2);
        }
        g.setColor(Color.WHITE);

        g.setFont(new Font("Georgia", Font.BOLD, 20));

        g.drawString("HighScore: " + highScoreName + " " + highScore, WindowGame.WIDTH - 250, WindowGame.HEIGHT / 2 - 30);

        g.drawString("SCORE", WindowGame.WIDTH - 125, WindowGame.HEIGHT / 2);
        g.drawString(score + "", WindowGame.WIDTH - 125, WindowGame.HEIGHT / 2 + 30);

        g.setColor(new Color(1f, 1f, 1f, .25f));

        for (int i = 0; i <= boardHeight; i++) {
            g.drawLine(0, i * blockSize, boardWidth * blockSize, i * blockSize);
        }
        for (int j = 0; j <= boardWidth; j++) {
            g.drawLine(j * blockSize, 0, j * blockSize, boardHeight * 30);
        }

        pauseButton.repaint();
    }

    public void gameOvered()
    {
        if(gameOver){
            if(getScore() > highScore)
            {
                String nama = JOptionPane.showInputDialog("Masukkan Nama");

                SerialScoreClassic.openFile();
                SerialScoreClassic.addRecords(nama,getScore());
                highScore = getScore();
                // SerialScoreClassic.closeFile();
            }
        }
    }

    public int getScore()
    {
        return score;
    }

    public void setNextShape() {
        int index = random.nextInt(ShapeZens.length);
        int colorIndex = random.nextInt(colors.length);
        nextShapeZen = new ShapeZen(ShapeZens[index].getCoords(), this, colors[colorIndex]);
    }

    public void setCurrentShape() {
        currentShapeZen = nextShapeZen;
        setNextShape();

        for (int row = 0; row < currentShapeZen.getCoords().length; row++) {
            for (int col = 0; col < currentShapeZen.getCoords()[0].length; col++) {
                if (currentShapeZen.getCoords()[row][col] != 0) {
                    if (board[currentShapeZen.getY() + row][currentShapeZen.getX() + col] != null) {
                        gameOver = true;
                        gameOvered();
                        break;
                    }
                }
            }
        }
    }

    public Color[][] getBoard() {
        return board;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && !gamePaused) {
            currentShapeZen.rotateShape();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            currentShapeZen.setDeltaX(1);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            currentShapeZen.setDeltaX(-1);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            currentShapeZen.speedUp();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            currentShapeZen.speedDown();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void pauseGame() {

        gamePaused = !gamePaused;
        if (gamePaused) {
            looper.stop();
            pauseButton.setVisible(false);
            pauseDialog.showDialog();
        } else {
            looper.start();
            pauseButton.setVisible(true);
            pauseDialog.hideDialog();
        }
    }

    public void startGame() {
        stopGame();
        setNextShape();
        setCurrentShape();
        gameOver = false;
        looper.start();
    }

    public void stopGame() {
        score = 0;

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = null;
            }
        }
        looper.stop();
    }

    class GameLooper implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            update();
            repaint();
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftClick = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftClick = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void addScore(int lineCleared) {
        int plus = lineCleared * 100;

        if(lineCleared == 2) {
            plus = (int) (plus * 1.1);
        } else if (lineCleared == 3) {
            plus = (int) (plus * 1.3);
        } else if (lineCleared == 4) {
            plus = (int) (plus * 1.6);
        }

        score += plus;
    }

    public void setPauseAction(JButton btn) {
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pauseGame();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                btn.setBackground(new Color(244, 179, 80));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                btn.setBackground(UIManager.getColor("control"));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(244, 179, 80));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(UIManager.getColor("control"));
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
            }
        });

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pauseGame();
            }
        });
    }

}
