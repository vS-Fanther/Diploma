package Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LearnButtons extends JPanel{
	static JFrame learnFrame = new JFrame("Learning");
	static JFrame LBF = new JFrame("KeyBoard Trainer");
	BufferedImage bk1;
		LearnButtons() {
			learnFrame.setVisible(false);
			LBF.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					Learn.frame.setVisible(true);
				}
			});
			learnFrame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					LBF.setVisible(true);
				}
			});
			try {
	        bk1 = ImageIO.read(new File("field/afterMain.jpg"));
	    } catch(IOException ex) {
	        ex.printStackTrace();
	    }
		this.setLayout(null);
		JButton easy = new JButton(new Action("Легко", "Texts/Lesson 1.txt"));
		JButton normal = new JButton(new Action("Средне", "Texts/Lesson 2.txt"));
		JButton hard = new JButton(new Action("Тяжело", "Texts/Lesson 3.txt"));
		JButton Audio = new JButton("Аудирование");
		Audio.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
				learnFrame.setVisible(true);
	        	LBF.setVisible(false);
	    		learnFrame.setSize(1000, 250);
	    		learnFrame.setVisible(true);
	    		learnFrame.setResizable(false);
				learnFrame.add(new Audio("Texts/2.wav"));
	        }
	    });
		JButton Text = new JButton(new Action("Предложение", "Texts/Texts.txt"));
		easy.setBounds(50, 50, 200, 100);
		normal.setBounds(50, 200, 200, 100);
		hard.setBounds(Field.size.width-250, 50, 200, 100);
		Audio.setBounds(Field.size.width-250, 200, 200, 100);
		Text.setBounds(Field.size.width/2-100, 100, 200, 100);
		this.add(easy);
		this.add(normal);
		this.add(hard);
		this.add(Audio);
		this.add(Text);
		repaint();
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	    if(bk1 != null)
	        g.drawImage(bk1, 0, 0, Field.size.width, Field.size.height, this);
	}
}

class Action extends AbstractAction{
	String filename, name;
	Action(String name, String filename) {
		super(name);
		this.name = name;
		this.filename = filename;
	}

	public void actionPerformed(ActionEvent arg0) {
		LearnButtons.learnFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		LearnButtons.learnFrame.setVisible(true);
		LearnButtons.LBF.setVisible(false);
		LearnButtons.learnFrame.setSize(1000, 250);
		LearnButtons.learnFrame.setVisible(true);
		LearnButtons.learnFrame.setResizable(false);
		try {
			LearnButtons.learnFrame.add(new Lessons(filename));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
