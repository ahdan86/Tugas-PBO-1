package id.ac.its.kelompok;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


public class SpaceShip extends Sprite {

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

    }
    
    public void mouseMoved(MouseEvent e)
    {
    	if(!(e.getX()  + width > 380) && !(e.getX() < 0)){
            x=e.getX();
        }	
    	
    	if (!(e.getY() < 0) && !(e.getY() + height > 270)){
            y=e.getY();
        }

    }
}
