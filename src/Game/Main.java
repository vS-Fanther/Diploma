package Game;

import javax.swing.*;
import java.awt.*;


public class Main extends JPanel{


    private static final long serialVersionUID = 1L;
    static JFrame frame = new JFrame("Keyboard Trainer");

    public static void main(String[] args) {

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(Field.size);
        frame.setResizable(false);
        frame.add(new Start());
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    }
}
