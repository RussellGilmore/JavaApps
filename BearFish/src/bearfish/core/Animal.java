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
 * This class contains all of the common methods between the bear and fish
 * objects.
 *
 * @author Russell Gilmore
 */
public abstract class Animal {

    private int x;
    private int y;
    private String animal;

    /**
     *
     * @param animal
     * @param x
     * @param y
     */
    public Animal(String animal, int x, int y) {
        //handles what kind of animal will be generated.

        this.x = x;
        this.y = y;
        this.animal = animal;

    }

    /**
     * Returns the animal object x position.
     *
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the animal object y position.
     *
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the objects x position.
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the objects y position.
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * The the objects type.
     *
     * @param animal
     */
    public void setAnimal(String animal) {
        this.animal = animal;
    }

    /**
     * Get the animal type.
     *
     * @return
     */
    public String getAnimal() {
        return animal;
    }

}
