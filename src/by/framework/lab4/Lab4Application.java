package by.framework.lab4;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class Lab4Application {
	
	private static final int WIDTH = 700;
	private static final int HEIGHT = 500;

	public static void main(String[] args) {
		JFrame window = new Window();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		window.setLocation(
				(kit.getScreenSize().width - WIDTH)/2,
				(kit.getScreenSize().height - HEIGHT)/2
				);
//		window.setSize(kit.getScreenSize().width/2, kit.getScreenSize().height/2);
		window.setSize(WIDTH, HEIGHT);
		
//		try {
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
//			SwingUtilities.updateComponentTreeUI(window);
//		} catch (Exception e) {}
	}

}
