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
package huffmanencoder;

/**
 * This class contains what makes of a HuffmanLeafNode.
 *
 * @author Russell
 */
public class HuffmanLeafNode<E> implements HuffmanNode {

    // Value and Weight
    private E element;
    private int weight;

    /**
     * All nodes must be instantiated with a element type and value and a
     * weight.
     *
     * @param character
     * @param number
     */
    public HuffmanLeafNode(E character, int number) {
        this.element = character;
        this.weight = number;
    }

    /**
     * Returns the data type.
     *
     * @return The data type.
     */
    public E element() {
        return element;
    }

    /**
     * The weight of the node.
     *
     * @return The weight.
     */
    public int weight() {
        return weight;
    }

    /**
     * Nodes in the huffman tree will always be a leaf.
     *
     * @return True since it will always be a leaf.
     */
    public boolean isLeaf() {
        return true;
    }

    /**
     * Used to test and to print leafs.
     *
     * @return
     */
    @Override
    public String toString() {
        return "Leaf{" + "element=" + element + ", number=" + weight + '}';
    }

}
