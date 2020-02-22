package Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Enemy {

    private BufferedImage[] frames;
    private int amount = 7, curr = 0;
    private int x = 0;
    public final static int HEIGHT = 300;

    public Enemy() {
        try {
            frames = new BufferedImage[amount];
            for(int i = 0; i < amount; i++)
                frames[i] = ImageIO.read(new File("enemy/enemy_" + i + ".png"));
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setCurrFrame(int curr) {
        this.curr = curr;
    }

    public int getAmount() {
        return amount;
    }

    public void setPlusX(int x) {
        this.x += x;
    }

    public int getX() {
        return x + 130;
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if(frames[curr] != null)
            if(curr == 3 || curr == 6)
                g2.drawImage(frames[curr], x, Field.ground - HEIGHT, 150, HEIGHT, null);
            else
                g2.drawImage(frames[curr], x, Field.ground - HEIGHT, 100, HEIGHT, null);
    }
}
