package utils;

public class Main {

    public static void main(String[] args) {
        double[][] a = {
            {1, 0, 0},
            {1, -2, 4},
            {1, 0.5, 0.25}
        };

        double[] b = {2, 0, -3};
        int n = 3;

        Gauss gauss = new Gauss();
        gauss.prepare(a, b, n);
    }

}
