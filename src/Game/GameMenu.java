package Game;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class GameMenu extends JPanel {


	public static void GameMenu() {

		JMenuBar menubar = new JMenuBar();

		JMenu game = new JMenu("Назад");
		JMenuItem mainMenu = new JMenuItem("На главную?");
		game.add(mainMenu);
		menubar.add(game);

		mainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Start.gameFrame.setVisible(false);
				Main.frame.setVisible(true);
			}
		});

		JMenu level = new JMenu("Уровень");
		JMenuItem easy = new JMenuItem(new MyAction("Легкий", 15, 20));
		JMenuItem medium = new JMenuItem(new MyAction("Средний", 15, 40));
		JMenuItem hard = new JMenuItem(new MyAction("Сложный", 15, 60));
		level.add(easy);
		level.add(medium);
		level.add(hard);
		menubar.add(level);

		JMenu help = new JMenu("Помощь");
		JMenuItem about = new JMenuItem("О игре");
		help.add(about);
		menubar.add(help);

		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Field.stop();
				JOptionPane.showMessageDialog(Start.gameFrame, "Created by Vadim Ryashentsev");
			}
		});

		JMenu exits = new JMenu("Выход");
		JMenuItem exit = new JMenuItem("Закрыть");
		exits.add(exit);
		menubar.add(exits);
		Start.gameFrame.setJMenuBar(menubar);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.exit(0);
			}
		});
	}
}

class MyAction extends AbstractAction {

	int heroSpeed, enemySpeed;

	MyAction(String name, int heroSpeed, int enemySpeed) {
		super(name);
		this.heroSpeed = heroSpeed;
		this.enemySpeed = enemySpeed;
	}



	public void actionPerformed(ActionEvent arg0) {
		Field.stop();
		Start.gameFrame.removeAll();
		Start.gameFrame.dispose();
		try {
			Start.gameFrame = new JFrame("KeyBoard Trainer");
			Start.gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			Start.gameFrame.setSize(Field.size);
			Start.gameFrame.add(new Field(heroSpeed, enemySpeed));
			Start.gameFrame.setResizable(false);
			Start.gameFrame.setVisible(true);
			Start.gameFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
