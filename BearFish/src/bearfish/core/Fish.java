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
 * This class handles the behavior of the fish object. This class contains all
 * the unique methods associated with a fish object. This object keeps track of
 * the number of fish made and how many times the fish has moved.
 *
 * @author Russell Gilmore
 */
public class Fish extends Animal {

    private int timesMoved = 0;
    private final int FISH_MAKE_LIMIT = 10;
    private final int FISH_MOVE_LIMIT = 10;
    private int goodTimes = 0;

    /**
     * Give the animal object a starting location and its type.
     *
     * @param animal
     * @param x
     * @param y
     */
    public Fish(String animal, int x, int y) {
        super(animal, x, y);

    }

    /**
     * Spawns a fish object at the giving it a starting position. It checks to
     * see if the max number spawns has happened.
     *
     * @param x X cord
     * @param y y cord
     * @return The new fish object.
     */
    public Fish makeFish(int x, int y) {
        // Checks if limit is reached for creating.
        if (goodTimes != FISH_MAKE_LIMIT) {
            goodTimes++;
            return new Fish("Fish", x, y);
        } else {
            return null;
        }

    }

    /**
     * Checks if the fish can breed.
     *
     * @return
     */
    public boolean canBreed() {
        return goodTimes != FISH_MAKE_LIMIT;
    }

    /**
     * Returns the objects type.
     *
     * @return String type.
     */
    public String type() {
        return "Fish";
    }

    /**
     * Checks if the fish can move.
     *
     * @return if the fish can move.
     */
    public boolean swimFish() {
        if (timesMoved < FISH_MOVE_LIMIT) {
            timesMoved++;
            return true;
        } else {
            return false;
        }

    }

}
