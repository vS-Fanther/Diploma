package Game;

import java.awt.*;
import java.util.ArrayList;

public class KeyPanel {

    private ArrayList<ImageManager> keys;
    public final int maxKeys = 12, SIDE = 50;
    private KeyManager keyManager;

    public KeyPanel() {
        keys = new ArrayList<>();
        keyManager = new KeyManager();
        for(int i = 0; i < maxKeys; i++)
            keys.add(keyManager.getRandomKey());
    }

    public ImageManager getFirst() {
        return keys.get(0);
    }

    public void update() {
        keys.remove(0);
        keys.add(keyManager.getRandomKey());
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int x = Field.size.width / 2 - SIDE / 2;
        for(ImageManager i : keys) {
            if (i != null)
                g2.drawImage(i.getImg(), x, Field.ground + SIDE * 2 - 40, SIDE, SIDE, null);
            x += SIDE + 8;
        }
    }
}
