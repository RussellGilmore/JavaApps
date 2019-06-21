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

import java.util.Random;

/**
 * This class is used to represent a river filled with bears and fish. The goal
 * of this is to simulate an environments where fish and bear breed and try to
 * inhabit and many of the elements as possible. This class handles the
 * interaction with all the objects as well as the initialization of the
 * objects. It also keeps track of multiple fields that yield information about
 * the environment.
 *
 * @author Russell Gilmore
 */
public class River {

    // Used as the river.
    private final Animal[][] RIVER;
    // Used to keep track of how many fish are alive.
    public static int fishCount;
    // Used to keep track of how many bear are alive.
    public static int bearCount;
    // Steve these comments are killing me like these fish.
    public static int fishKilled;
    // Keeps track of how many elements are empty.
    public static int empty;

    private final Random RAND;
    private final int SIZE;

    /**
     * The constructor takes in a size that is used to initialize the dimensions
     * of the array.
     *
     * @param size used for the number of rows and columns in the array.
     */
    public River(int size) {
        this.SIZE = size;
        RIVER = new Animal[SIZE][SIZE];
        RAND = new Random();

    }

    /**
     * This method is responsible for the initial filling of the RIVER with
     * object. It will randomly assign each element in the array with either a
     * Bear or Fish object. It will also randomly place nulls to be used as
     * future free space.
     */
    public void populate() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                int choice = RAND.nextInt(3);
                switch (choice) {
                    case 0:
                        RIVER[x][y] = new Fish("Fish", x, y);
                        fishCount++;
                        break;
                    case 1:
                        RIVER[x][y] = new Bear("Bear", x, y);
                        bearCount++;
                        break;
                    case 2:
                        RIVER[x][y] = null;
                        empty++;
                        break;
                }
            }
        }
    }

    /**
     * This method iterates through all the elements in the River. Fetches the
     * element and then compares it to the certain cases that could happen.
     *
     */
    public void moveAll() {
        // Handles if a move was made for an iteration.
        boolean madeMove = false;
        int xpos;
        int ypos;
        int tries = 0;
        // Goes through all positions in the river.
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (null != RIVER[x][y]) {
                    if (RIVER[x][y].getAnimal() != null) {
                        // Gets the type of object and checks to see if it made a move.
                        if ("Bear".equals(RIVER[x][y].getAnimal())) {
                            Bear thisBear = (Bear) RIVER[x][y];
                            while (madeMove == false && tries < 3) {
                                xpos = RAND.nextInt(SIZE);
                                ypos = RAND.nextInt(SIZE);
                                madeMove = bearHandleMove(checkMove(xpos, ypos), xpos, ypos, thisBear);
                                tries++;
                            }
                            // Reset
                            tries = 0;
                            // Gets the type of object and checks to see if it made a move.
                        } else if ("Fish".equals(RIVER[x][y].getAnimal())) {
                            Fish thisFish = (Fish) RIVER[x][y];
                            while (madeMove == false && tries < 3) {
                                xpos = RAND.nextInt(SIZE);
                                ypos = RAND.nextInt(SIZE);
                                madeMove = fishHandleMove(checkMove(xpos, ypos), xpos, ypos, thisFish);
                                tries++;
                            }
                            // Reset
                            tries = 0;
                        }
                    }
                }
            }
        }
    }

    /**
     * This method checks to see what is present at the new location. Used in
     * conjunction with the object handlers. Returns the state of the location
     * being examined.
     *
     * @param x The row.
     * @param y The column.
     * @return String that contains the state of the location.
     */
    public String checkMove(int x, int y) {
        if (RIVER[x][y] != null) {
            String state = RIVER[x][y].getAnimal();
            return state;
        }
        return "Nothing";
    }

    /**
     * This method is used to handle the bear move process. Each condition
     * handled is given a success state that is then returned to the moveAll()
     * method. It will return a boolean to determine if the move was a success.
     *
     * @param state what is at the new location the object is moving too.
     * @param x The row that is being examined.
     * @param y The column that is being examined.
     * @param thisBear The bear object that is being handled.
     * @return boolean the state of the moves success.
     */
    public Boolean bearHandleMove(String state, int x, int y, Bear thisBear) {
        System.out.println("Made it to bear handle");
        boolean success = false;
        int tempX;
        int tempY;
        //Checks the state to see how the move should be handled.
        switch (state) {
            // If bear it will try to find a place for a new bear.
            // Will return success for successful move.
            case "Bear":
                int limit = 0;
                int xpos;
                int ypos;
                // Checks attempts to place a new bear object.
                while (limit < 5) {
                    limit++;
                    xpos = RAND.nextInt(SIZE);
                    ypos = RAND.nextInt(SIZE);
                    if ("Nothing".equals(checkMove(xpos, ypos)) && thisBear.canBreed()) {
                        System.out.println("Make Bear");
                        RIVER[xpos][ypos] = thisBear.makeBear(xpos, ypos);
                        bearCount++;
                        empty--;
                        limit++;
                        // Successfully inserted a new bear object in a null position.
                        success = true;
                    }

                }
                break;
            // If fish it will replace for that bear.
            // Will return success for successful move.
            // Updates river stats.
            case "Fish":
                // Moves the bear to a new spot and kills a fish.
                thisBear.setX(x);
                thisBear.setY(y);
                thisBear.eatFish();
                tempX = thisBear.getX();
                tempY = thisBear.getY();
                RIVER[x][y] = null;
                RIVER[x][y] = thisBear;
                RIVER[tempX][tempY] = null;
                fishCount--;
                fishKilled++;
                empty++;
                success = true;
                break;
            // If the space is blank the bear will move here.
            case "Nothing":
                tempX = thisBear.getX();
                tempY = thisBear.getY();
                thisBear.setX(x);
                thisBear.setY(y);
                RIVER[x][y] = thisBear;
                RIVER[tempX][tempY] = null;
                success = true;
                break;
        }
        return success;
    }

    /**
     * This method is used to handle the bear move process. Each condition
     * handled is given a success state that is then returned to the moveAll()
     * method. It will return a boolean to determine if the move was a success.
     *
     * @param state what is at the new location the object is moving too.
     * @param x The row that is being examined.
     * @param y The column that is being examined.
     * @param thisFish The bear object that is being handled.
     * @return boolean the state of the moves success.
     */
    public Boolean fishHandleMove(String state, int x, int y, Fish thisFish) {
        boolean success = false;
        int xpos;
        int ypos;

        if (thisFish.swimFish()) {
            switch (state) {
                case "Fish":
                    int limit = 0;
                    // A higher limit with a small chance of duplicate spawn give.
                    // A small chance of duplicate spawn gives fish an advantage.
                    while (limit < 10) {
                        limit++;
                        xpos = RAND.nextInt(SIZE);
                        ypos = RAND.nextInt(SIZE);
                        if ("Nothing".equals(checkMove(xpos, ypos))) {
                            RIVER[xpos][ypos] = thisFish.makeFish(xpos, ypos);
                            fishCount++;
                            empty--;
                            success = true;
                        }
                    }
                    break;
                case "Bear":
                    //If it runs into a bear then it deserves a slap.
                    success = false;
                    break;
                default:
                    //If it finds an empty section to swim too.
                    int tempX = thisFish.getX();
                    int tempY = thisFish.getY();
                    thisFish.setX(x);
                    thisFish.setY(y);
                    RIVER[x][y] = thisFish;
                    RIVER[tempX][tempY] = null;
                    success = true;
                    break;
            }
        }
        //Checks the state to see how the move should be handled.
        return success;
    }

    /**
     * This method goes to every element in the array and then prints out the
     * state in the element. Print it out as a table with either null, Fish, or
     * Bear at the element.
     */
    public void print() {
        // Stays within range of array by looking at size.
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                if (RIVER[x][y] == null) {
                    System.out.print("Null ");
                } else {
                    switch (RIVER[x][y].getAnimal()) {
                        case "Bear":
                            System.out.print("Bear ");
                            break;
                        case "Fish":
                            System.out.print("Fish ");
                            break;
                    }
                }
            }
            System.out.println();
        }
    }
}
