package by.framework.lab4;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Window extends JFrame {
	
	String ss = "0";
	int k = Integer.parseInt(ss);

	private JMenuBar menuBar;
	private JFileChooser fileChooser = null;

	PaintPanel paintPanel;

	JCheckBoxMenuItem showAxisMenu;
	JCheckBoxMenuItem showMarkersMenu;
	JCheckBoxMenuItem setRotateMenu;
	JCheckBoxMenuItem findSqMenu;
	JCheckBoxMenuItem showMarkedPointsMenu;

	public Window() {
		this("Постоение графика по точкам.");
	}

	public Window(String title) {
		super(title);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu fileMenu = new JMenu("Файл");
		menuBar.add(fileMenu);

		fileMenu.add(new AbstractAction("Открыть файл") {

			@Override
			public void actionPerformed(ActionEvent event) {

				if(fileChooser == null) {
					fileChooser = new JFileChooser();
					fileChooser.setCurrentDirectory(new File("."));
				}
				if (fileChooser.showOpenDialog(Window.this) ==
						JFileChooser.APPROVE_OPTION)
					readDataFile(fileChooser.getSelectedFile());
			}

		});

		fileMenu.add(new AbstractAction("Выход") {	
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				System.exit(0);
			}
		});

		JMenu viewMenu = new JMenu("Вид");
		menuBar.add(viewMenu);

		showAxisMenu = new JCheckBoxMenuItem("Показывать оси координат");
		showAxisMenu.getModel().setSelected(true);
		showAxisMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paintPanel.setShowAxis(showAxisMenu.getModel().isSelected());
			}
		});
		viewMenu.add(showAxisMenu);

		showMarkersMenu = new JCheckBoxMenuItem("Показывать маркеры");
		showMarkersMenu.getModel().setSelected(true);
		showMarkersMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paintPanel.setShowMarkers(showMarkersMenu.getModel().isSelected());
				showMarkedPointsMenu.setEnabled(showMarkersMenu.getModel().isSelected());			
			}
		});
		viewMenu.add(showMarkersMenu);

		showMarkedPointsMenu = new JCheckBoxMenuItem("Пометить точки");
		showMarkedPointsMenu.getModel().setSelected(false);
		showMarkedPointsMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paintPanel.setShowMarkedPoints(showMarkedPointsMenu.getModel().isSelected());
			}
		});
		viewMenu.add(showMarkedPointsMenu);

		setRotateMenu = new JCheckBoxMenuItem("Повернуть");
		setRotateMenu.getModel().setSelected(false);
		setRotateMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paintPanel.setRotate(setRotateMenu.getModel().isSelected());
			}
		});
		viewMenu.add(setRotateMenu);

		findSqMenu = new JCheckBoxMenuItem("Найти замкнутые области");
		findSqMenu.getModel().setSelected(false);
		findSqMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paintPanel.setFindSq(findSqMenu.getModel().isSelected());
			}
		});
		viewMenu.add(findSqMenu);
		
		/*JMenu appearanceMenu = new JMenu("Appearance");
		viewMenu.add(appearanceMenu);
		ButtonGroup appearanceGroup = new ButtonGroup();
		
		UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
		for(final UIManager.LookAndFeelInfo look : looks) {
			JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem(look.getName());
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						UIManager.setLookAndFeel(look.getClassName());
						SwingUtilities.updateComponentTreeUI(Window.this);
					} catch (Exception ex) {}
				}
			});
			appearanceGroup.add(menuItem);
			appearanceMenu.add(menuItem);
			System.out.println(look.getClassName());
		}
		appearanceGroup.setSelected(
				appearanceGroup.getElements().nextElement().getModel(), true);*/

		if(paintPanel == null) {
			 paintPanel = new PaintPanel();
		}
		paintPanel.setShowAxis(showAxisMenu.getModel().isSelected());
		paintPanel.setShowMarkers(showMarkersMenu.getModel().isSelected());
		getContentPane().add(paintPanel);

//		System.out.println(menuBar.getSize().getHeight());
		System.out.println(getContentPane().getWidth() + " " + getContentPane().getHeight());
		
		readDataFile(new File("/home/alexandr/workspace/JavaLabs/x5.bin"));
	}

	private void readDataFile(File file){
		DataInputStream in;
		try {
			in = new DataInputStream(new FileInputStream(file));
			ArrayList<GraphicPoint> points = new ArrayList<GraphicPoint>();
			while(in.available()>0) {
				GraphicPoint point = new GraphicPoint(in.readDouble(), in.readDouble());
				points.add(point);				
			}
//			paintPanel.setRotate(false);
//			setRotateMenu.getModel().setSelected(false);
			paintPanel.paintGraphic(points);
			System.out.println(getContentPane().getWidth() + " " + getContentPane().getHeight());
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(Window.this,
					"Ошибка открытия файла", 
					"Файл не найден",
					JOptionPane.WARNING_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(Window.this, 
					"Ошибка чтения координат точек из файла",
					"Ошибка загрузки данных",
					JOptionPane.WARNING_MESSAGE);
		}
	}

}
