package by.framework.lab2;

import java.io.IOException;

import javax.swing.JFrame;

public class WindowApplication {

	public static void main(String[] args) throws IOException {
		JFrame window = new Window();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

}
