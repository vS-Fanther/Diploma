package Game;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class Audio extends JPanel {


	AudioInputStream ais;
	Clip clip;
	String filename;
	JButton next, help, start;

	Audio(String filename) {

		this.filename = filename;
		String b = "Голодные собаки увидели в реке шкуры, которые там отмачивались, но не могли их достать и тогда сговорились сначала выпить воду, а потом уже добраться до шкур.\n Принялись они пить, но только лопнули, а до шкур не добрались.\nТак иные люди в надежде на прибыль берутся за опасные труды, но скорее губят себя, чем добиваются желаемого.";
		this.setLayout(null);
		JLabel label = new JLabel("Аудирование. Писать с запятыми и точками.");
		label.setBounds(275, 10, 600, 40);
		this.add(label);
		JTextField tf = new JTextField("", 400);
		tf.setBounds(40, 90, 500, 80);
		tf.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(b.equals(tf.getText()))
					{
						label.setText("Поздравляю");
						tf.setText("Вы справились !! ");
					} else label.setText("Проверьте на ошибки");
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

				if(b.equals(tf.getText()))
				{
					label.setText("Поздравляю");
					tf.setText("Вы справились !! ");
				} else label.setText("Проверьте на ошибки");
			}

		});
		this.add(next);
		JButton start = new JButton("Начать");
		start.setBounds(250, 60, 100, 20);
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					File soundFile = new File(filename);
					AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
					Clip clip = AudioSystem.getClip();
					clip.open(ais);
					clip.setFramePosition(0);
					clip.start();
				} catch (IOException | UnsupportedAudioFileException | LineUnavailableException exc) {
					exc.printStackTrace();
				}
			}

		});
		this.add(start);
		JButton help = new JButton("+");
		help.setBounds(400, 60, 100, 20);
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(LearnButtons.learnFrame, b);
			}
		});
		this.add(help);
	}
}
