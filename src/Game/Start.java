package Game;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;



public class Start extends JPanel {
	JButton game = new JButton(" ");
	JButton learn = new JButton("   ");
	BufferedImage bk;
	static JFrame gameFrame;
	
	Start() {
	gameFrame = new JFrame("Keyboard Trainer");
	this.setLayout(null);
	try {
        bk = ImageIO.read(new File("field/main.jpg"));
    } catch(IOException ex) {
        ex.printStackTrace();
    }
	game.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
				try {
					//gameFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					gameFrame.setSize(Field.size);
					gameFrame.add(new Field(10,20));
					gameFrame.setResizable(false);
					gameFrame.setVisible(true);
					gameFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
					Main.frame.setVisible(false);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				gameFrame.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						Main.frame.setVisible(true);
					}
				});
        }
    });
	repaint();
	learn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	Main.frame.setVisible(false);
        //	Learn.frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        	Learn.frame.setSize(Field.size);
        	Learn.frame.add(new Learn());
        	Learn.frame.setResizable(false);
        	Learn.frame.setVisible(true);
        	Learn.frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        }
	});
	learn.setBounds(65, 35, 280, 130);
	game.setBounds(1005, 585, 285, 115);
	game.setBorderPainted(false);
	game.setContentAreaFilled(false);
	game.setFocusPainted(false);
	learn.setBorderPainted(false);
	learn.setContentAreaFilled(false);
	learn.setFocusPainted(false);
	this.add(learn);
	this.add(game);
	}
	

public void paintComponent(Graphics g) {
	super.paintComponent(g);
    if(bk != null)
        g.drawImage(bk, 0, 0, Field.size.width, Field.size.height, this);

}
}
