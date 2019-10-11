package utils;

import java.text.DecimalFormat;

public class Gauss {

    private double pivot, mod_pivot, mod_aik;
    private double swap, m;
    private int pivot_row, n;

    /**
     * Method responsible for defining the pivot
     * First time pivot is a[0, 0]
     * @param a
     * @param b
     * @param n 
     */
    public void prepare(double[][] a, double[] b, int n) {        
        this.n = n;
        boolean error = false;

        for (int k = 0; k < n; k++) {
            pivot = a[k][k];
            pivot_row = k;

            // Looks for the highest value to make the pivot line
            for (int i = k + 1; i < n; i++) {
                mod_pivot = Math.abs(pivot);
                mod_aik = Math.abs(a[i][k]);

                if (mod_aik > mod_pivot) {
                    pivot = a[i][k];
                    pivot_row = i;
                }
            }
            
            if (pivot == 0) {
                error = true;
            }

            // Swap the lines to put the pivot line on top
            if (pivot_row != k) {
                for (int j = 0; j < n; j++) {
                    swap = a[k][j];
                    a[k][j] = a[pivot_row][j];
                    a[pivot_row][j] = swap;
                }
                swap = b[k];
                b[k] = b[pivot_row];
                b[pivot_row] = swap;
            }
        }
        
        if (error) {
            System.out.println("Error, pivot value = 0");
        } else {
            this.elimination(a, b);
        }
    }

    /**
     * Method responsible for turning the matrix into an upper triangular matrix
     * Defines a multiplier for each line below the pivot
     * @param a
     * @param b 
     */
    public void elimination(double[][] a, double[] b) {
        for (int k = 0; k < n; k++) {
            for (int i = k + 1; i < n; i++) {
                m = a[i][k] / a[k][k];
                a[i][k] = 0;
                for (int j = k + 1; j < n; j++) {
                    a[i][j] = a[i][j] - (m * a[k][j]);
                }
                b[i] = b[i] - (m * b[k]);
            }
        }
        
        this.solve(a, b);
    }

    /**
     * Get upper triangular matrix as parameter and find system roots
     * @param a
     * @param b 
     */
    public void solve(double[][] a, double[] b) {
        DecimalFormat df = new DecimalFormat("##.####");
        double[] x = new double[n];
        double s;
        for (int k = n - 1; k >= 0; k--) {
            s = 0;
            for (int j = k + 1; j < n; j++) {
                s += a[k][j] * x[j];
            }
            x[k] = (b[k] - s) / a[k][k];
        }

        // Print the upper triangular matrix
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                System.out.print(df.format(a[k][i]) + " | ");
                if (i == n - 1) {
                    System.out.println(df.format(b[k]));
                    System.out.println("\n");
                }
            }
        }

        // Print linear system roots
        for (int k = 0; k < n; k++) {
            if (x[k] == 0) {
                System.out.println("x" + (k + 1) + ": " + 0);
            } else {
                System.out.print("x" + (k + 1) + ": " + df.format(x[k]) + " | ");
            }
        }
    }
}
