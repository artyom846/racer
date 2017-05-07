import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;

/**
 * Created by artemastasenok on 20.03.17.
 */
public class Enemy {
    int z;
    int w;
    int speed1 = 10;
    Image img = new ImageIcon("res/Enemey.png").getImage();
    Road road;

    public Rectangle getRect() {
        return new Rectangle(z, w, 138, 62);
    }



    public Enemy(int z, int w, int speed, Road road) {
        this.z = z;
        this.w = w;
        this.speed1 = speed;
        this.road = road;
    }


    public void move(){
        z=z-road.player.speed+speed1;
    }
}
