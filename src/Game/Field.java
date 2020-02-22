package Game;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Field extends JPanel implements Runnable {

    public final static Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    public final static int ground = 600;
    private BufferedImage bk;
    private Hero hero;
    private Enemy enemy;
    private KeyPanel keyPanel;
    private Clip clip;
    private AudioInputStream ais;
    private long st, end;
    private long start;
    private int curr = 0, enemySpeed, enemyDelay = 600, heroSpeed;
    private static boolean running = true;
    private int rec;
    private Record record;

    Field(int heroSpeed, int enemySpeed) throws FileNotFoundException {
        running = true;
        GameMenu.GameMenu();
        this.heroSpeed = heroSpeed;
        this.enemySpeed = enemySpeed;
        Record record = new Record();
        rec = record.read("E:/Diploma/Record/Record.txt");
        st = System.currentTimeMillis()/1000L;
        start = System.currentTimeMillis();
        setSize(size);
        setFocusable(true);
        requestFocus();
        keyPanel = new KeyPanel();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    if (e.getKeyChar() == keyPanel.getFirst().getKey()) {
                        ais = AudioSystem.getAudioInputStream(new File("music/right.wav"));
                        clip = AudioSystem.getClip();
                        clip.open(ais);
                        clip.setFramePosition(0);
                        clip.start();
                        hero.setPlusX(heroSpeed);
                        keyPanel.update();
                    } else {
                        ais = AudioSystem.getAudioInputStream(new File("music/wrong.wav"));
                        clip = AudioSystem.getClip();
                        clip.open(ais);
                        clip.setFramePosition(0);
                        clip.start();
                    }
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        hero = new Hero();
        enemy = new Enemy();
        try {
            bk = ImageIO.read(new File("field/background.png"));
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        new Thread(this).start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(bk != null)
            g.drawImage(bk, 0, 0, size.width, size.height, this);
        hero.paint(g);
        enemy.paint(g);
        keyPanel.paint(g);
    }

    @Override
    public void run() {
        while (running) {
            repaint();
            if ((System.currentTimeMillis() - start) > enemyDelay) {
                curr++;
                if (curr >= enemy.getAmount())
                    curr = 0;
                if (curr == 2 || curr == 4 || curr == 0)
                    enemy.setPlusX(enemySpeed);
                enemy.setCurrFrame(curr);
                start = System.currentTimeMillis();
            }
            if(hero.getX() + hero.SIDE / 2 <= enemy.getX()) {
                running = false;
                try {
                    ais = AudioSystem.getAudioInputStream(new File("music/lose.wav"));
                    clip = AudioSystem.getClip();
                    clip.open(ais);
                    clip.setFramePosition(0);
                    clip.start();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(this, "Вы проиграли!", "Game Over!",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            if(hero.getX() + hero.SIDE / 2 > Field.size.width) {
                running = false;
                try {
                    ais = AudioSystem.getAudioInputStream(new File("music/win.wav"));
                    clip = AudioSystem.getClip();
                    clip.open(ais);
                    clip.setFramePosition(0);
                    clip.start();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
                end = System.currentTimeMillis()/1000L - st;
                if(rec > end) {
                    try {
                        record.write(end, "E:/Diploma/Record/Record.txt");
                        rec = record.read("E:/Diploma/Record/Record.txt");
                    } catch (IOException e) {
                        e.printStackTrace();
                    } }
                JOptionPane.showMessageDialog(this, "Вы выиграли! За "+(end) + " секунд" + "\nРекорд = " + rec + " секунд", "Game Over!",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            try {
                Thread.currentThread();
                Thread.sleep(60);
            } catch(InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    public static void stop() {
        running = false;
    }

    public static void Continue() {
        running = true;
    }
}
