package by.framework.lab3;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Lab3Application {

	public static void main(String[] args) {
		// Если не задано ни одного аргумента командной строки -
		// Продолжать вычисления невозможно, коэффиценты неизвестны
		if (args.length==0) {
			System.out.println("Невозможно табулировать многочлен, для которого не задано ни одного коэффициента!");
			System.exit(-1);
		}
		// Зарезервировать места в массиве коэффициентов столько, сколько аргументов командной строки
		Double[] coefficients = new Double[args.length];
		int i = 0;
		try {
			// Перебрать аргументы, пытаясь преобразовать их в Double
			for (String arg: args) {
				coefficients[i++] = Double.parseDouble(arg);
			}
		}
		catch (NumberFormatException ex) {
			// Если преобразование невозможно - сообщить об ошибке и завершиться
			System.out.println("Ошибка преобразования строки '" +
					args[i] + "' в число типа Double");
			System.exit(-2);
		}
		
		// Создать экземпляр главного окна, передав ему коэффициенты
		MainFrame frame = new MainFrame(coefficients);
		// Задать действие, выполняемое при закрытии окна
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(frame);
		
		frame.setVisible(true);
	}
	
}
