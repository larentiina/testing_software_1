package larentina.task1;

public class Cos {

    public static double cosTaylor(double x, int n) {
        double result = 0.0;

        for (int i = 0; i < n; i++) {
            double term = Math.pow(-1, i) * Math.pow(x, 2 * i) / factorial(2 * i);
            result += term;
        }

        return result;
    }

    private static double factorial(int num) {
        if (num == 0) {
            return 1;
        }
        double result = 1;
        for (int i = 1; i <= num; i++) {
            result *= i;
        }
        return result;
    }
}
