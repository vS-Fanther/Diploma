package Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Hero  {

    private BufferedImage[] frames;
    private int amount = 9, curr = 0;
    public final static int SIDE = 120;
    private int x = Field.size.width / 2 - SIDE / 2;

    public Hero() {
        try {
            frames = new BufferedImage[amount];
            for (int i = 0; i < amount; i++)
                frames[i] = ImageIO.read(new File("hero/hero_" + (i + 1) + ".png"));
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setPlusX(int x) {
        this.x += x;
    }

    public int getX() {
        return x;
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        if(frames[curr] != null)
            g2.drawImage(frames[curr], x, Field.ground - SIDE - SIDE / 2, SIDE, SIDE, null);
        curr++;
        if(curr >= amount)
            curr = 0;
    }
}
