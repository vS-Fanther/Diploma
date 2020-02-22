package Game;

import java.awt.image.BufferedImage;

public class ImageManager {

    private BufferedImage img;
    private String source;

    public ImageManager(BufferedImage img, String source) {
        this.img = img;
        this.source = source;
    }

    public BufferedImage getImg() {
        return img;
    }

    public String getSource() {
        return source;
    }

    public char getKey() {
        char[] temp = source.toCharArray();
        return temp[5];
    }
}
