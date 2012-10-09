package by.framework.lab2;

import static java.lang.Math.*;;

public final class Calculate {
	public static double calculete1(double x, double y, double z) {
		double s = sin(log(y) + sin(PI*y*y));
		double sq = pow(x*x + sin(z) + exp(cos(z)), 1./4.);
		return s * sq;
	}
	
	public static double calculete2(double x, double y, double z) {
		double s1 = cos(exp(x));
		double s2 = pow(log(1 + y), 2.);
		double s3 = sqrt(exp(cos(x)) 
				+ pow(sin(PI*z), 2.));
		double s4 = sqrt(1./x);
		double s5 = cos(y*y);
		return pow(s1 + s2 +s3 + s4 + s5, sin(z));
	}
}
