package id.ac.its.kelompok;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class SpaceShip extends Sprite {

    private int dx;
    private int dy;
    private List<Missile> missiles;

    public SpaceShip(int x, int y) {
        super(x, y);

        initSpaceShip();
    }

    private void initSpaceShip() {

        missiles = new ArrayList<>();

        loadImage("src/resources/craft.png");
        getImageDimension();
    }

    public void move() {
        if (!(y + dy < 0) && !(y + dy + height > 260)){
            y += dy;
        }

        if(!(x + dx + width > 380) && !(x + dx < 0)){
            x += dx;
        }
    }

    public List<Missile> getMissiles() {
        return missiles;
    }

    public void fire() {
        missiles.add(new Missile(x + width, y + height / 2));
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            fire();
        }

        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -2;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 2;
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
}
