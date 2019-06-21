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
package bearfish.core;

import java.util.Scanner;

/**
 * This class is used as an interface between the user and the application. It
 * handles the what size is being used for the dimensions of the river and how
 * many iterations should be used in the simulation.
 *
 * @author Russell Gilmore
 */
public class LifeLoop {

    private static Scanner scan;
    private int turns;

    /**
     * Initializes scanner.
     */
    public LifeLoop() {
        scan = new Scanner(System.in);

    }

    /**
     * Serves as the interface and prints out instructions for the user to
     * follow. Handles the number of iterations that the simulation will
     * perform.
     */
    public void loop() {
        System.out.println("Welcome to Fish Genocide Simulator Version 1.0");
        System.out.println("Step 1: Input a size for the river you wish your fish to die in (10-30 for good results!).");

        int size;
        do {
            System.out.println("Please enter a positive number (10-30 for good results!).");
            while (!scan.hasNextInt()) {
                System.out.println("I hate you. Use a number!");
                scan.next();
            }
            size = scan.nextInt();
        } while (size <= 0);

        River area = new River(size);
        area.populate();
        area.print();
        System.out.println("Starting Phase");
        System.out.println("Contains " + River.bearCount + " Bears, " + River.fishCount + " Fish, and " + River.empty + " Empty Spaces.");
        System.out.println("Step 2: Input a number of interations you wish to run to see how many fish die.(Higher Number = Less Survivors)");
        String exit = "";
        int loops = 0;
        do {
            System.out.print("Number of interations = ");
            do {
                while (!scan.hasNextInt()) {
                    System.out.println("I hate you. Use a number!");
                    scan.next();
                }
                loops = scan.nextInt();
                for (int i = 0; i < loops; i++) {
                    area.moveAll();
                }
                area.print();
                System.out.println("Contains " + River.bearCount + " Bears, " + River.fishCount + " Fish, and " + River.empty + " Empty Spaces.");
                System.out.println(River.fishKilled + " Fish Died... ");
            } while (loops <= 0);
            System.out.println("Type 'exit' to close the application");
            System.out.println("Type 'continue' to continue the simulation.");
            exit = scan.next();
        } while (!"exit".equals(exit));
        System.out.println("Remember.");
    }

    /**
     * Starts the application.
     *
     * @param args
     */
    public static void main(String[] args) {
        LifeLoop loop = new LifeLoop();
        loop.loop();

    }

}
