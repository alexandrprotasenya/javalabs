package by.framework.lab3;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class GornerTableModel extends AbstractTableModel {

	private Double step;
	private Double to;
	private Double from;
	private Double[] coefficients;

	private Formatter formatter = Formatter.getInstanse();

	public GornerTableModel(Double from, Double to, Double step,
			Double[] coefficients) {
		this.from = from;
		this.to = to;
		this.step = step;
		this.coefficients = coefficients;
	}

	public Double getFrom() {
		return from;
	}

	public Double getTo() {
		return to;
	}

	public Double getStep() {
		return step;
	}

	public int getColumnCount() {
		// В данной модели два столбца
		return 4;
	}

	public int getRowCount() {
		// Вычислить количество точек между началом и концом отрезка
		// исходя из шага табулирования
		return new Double(Math.ceil((to-from)/step)).intValue()+1;
	}

	public Object getValueAt(int row, int col) {
		// Вычислить значение X как НАЧАЛО_ОТРЕЗКА + ШАГ*НОМЕР_СТРОКИ
		double x = from + step*row;
		if (col == 0) {
			// Если запрашивается значение 1-го столбца, то это X
			return x;
		} else if(col == 1) {
			double result = coefficients[0];
			for(int i = 1; i < coefficients.length; i++) {
				result = result*x + coefficients[i];
			}
			return result;
		} else if(col == 2) {
			double result = coefficients[coefficients.length - 1];
			for(int i = coefficients.length - 2; i >= 0; i--) {
				result = result*x + coefficients[i];
			}
			return result;
		} else {
			return Double.parseDouble(formatter.getFormatter().format(getValueAt(row, 1))) 
					- Double.parseDouble(formatter.getFormatter().format(getValueAt(row, 2)));
		}
	}

	public String getColumnName(int col) {
		switch (col) {
		case 0:
			// Название 1-го столбца
			return "Значение X";
		case 1:
			// Название 2-го столбца
			return "Значение многочлена 1";
		case 2:
			// Название 3-го столбца
			return "Значение многочлена 2";
		default:
			// Название 4-го столбца
			return "Разность";
		}
	}

	public Class<?> getColumnClass(int col) {
		// И в 1-ом и во 2-ом столбце находятся значения типа Double
		return Double.class;
	}

}
