package by.framework.lab2;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Window extends JFrame {
	private static final int WIDTH = 600;
	private static final int HEIGHT = 480;
	
	private static final String WINDOW_TITLE = "Calculate";
	
	public Window() throws IOException {
		super(WINDOW_TITLE);
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		
		setLocation((kit.getScreenSize().width - WIDTH)/2,
				(kit.getScreenSize().height - HEIGHT)/2);
		
		JPanel panel = new CalculatePanel();
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(panel);
		add(panel);
	}
}
