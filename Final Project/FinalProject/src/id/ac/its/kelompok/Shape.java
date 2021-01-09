package id.ac.its.kelompok;

import java.awt.Color;
import java.awt.Graphics;

public class Shape {
	private Color color;

    private int x, y;

    private long time, lastTime;

    public static int normal = 600, fast = 50;

    private int delay;

    private int[][] coords;

    private int[][] reference;

    private int deltaX;

    private BoardClassic boardClassic;

    private boolean collision = false, moveX = false;

    private int timePassedFromCollision = -1;

    public Shape(int[][] coords, BoardClassic boardClassic, Color color) {
        this.coords = coords;
        this.boardClassic = boardClassic;
        this.color = color;
        deltaX = 0;
        x = 4;
        y = 0;
        delay = normal;
        time = 0;
        lastTime = System.currentTimeMillis();
        reference = new int[coords.length][coords[0].length];

        System.arraycopy(coords, 0, reference, 0, coords.length);

    }

    long deltaTime;

    public void update() {
        moveX = true;
        deltaTime = System.currentTimeMillis() - lastTime;
        time += deltaTime;
        lastTime = System.currentTimeMillis();

        if (collision && timePassedFromCollision > 500) {
            for (int row = 0; row < coords.length; row++) {
                for (int col = 0; col < coords[0].length; col++) {
                    if (coords[row][col] != 0) {
                        boardClassic.getBoard()[y + row][x + col] = color;
                    }
                }
            }
            checkLine();
            boardClassic.setCurrentShape();
            timePassedFromCollision = -1;
        }

        // check moving horizontal
        if (!(x + deltaX + coords[0].length > 10) && !(x + deltaX < 0)) {

            for (int row = 0; row < coords.length; row++) {
                for (int col = 0; col < coords[row].length; col++) {
                    if (coords[row][col] != 0) {
                        if (boardClassic.getBoard()[y + row][x + deltaX + col] != null) {
                            moveX = false;
                        }

                    }
                }
            }

            if (moveX) {
                x += deltaX;
            }

        }

        // Check position + height(number of row) of shape
        if (timePassedFromCollision == -1) {
            if (!(y + 1 + coords.length > 20)) {

                for (int row = 0; row < coords.length; row++) {
                    for (int col = 0; col < coords[row].length; col++) {
                        if (coords[row][col] != 0) {

                            if (boardClassic.getBoard()[y + 1 + row][x + col] != null) {
                                collision();
                            }
                        }
                    }
                }
                if (time > delay) {
                    y++;
                    time = 0;
                }
            } else {
                collision();
            }
        } else {
            timePassedFromCollision += deltaTime;
        }

        deltaX = 0;
    }

    private void collision() {
        collision = true;
        timePassedFromCollision = 0;
    }

    public void render(Graphics g) {

        g.setColor(color);
        for (int row = 0; row < coords.length; row++) {
            for (int col = 0; col < coords[0].length; col++) {
                if (coords[row][col] != 0) {
                    g.fillRect(col * 30 + x * 30, row * 30 + y * 30, BoardClassic.blockSize, BoardClassic.blockSize);
                }
            }
        }

//        for (int row = 0; row < reference.length; row++) {
//            for (int col = 0; col < reference[0].length; col++) {
//                if (reference[row][col] != 0) {
//                    g.fillRect(col * 30 + 320, row * 30 + 160, Board.blockSize, Board.blockSize);
//                }
//
//            }
//
//        }

    }

    private void checkLine() {
        int size = boardClassic.getBoard().length - 1;
        int lineCleared = 0;

        for (int i = boardClassic.getBoard().length - 1; i > 0; i--) {
            int count = 0;

            for (int j = 0; j < boardClassic.getBoard()[0].length; j++) {
                if (boardClassic.getBoard()[i][j] != null) {
                    count++;
//                    System.out.println("count bertambah");
                }

                boardClassic.getBoard()[size][j] = boardClassic.getBoard()[i][j];
//                System.out.println("size: " + size + "\nj: " + j);
            }

            if (count < boardClassic.getBoard()[0].length) {
                size--;
            } else {
                lineCleared++;
            }
        }
        boardClassic.addScore(lineCleared);
        BoardClassic.lineCleared += lineCleared;
        System.out.println("\ntotal line cleared: " + BoardClassic.lineCleared);
        if (BoardClassic.lineCleared/4 > BoardClassic.treeshold) {
            BoardClassic.treeshold++;
            BoardClassic.normal -= (int) 100/ BoardClassic.treeshold;
            System.out.println("\ntreeshold: " + BoardClassic.treeshold);
            System.out.println("\nnormal: " + BoardClassic.normal);
        }
    }

    public void rotateShape() {

        int[][] rotatedShape = null;

        rotatedShape = transposeMatrix(coords);

        rotatedShape = reverseRows(rotatedShape);

        if ((x + rotatedShape[0].length > 10) || (y + rotatedShape.length > 20)) {
            return;
        }

        for (int row = 0; row < rotatedShape.length; row++) {
            for (int col = 0; col < rotatedShape[row].length; col++) {
                if (rotatedShape[row][col] != 0) {
                    if (boardClassic.getBoard()[y + row][x + col] != null) {
                        return;
                    }
                }
            }
        }
        coords = rotatedShape;
    }

    private int[][] transposeMatrix(int[][] matrix) {
        int[][] temp = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                temp[j][i] = matrix[i][j];
            }
        }
        return temp;
    }

    private int[][] reverseRows(int[][] matrix) {

        int middle = matrix.length / 2;

        for (int i = 0; i < middle; i++) {
            int[] temp = matrix[i];

            matrix[i] = matrix[matrix.length - i - 1];
            matrix[matrix.length - i - 1] = temp;
        }

        return matrix;

    }

    public Color getColor() {
        return color;
    }

    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    public void speedUp() {
        delay = fast;
    }

    public void speedDown() {
        delay = normal;
    }

    public int[][] getCoords() {
        return coords;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
