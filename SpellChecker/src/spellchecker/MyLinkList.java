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

import java.util.Iterator;

/**
 * This is my link list implementation that tries to follow most of what the
 * Oracle ADT has for their implementation of a link list.
 *
 * @author Russell Gilmore
 * @param <E>
 */
public class MyLinkList<E> implements Iterable<E> {

    // Number of nodes in the link list.
    private int counter;
    private MyNode head;

    /**
     * Adds a single node to the head of the link list.
     *
     * @param data The value that is going to be stored in the node.
     */
    public void add(E data) {

        if (head == null) {
            head = new MyNode(data);
        }
        //Creates the node and passes the data along.
        MyNode temp = new MyNode(data);
        MyNode nodeCurrent = head;

        if (nodeCurrent != null) {
            while (nodeCurrent.getNext() != null) {
                nodeCurrent = nodeCurrent.getNext();
            }
            nodeCurrent.setNext(temp);
        }
        // Increments the counter.
        this.counter++;
    }

    /**
     * Inserts a node at a position given by an index.
     *
     * @param data Value given to the node.
     * @param index Position that the node is going to be inserted at.
     */
    public void add(E data, int index) {
        // Creates the new node and passes the data value to it.
        MyNode temp = new MyNode(data);
        MyNode currentNode = head;

        if (currentNode != null) {
            for (int i = 0; i < index && currentNode.getNext() != null; i++) {
                currentNode = currentNode.getNext();
            }
        }
        temp.setNext(currentNode.getNext());

        currentNode.setNext(temp);
        this.counter++;
    }

    /**
     * Checks the passed index for the position of the link list and then
     * returns the value at the position.
     *
     * @param index Position of the link list that is going to be accessed.
     * @return Returns the value of the index.
     */
    public E get(int index) {
        // Valid Check
        if (index < 0) {
            return null;
        }
        MyNode nodeCurrent = null;
        if (head != null) {
            nodeCurrent = head.getNext();
            for (int i = 0; i < index; i++) {
                if (nodeCurrent.getNext() == null) {
                    return null;
                }

                nodeCurrent = nodeCurrent.getNext();
            }
            return (E) nodeCurrent.getData();
        }
        return null;
    }

    /**
     * Removes a node in the link list at the given position.
     *
     * @param index Position of the node being removed.
     * @return Returns true if the node has been removed.
     */
    public boolean remove(int index) {
        if (index < 1 || index > size()) {
            return false;
        }
        MyNode currentNode = head;
        if (head != null) {
            for (int i = 0; i < index; i++) {
                if (currentNode.getNext() == null) {
                    return false;
                }
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(currentNode.getNext().getNext());

            this.counter--;
            return true;

        }
        return false;
    }

    /**
     * Returns the size of the link list.
     *
     * @return Returns an integer for the number of nodes.
     */
    public int size() {
        return this.counter;
    }

    /**
     * Used primarily for testings and printing out all elements in the link
     * list.
     *
     * @return A string showing all node values.
     */
    @Override
    public String toString() {
        String output = "";
        if (head != null) {
            MyNode currentNode = head.getNext();
            while (currentNode != null) {
                output += "[" + currentNode.getData().toString() + "]";
                currentNode = currentNode.getNext();
            }

        }
        return output;
    }
    // Below this point is code that is currently not being used. It was my attempt to make an interator, but I ran out of time..

    @Override
    public Iterator<E> iterator() {

        return new IteratorImpl();

    }

    private class IteratorImpl implements Iterator<E> {

        private MyNode<E> currentNode = head;

        @Override
        public boolean hasNext() {
            return currentNode.getNext() != null;
        }

        @Override
        public E next() {
            if (hasNext() == true) {
                currentNode = currentNode.getNext();
                return currentNode.getData();
            }
            return null;

        }
    }
}
