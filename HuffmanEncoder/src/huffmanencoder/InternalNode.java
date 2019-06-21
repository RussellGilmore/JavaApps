/*
 * The MIT License
 *
 * Copyright 2016 Russell.
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
 * This class is used to create the internal node objects that are used in the
 * HuffmanTree.
 *
 * @author Russell
 * @param <E>
 */
public class InternalNode<E> implements HuffmanNode<E> {

    private final int weight;
    private final HuffmanNode<E> left;
    private final HuffmanNode<E> right;

    /**
     * Each internal node must be initialized with a weight, and a left and
     * right node.
     *
     * @param left The left node
     * @param right The right node
     * @param weight The weight value of the internal node.
     */
    public InternalNode(HuffmanNode<E> left, HuffmanNode<E> right, int weight) {
        this.left = left;
        this.right = right;
        this.weight = weight;
    }

    /**
     * This is not a leaf so it will always return false.
     *
     * @return
     */
    @Override
    public boolean isLeaf() {
        return false;
    }

    /**
     * Returns the weight of the internal node.
     *
     * @return The weight of the internal node.
     */
    @Override
    public int weight() {
        return weight;
    }

    /**
     * Returns the node to the left of the internal node.
     *
     * @return The node to the left.
     */
    public HuffmanNode<E> left() {
        return left;
    }

    /**
     * Returns the node to the right of the internal node.
     *
     * @return The node to the right.
     */
    public HuffmanNode<E> right() {
        return right;
    }

    /**
     * A method used print out the internal nodes.
     *
     * @return A string representing the internal node.
     */
    @Override
    public String toString() {
        return "InternalNode{" + "weight=" + weight + ", left=" + left + ", right=" + right + '}';
    }

}
