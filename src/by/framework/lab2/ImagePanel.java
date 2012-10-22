package by.framework.lab2;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel {
	
	JLabel formulaImage = new JLabel();
	
	public ImagePanel() {
		super();
		setSize(600, 50);
		add(formulaImage);
	}
	
	public void updateImage(int id) throws IOException {
		File imageFile = 
				new File("resources/" + id + ".bmp");
		Image image;
		image = ImageIO.read(imageFile);
		ImageIcon icon = new ImageIcon(image);
		formulaImage.setIcon(icon);
	}

}
