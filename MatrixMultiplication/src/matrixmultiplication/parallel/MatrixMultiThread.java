/*
 * The MIT License
 *
 * Copyright 2016 Russell Gilmore.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package matrixmultiplication.parallel;


/**
 * @author Russell Gilmore
 */
public class MatrixMultiThread {

    public static final int MAX_NUM_OF_THREADS = 9;

    public static void main(String args[]) {

        int row;
        int col;
        int MatrixA[][] = {{4, 3}, {2, 1}};
        int MatrixB[][] = {{5, 6}, {7, 8}};
        int MatrixC[][] = new int[2][2];
        int numOfThreads = 0;
        Thread[] thread = new Thread[MAX_NUM_OF_THREADS];
        long timeStart = System.nanoTime();
        try {
            for (row = 0; row < 2; row++) {
                for (col = 0; col < 2; col++) {
                    thread[numOfThreads] = new Thread(new Threading(row, col, MatrixA, MatrixB, MatrixC));
                    thread[numOfThreads].start();
                    thread[numOfThreads].join();
                    numOfThreads++;
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Error!");
        }
        long timeEnd = System.nanoTime();
    
        
        System.out.println(" Resulting Matrix C: ");
        for (row = 0; row < 2; row++) {
            for (col = 0; col < 2; col++) {
                System.out.print("  " + MatrixC[row][col]);
            }
            System.out.println();
            
        }
        System.out.println("Parallel Approach is " + (timeEnd - timeStart) + " Nanoseconds.");

    }

}
