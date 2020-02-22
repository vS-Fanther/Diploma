package Game;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class KeyManager {

    private final int amount = 42;
    private ImageManager[] values;

    public KeyManager() {
        values = new ImageManager[amount];
        int j = 0;
        try {
            for (char i = '0'; i <= 'z'; i++) {
                if(i > '9' && i < 'a')
                    i = 'a';
                values[j] = new ImageManager(ImageIO.read(new File("keys/" + i + ".png")), "keys/" + i + ".png");
                j++;
            }
            char[] tmp = {',','.',';','\'','[',']'};
            for(char i : tmp) {
                values[j] = new ImageManager(ImageIO.read(new File("keys/" + i + ".png")), "keys/" + i + ".png");
                j++;
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public char getKey(ImageManager img) {
        char s = 0;
        for(ImageManager i : values) {
            if(i.getSource().toCharArray()[5] == img.getSource().toCharArray()[5]) {
                s = i.getKey();
                break;
            }
        }
        return s;
    }

    public ImageManager getRandomKey() {
        return values[new Random().nextInt(amount)];
    }
}
