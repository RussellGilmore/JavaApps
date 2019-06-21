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
 * This class implements a minheap priority queque. The class takes in an array
 * of huffman nodes and sorts them by minimum weight.
 *
 * @author Russell Gilmore
 */
public class MinHeap<E> {

    // The array of heap nodes.
    private final HuffmanNode[] heap;

    private int leafNum;

    /**
     * Passes in the heap array and number of nodes.
     *
     * @param heap Heap array of nodes.
     * @param leafNum Numbers of nodes in the heap. Array size.
     */
    public MinHeap(HuffmanNode[] heap, int leafNum) {
        this.heap = heap;
        this.leafNum = leafNum;
        //Build the heap
        makeHeap();
    }

    /**
     * Inserts a node into the heap and rebuilds. In my implementation the
     * rebuild is called after all nodes have been inserted. This method will
     * not work correctly if the rebuild is not called.
     *
     * @param val The node.
     */
    public void insert(HuffmanNode<E> val) {
        int current = leafNum++;
        heap[current] = val;
        while ((current != 0) && (heap[current].weight() < heap[parent(current)].weight())) {
            HuffmanNode temp = heap[current];
            heap[current] = heap[parent(current)];
            heap[parent(current)] = temp;
            current = parent(current);

        }
        makeHeap();

    }

    /**
     * Rebuilds the heap from a position.
     *
     * @param pos Where the heap needs rebuilt at from a position in the array.
     * Best to always rebuild at 0;
     */
    private void rebuild(int pos) {
        while (!isLeaf(pos)) {
            int j = leftChildNode(pos);
            if ((j < (leafNum - 1)) && (heap[j].weight() > heap[j + 1].weight())) {
                j++;
            }
            if (heap[pos].weight() <= heap[j].weight()) {
                return;
            }
            HuffmanNode temp = heap[pos];
            heap[pos] = heap[j];
            heap[j] = temp;
            pos = j;
        }
    }

    /**
     * Remove the top node from the heap. If the heap has been built it should
     * be the minimum.
     *
     * @return
     */
    public HuffmanNode<E> removeMin() {
        int leafPos = --leafNum;
        HuffmanNode temp = heap[0];
        heap[0] = heap[leafPos];
        heap[leafPos] = temp;

        if (leafNum != 0) {
            rebuild(0);
        }
        return heap[leafNum];
    }

    /**
     * Removes a node at a certain position. Then rebuilds the tree from that
     * position.
     *
     * @param pos Position in the array where the node was removed.
     * @return The node.
     */
    public HuffmanNode<E> remove(int pos) {
        if (pos == (leafNum - 1)) {
            leafNum--;
        } else {
            int leafPos = --leafNum;
            HuffmanNode temp = heap[pos];
            heap[pos] = heap[leafPos];
            heap[leafPos] = temp;
            while ((pos > 0) && heap[pos].weight() < heap[parent(pos)].weight()) {
                temp = heap[pos];
                heap[pos] = heap[parent(pos)];
                heap[parent(pos)] = temp;
                pos = parent(pos);
            }
            if (leafNum != 0) {
                rebuild(pos);
            }
        }
        return heap[leafNum];
    }

    /**
     * Returns the number is nodes in the heap.
     *
     * @return The number of nodes in the heap.
     */
    public int heapsize() {
        return leafNum;
    }

    /**
     * Checks if there is a leaf from a position in the heap.
     *
     * @param position Checks the location in the heap and finds if it has
     * nodes.
     * @return True if there is no other connected nodes. False if it has
     * children.
     */
    public boolean isLeaf(int position) {
        if (position >= leafNum / 2 && position < leafNum) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Builds the heap.
     */
    public void makeHeap() {
        for (int position = (leafNum / 2) - 1; position >= 0; position--) {
            rebuild(position);
        }
    }

    /**
     * Returns the location in heap of left child.
     *
     * @param postion
     * @return the position
     */
    public int leftChildNode(int postion) {
        return (2 * postion) + 1;
    }

    /**
     * Returns the location in heap of right child.
     *
     * @param postion
     * @return the position
     */
    public int rightChildNode(int position) {
        return (2 * position) + 2;
    }

    /**
     * Takes the position and find the node above it.
     *
     * @param position Node that is to be selected to find parent of.
     * @return Location of parent.
     */
    public int parent(int position) {
        return (position - 1) / 2;
    }

}
