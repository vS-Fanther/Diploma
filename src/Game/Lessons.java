package Game;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Lessons extends JPanel {
	
	InputStreamReader fr;
	BufferedReader reader;
	String line;
	int mistakes = 0;
	JLabel text;
	Lessons(String filename) throws IOException {
		fr = new InputStreamReader(new FileInputStream(filename), "UTF-8");
		reader = new BufferedReader(fr);
		line  = reader.readLine();
		this.setLayout(null);
		JLabel label = new JLabel("Введите:");
		label.setBounds(275, 10, 100, 20);
		this.add(label);
		text = new JLabel(line);
		Font font = new Font("TimesRoman", Font.BOLD, 15);
		text.setFont(font);
		text.setBounds(10, 40, Field.size.width, 40);
		this.add(text);
		JTextField tf = new JTextField("", 400);
		tf.setBounds(40, 90, 500, 80);
		tf.addKeyListener(new KeyListener() {
		    public void keyPressed(KeyEvent e) {
		    	if(e.getKeyCode() == KeyEvent.VK_ENTER) {
		    		String a = tf.getText();
		        	if(a.equals(line)) {
		        		if (!line.matches("(?i).*[a-zа-я].*")) line = null;
		        		if(line != null) {
		        			try {
								line = reader.readLine();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
		        		tf.setText("");
		        		text.setText(line);
		        		if(line.equals(" ")) line = null;
		        		}
		        		else {
		        			text.setText("Поздравляю.");
		        			tf.setText("Вы выиграли с кол-вом ошибок: " + mistakes);
		        		}
		        	}
		        	else {
		        		JOptionPane.showMessageDialog(null, "Попробуйте еще раз", "Ошибка", JOptionPane.INFORMATION_MESSAGE);
		        		mistakes++;
		        	}
		    	}
		    }

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		this.add(tf);
		JButton next = new JButton("Далее");
		next.setBounds(250, 175, 100, 20);
		next.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	String a = tf.getText();
	        	if(a.equals(line)) {
	        		if (!line.matches("(?i).*[a-zа-я].*")) line = null;
	        		if(line != null) {
	        			try {
							line = reader.readLine();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
	        		tf.setText("");
	        		text.setText(line);
	        		if(line.equals(" ")) line = null;
	        		}
	        		else {
						text.setText("Поздравляю.");
						tf.setText("Вы выиграли с кол-вом ошибок: " + mistakes);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Попробуйте еще раз", "Ошибка", JOptionPane.INFORMATION_MESSAGE);
					mistakes++;
				}
	        }
	    });
		this.add(next);
	}
}
