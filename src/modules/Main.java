package modules;

import java.awt.Color;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame f=new JFrame();
		Game game=new Game();
		f.setBounds(10,10,905,700);
		f.setBackground(Color.BLACK);
		f.setResizable(false);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(game);
	}
}
