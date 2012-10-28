package by.framework.lab3;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class GornerTableCellRenderer implements TableCellRenderer {

	private JPanel panel = new JPanel();
	private JLabel label = new JLabel();
	// Ищем ячейки, строковое представление которых совпадает с needle
	// (иголкой). Применяется аналогия поиска иголки в стоге сена, в роли
	// стога сена - таблица
	private String needle = null;

	private String start = null;
	private String finish = null;
	private double doubleStart = 0;
	private double doubleFinish = 0;

	private Formatter formatter = Formatter.getInstanse();

	public GornerTableCellRenderer() {
		//Разместить надпись внутри панели
		panel.add(label);
		//Установить выравнивание надписи по левому краю панели
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
	}

	public Component getTableCellRendererComponent(JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row, int col) {

		if(((row+col)&1) == 0) {
			panel.setBackground(Color.BLACK);
			label.setForeground(Color.WHITE);
		} else {
			panel.setBackground(Color.WHITE);
			label.setForeground(Color.BLACK);
		}

		//Преобразовать double в строку с помощью форматировщика
		String formattedDouble = formatter.getFormatter().format(value);
		//Установить текст надписи равным строковому представлению числа
		label.setText(formattedDouble);
		if(needle != null) {
			if ((col == 1 || col == 2) && needle.equals(formattedDouble)) {
				panel.setBackground(Color.RED);
			}
		} else if(start != null && finish != null) {
			double v = Double.parseDouble(formattedDouble);
			if ((col == 1 || col == 2) && doubleStart <= v && v <= doubleFinish) {
				panel.setBackground(Color.GREEN);
			}
		}
		return panel;
	}

	public void setNeedle(String needle) {
		this.needle = needle;
	}

	public void setRange(String start, String finish) {
		this.start = start;
		this.finish = finish;
		if(start != null && finish != null) {
			double s = Double.parseDouble(start);
			double f = Double.parseDouble(finish);
			if(s > f) {
				double t = s;
				s = f;
				f = t;
			}
			doubleStart = s;
			doubleFinish = f;
		}
	}
}
