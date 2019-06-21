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
package spellchecker;

/**
 * This is an implementation of a HashSet. It mods the hashcode of entries and
 * then finds the table position and adds it to a link list. Uses Open
 * collision. The size is generated based on the the number the entries and is
 * not dynamic past that point.
 *
 * @author Russell Gilmore
 * @param <E>
 */
public class MyHashSet<E> {

    // Number of entries in hash set.
    private int size;
    private final MyLinkList<E>[] set;

    /**
     * Create the table size based on a calculation run against the size given
     * of entries that would be added to the hash set. When instantiated a type
     * must be given.
     *
     * @param size
     */
    public MyHashSet(int size) {
        int bounds = ((int) Math.round(size / .75));
        set = new MyLinkList[bounds];
        for (int i = 0; i < bounds; i++) {
            set[i] = new MyLinkList<>();
        }

    }

    /**
     * Adds a entry to the hash set by modding the length with the hashcode of
     * the entry.
     *
     * @param e
     */
    public void add(E e) {
        int hash = mod(e.hashCode(), set.length);
        set[hash].add(e);
    }

    /**
     * Mathematical mod for to integers.
     *
     * @param x
     * @param y
     * @return
     */
    private int mod(int x, int y) {
        int result = x % y;
        if (result < 0) {
            result += y;
        }
        return result;
    }

    /**
     * Used to get an item from the hash set.
     *
     * @param e
     * @return returns the data if present or null is not in the table.
     */
    public E get(E e) {
        if (e == null) {
            return null;
        }

        int hash = mod(e.hashCode(), set.length);
        int pos = 0;
        // Iterates through the hash set until the object is found.
        MyLinkList<E> ll = set[hash];
        while (ll.get(pos) != null) {

            if (e.equals(ll.get(pos))) {
                return ll.get(pos);
            }
            pos++;
        }

        return null;
    }

    /**
     *
     * @return The number of objects in the hashset.
     */
    public int size() {
        return this.size;

    }

}
