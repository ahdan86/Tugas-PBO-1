package id.ac.its.kelompok;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.*;

public class BoardClassic extends JPanel implements KeyListener, MouseListener, MouseMotionListener {

	//Assets
    private static final long serialVersionUID = 1L;
    private final int boardHeight = 20, boardWidth = 10;
    public static final int blockSize = 30;
    private Color[][] board = new Color[boardHeight][boardWidth];
    private Shape[] shapes = new Shape[7];
    private static Shape currentShape, nextShape;
    private Timer looper;
    private int FPS = 60;
    private int delay = 1000 / FPS;
    private int mouseX, mouseY;
    private boolean leftClick = false;
    private boolean gamePaused = false;
    private boolean gameOver = false;
    private JButton pauseButton = new JButton("Pause");
    private PauseMenu pauseDialog;

    // score
    private int score = 0;
    private int highScore;
    private String highScoreName;
    public static int lineCleared = 0;
    public static int treeshold = 0;
    public static int normal = 600;
    
    private Color[] colors = {Color.decode("#ed1c24"), Color.decode("#ff7f27"), Color.decode("#fff200"), 
        Color.decode("#22b14c"), Color.decode("#00a2e8"), Color.decode("#a349a4"), Color.decode("#3f48cc")};
    private Random random = new Random();

    public BoardClassic(JFrame frame) {
        setLayout(null);
        // create shapes
        shapes[0] = new Shape(new int[][]{
                {1, 1, 1, 1} // I shape;
        }, this, colors[0]);

        shapes[1] = new Shape(new int[][]{
                {1, 1, 1},
                {0, 1, 0}, // T shape;
        }, this, colors[1]);

        shapes[2] = new Shape(new int[][]{
                {1, 1, 1},
                {1, 0, 0}, // L shape;
        }, this, colors[2]);

        shapes[3] = new Shape(new int[][]{
                {1, 1, 1},
                {0, 0, 1}, // J shape;
        }, this, colors[3]);

        shapes[4] = new Shape(new int[][]{
                {0, 1, 1},
                {1, 1, 0}, // S shape;
        }, this, colors[4]);

        shapes[5] = new Shape(new int[][]{
                {1, 1, 0},
                {0, 1, 1}, // Z shape;
        }, this, colors[5]);

        shapes[6] = new Shape(new int[][]{
                {1, 1},
                {1, 1}, // O shape;
        }, this, colors[6]);

        mouseX = 0;
        mouseY = 0;

        pauseButton.setBounds(620, 560, 100, 40);
        setPauseAction(pauseButton);
        this.add(pauseButton);

        pauseDialog = new PauseMenu(frame, this, pauseButton);

        if(ReadSerialScoreClassic.openFile())
        {
            ReadSerialScoreClassic.readRecords();
            highScore = ReadSerialScoreClassic.getScore();
            highScoreName = ReadSerialScoreClassic.getNama();
        }
        else {
            highScore = 0;
            highScoreName = "-";
        }
        System.out.print(highScore);

        looper = new Timer(delay, new GameLooper());
        addKeyListener(this);
        startGame();
    }

    private void update() {

        if (gamePaused || gameOver) {
            return;
        }
        currentShape.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
        currentShape.render(g);
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {

                if (board[row][col] != null) {
                    g.setColor(board[row][col]);
                    g.fillRect(col * blockSize, row * blockSize, blockSize, blockSize);
                }

            }
        }
        g.setColor(nextShape.getColor());
        for (int row = 0; row < nextShape.getCoords().length; row++) {
            for (int col = 0; col < nextShape.getCoords()[0].length; col++) {
                if (nextShape.getCoords()[row][col] != 0) {
                    g.fillRect(col * 30 + 320, row * 30 + 50, BoardClassic.blockSize, BoardClassic.blockSize);
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

        g.drawString("LEVEL", WindowGame.WIDTH - 125, WindowGame.HEIGHT / 2 + 70);
        g.drawString(treeshold + "", WindowGame.WIDTH - 125, WindowGame.HEIGHT / 2 + 100);

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
        int index = random.nextInt(shapes.length);
        int colorIndex = random.nextInt(colors.length);
        nextShape = new Shape(shapes[index].getCoords(), this, colors[colorIndex]);
        nextShape.normal = BoardClassic.normal;
    }

    public void setCurrentShape() {
        currentShape = nextShape;
        setNextShape();

        for (int row = 0; row < currentShape.getCoords().length; row++) {
            for (int col = 0; col < currentShape.getCoords()[0].length; col++) {
                if (currentShape.getCoords()[row][col] != 0) {
                    if (board[currentShape.getY() + row][currentShape.getX() + col] != null) {
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
            currentShape.rotateShape();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            currentShape.setDeltaX(1);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            currentShape.setDeltaX(-1);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            currentShape.speedUp();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            currentShape.speedDown();
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
