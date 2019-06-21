package matrixmultiplication.serial;

public class Serial {

    double[][] matrixOne = {{4.00, 3.00}, {2.00, 1.00}};
    double[][] matrixTwo = {{5.00, 6.00}, {7.00, 8.00}};
    private static long timeStart;
    
    public static void main(String[] args) {
        Serial matrix = new Serial();
        double[][] result = Multiply(matrix.matrixOne, matrix.matrixTwo);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
        long timeEnd = System.nanoTime();
        System.out.println("Serial Approach is " + (timeEnd - timeStart) + " Nanoseconds.");

    }

    public static double[][] Multiply(double[][] matrixOne, double[][] matrixTwo) {
        
        timeStart = System.nanoTime();
        int aRows = matrixOne.length;
        int aColumns = matrixOne[0].length;

        int bColumns = matrixTwo[0].length;

        double[][] C = new double[aRows][bColumns];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                C[i][j] = 0.00000;
            }
        }
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                for (int k = 0; k < aColumns; k++) {
                    C[i][j] += matrixOne[i][k] * matrixTwo[k][j];
                }
            }
        }
        long timeEnd;
        
        return C;

    }

    
}
