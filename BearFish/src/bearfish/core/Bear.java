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

/**
 * This class handles the behavior of the bear object. This class contains all
 * the unique methods associated with a bear object. This object keeps track of
 * the number of bear made and how many times the bear has moved.
 *
 * @author Russell Gilmore
 */
public class Bear extends Animal {

    private int timesMoved;
    private int fishAte;
    private final int BEAR_MAKE_LIMIT = 3;
    private final int BEAR_MOVE_LIMIT = 5;
    private int goodTimes = 0;

    /**
     * Give the animal object a starting location and its type.
     *
     * @param animal
     * @param x
     * @param y
     */
    public Bear(String animal, int x, int y) {

        super(animal, x, y);
    }

    /**
     * Spawns a bear object at the giving it a starting position. It checks to
     * see if the max number spawns has happened.
     *
     * @param x X cord
     * @param y y cord
     * @return The new bear object.
     */
    public Bear makeBear(int x, int y) {
        if (goodTimes != BEAR_MAKE_LIMIT) {
            goodTimes++;
            return new Bear("Bear", x, y);
        } else {
            return null;
        }

    }

    /**
     * Checks to see if the bear has reached is limit.
     *
     * @return
     */
    public boolean canBreed() {
        return goodTimes != BEAR_MAKE_LIMIT;
    }

    /**
     * Increments how many fish at this bear object has eaten.
     */
    public void eatFish() {
        fishAte++;
    }

    /**
     * Returns the object type.
     *
     * @return
     */
    public String type() {
        return "Bear";
    }

    /**
     * Checks to see if the bear reached its move limit.
     *
     * @return
     */
    public boolean bearRun() {
        if (timesMoved < BEAR_MOVE_LIMIT) {
            timesMoved++;
            return true;
        } else {
            return false;
        }

    }

}
