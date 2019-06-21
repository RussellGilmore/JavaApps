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
 * This is an implementation of the nodes that will be used in my link list
 * implementation.
 *
 * @author Russell Gilmore
 * @param <E>
 */
public class MyNode<E> {

    MyNode next;
    E data;

    /**
     * Passes a value that will be stored in the node.
     *
     * @param data The type is specified on how you instantiate the link list.
     */
    public MyNode(E data) {
        next = null;
        this.data = data;
    }

    /**
     * Server the node by connecting it to the next value.
     *
     * @param data Value of the node.
     * @param nextValue Sets to the node next to it.
     */
    public MyNode(E data, MyNode nextValue) {
        this.data = data;
        this.next = nextValue;

    }

    /**
     * Get the data for the node and return it.
     *
     * @return Value of the node.
     */
    public E getData() {
        return this.data;
    }

    /**
     * Set the data to the node.
     *
     * @param dataValue Value passed to be set to the node.
     */
    public void setData(E dataValue) {
        this.data = dataValue;
    }

    /**
     * Get the node connected to this node and return it.
     *
     * @return The node the is connected.
     */
    public MyNode getNext() {
        return this.next;
    }

    /**
     * Set the value of the next node.
     *
     * @param nextValue The value used for that node.
     */
    public void setNext(MyNode nextValue) {
        next = nextValue;
    }

}
