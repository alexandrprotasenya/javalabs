package by.framework.lab3;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

public class Formatter {

	private static Formatter instanse;

	private DecimalFormat formatter;

	private Formatter() {
		formatter = (DecimalFormat)NumberFormat.getInstance();
		formatter.setMaximumFractionDigits(5);
		formatter.setGroupingUsed(false);
		DecimalFormatSymbols dottedDouble =
				formatter.getDecimalFormatSymbols();
		dottedDouble.setDecimalSeparator('.');
		formatter.setDecimalFormatSymbols(dottedDouble);
	}

	public static Formatter getInstanse() {
		if(instanse == null) {
			synchronized (Formatter.class) {
				if(instanse == null) {
					instanse = new Formatter();
				}
			}
		}
		return instanse;
	}
	
	public DecimalFormat getFormatter() {
		return formatter;
	}

}
