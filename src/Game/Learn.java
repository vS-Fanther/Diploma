package Game;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Learn extends JPanel {
	BufferedImage bk;
	static JFrame frame = new JFrame("Keyboard Trainer");
	Learn() {
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Main.frame.setVisible(true);
			}
		});
	this.setLayout(null);
	try {
        bk = ImageIO.read(new File("field/learnMain.jpg"));
    } catch(IOException ex) {
        ex.printStackTrace();
    }
	repaint();
	JButton button = new JButton(" ");
	int rejection = 180;
	button.setBounds(Field.size.width-rejection, 0, rejection, Field.size.height);
	button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	frame.setVisible(false);
        	//LearnButtons.LBF.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        	LearnButtons.LBF.setSize(Field.size);
        	LearnButtons.LBF.setResizable(false);
        	LearnButtons.LBF.add(new LearnButtons());
        	LearnButtons.LBF.setVisible(true);
        	LearnButtons.LBF.setExtendedState(Frame.MAXIMIZED_BOTH);
        }
    });
	button.setContentAreaFilled(false);
	button.setFocusPainted(false);
	button.setBorderPainted(false);
	this.add(button);
}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	    if(bk != null)
	        g.drawImage(bk, 0, 0, Field.size.width, Field.size.height, this);
	}
}
