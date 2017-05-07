import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;


public class Road extends JPanel implements ActionListener,Runnable{



    Timer mainTimer = new Timer(20,this);

    Image img = new ImageIcon("res/doroga.png").getImage();

    Player player = new Player();


    Thread enemiesFactory = new Thread(this);

    List<Enemy> enemies = new ArrayList<Enemy>();

    public Road(){
        mainTimer.start();
        enemiesFactory.start();
        addKeyListener(new MyKeyAdapter());
        setFocusable(true);
    }




    @Override
    public void run() {
        while (true){
            Random rand = new Random();
            try {
                Thread.sleep(rand.nextInt(8000));
                enemies.add(new Enemy(1300,rand.nextInt(530),rand.nextInt(30),this));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class MyKeyAdapter extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            player.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){player.keyReleased(e);
        }

    }



    public void paint(Graphics g){
        g = (Graphics2D) g;
        g.drawImage(img,player.layer1,0,null);
        g.drawImage(img,player.layer2,0,null);
        g.drawImage(player.img, player.x,player.y,null);

        Iterator<Enemy> i = enemies.iterator();
        while (i.hasNext()){
            Enemy e = i.next();
            if(e.z>=2400 || e.z<-2400){
                i.remove();
            }
            else {
                e.move();
                g.drawImage(e.img, e.z, e.w, null);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        player.move();
        repaint();
        testCollisionWithEnemies();
    }

    private void testCollisionWithEnemies() {
        Iterator<Enemy> i = enemies.iterator();
        while (i.hasNext()){
            Enemy e = i.next();
            if (player.getRect().intersects(e.getRect())){
                JOptionPane.showMessageDialog(null,"GAME OVER");
                System.exit(1);
            }
        }
    }





}
