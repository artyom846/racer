
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.Image;


import java.io.*;
import javax.swing.*;


class Helper implements Serializable {

    int x;
    int y;
    int dy;
    int speed ;
    int acceleration; //ускорение
    int way;

    public Helper(int x, int y, int dy, int speed, int acceleration, int way) {
        this.x = x;
        this.y = y;
        this.dy = dy;
        this.speed = speed;
        this.acceleration = acceleration;
        this.way = way;
    }

}
public class Player  {
    public static final int max_speed = 50;
    public static final int max_top = 30;
    public static final int max_bottom = 500;

    Image img = new ImageIcon("res/Player.png").getImage();

    public Rectangle getRect (){
        return new Rectangle(x,y,160,56);
    }

    int speed = 0;
    int acceleration = 0; //ускорение
    int way =0; // путь

    int layer1=0;
    int layer2=1200;

    int x = 10;
    int y = 10;
    int dy = 0;





    public void move(){
        way+=speed;
        speed+=acceleration;

        if(speed<=5){speed = 5;}
        if(speed>=max_speed){speed = max_speed;}

        y-=dy;

        if (y >= max_bottom){y=max_bottom;}
        if (y <= max_top){y=max_top;}
        if (layer2 - speed <= 0 )
        {
            layer1 = 0;
            layer2 = 1200;
        }
        else {
            layer1 -= speed;
            layer2 -= speed;
        }
    }

    public void save() throws IOException{
            FileOutputStream fot = new FileOutputStream("save.txt");
            ObjectOutputStream out = new ObjectOutputStream(fot);
            Helper helper = new Helper(x,y,dy,speed,acceleration,way);

            out.writeObject(helper);

            out.close();
    }
    public void load() throws Exception {
        FileInputStream fis = new FileInputStream("save.txt");
        ObjectInputStream oin = new ObjectInputStream(fis);
        Helper helper = (Helper) oin.readObject();
        this.x= helper.x;
        this.y= helper.y;
        this.dy= helper.dy;
        this.speed= helper.speed;
        this.acceleration= helper.acceleration;
        this.way= helper.way;
    }
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            acceleration = -1;
        }
        if (key == KeyEvent.VK_RIGHT) {
            acceleration = 1;
        }
        if (key == KeyEvent.VK_UP) {
            dy = 3;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = -3;
        }

    }



    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            acceleration = 0;
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
        if (key == KeyEvent.VK_S) {
            try {
                save();

            }
            catch(IOException z){
                System.out.println("g");
            }
        }

        if (key == KeyEvent.VK_L) {
            try {
                load();
            }
            catch(Exception z){
                System.out.println("Bad");
            }
        }
    }
}
