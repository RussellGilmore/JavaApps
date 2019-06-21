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
 * This class is used to generate a huffman tree.
 *
 * @author Russell Gilmore
 * @param <E>
 */
public class HuffmanTree<E> implements Comparable<HuffmanTree<E>> {

    private HuffmanNode<E> root;

    /**
     * Takes the initial root.
     *
     * @param element The node type or character for this case.
     * @param weight The frequency of the character converted to a weight value
     * for placing in the tree.
     */
    public HuffmanTree(E element, int weight) {
        root = new HuffmanLeafNode<>(element, weight);
    }

    /**
     * Roots becomes the top internal nodes. Is given a node for the left and
     * the right and a weight.
     *
     * @param left Node attached to left.
     * @param right Node attached to right.
     * @param weight Weight of the node that is used in comparisons.
     */
    public HuffmanTree(HuffmanNode<E> left, HuffmanNode<E> right, int weight) {
        root = new InternalNode<>(left, right, weight);
    }

    /**
     * Returns the top node.
     *
     * @return Returns the root.
     */
    public HuffmanNode<E> root() {
        return root;
    }

    /**
     * Returns the weight of the node.
     *
     * @return Return the weight.
     */
    public int weight() {
        return root.weight();
    }

    /**
     * If the nodes weight is less than it will be placed to the left. If the
     * node weight is more than it will be placed to the right.
     *
     * @param leafnode Node that is being used in comparison.
     * @return
     */
    @Override
    public int compareTo(HuffmanTree<E> leafnode) {
        return root.weight() - leafnode.weight();
    }

    /**
     * A test method used to access print the tree.
     *
     * @return String representation of the node.
     */
    @Override
    public String toString() {
        return "HuffTree{" + "root=" + root + '}';
    }

}
