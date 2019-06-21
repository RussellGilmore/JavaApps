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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Russell Gilmore
 * 
 * A messy class that is used to handle the file being used and
 * the generation of the huffman tree. It also handles the creation of a HashMap
 * that is used to keep track of all character and the frequency associated with
 * them.
 */
public class Handle {

    // hm is where the frequency is being kept.
    private final Map<Character, Integer> hm;
    private final String input;
    private final Encoder encoder;
    private int test = 0;

    /**
     *
     * @param filename Take filename of plaintext.
     * @throws IOException
     */
    public Handle(String filename) throws IOException {
        this(Paths.get(filename));
    }

    public Map getFrequencyTable() {
        return this.hm;
    }

    /**
     *
     * @param path Creates the path from the filename plaintext.
     * @throws IOException
     */
    public Handle(Path path) throws IOException {
        hm = new HashMap<>();
        this.input = new String(Files.readAllBytes(path));
        this.encoder = buildHuffmanTree();
    }

    /**
     * Takes each character from the textfile and adds it to a HashMap.
     * Increments the value by one each time that character is found.
     */
    private void findFrequency() {
        for (char c : this.input.toCharArray()) {
            //test = s.length();
            Integer i = hm.get(c);
            if (i == null) {
                i = 0;
            }
            hm.put(c, ++i);
        }
    }

    /**
     * Iterates through all keys in the frequency hashmap and creates
     * HuffmanLeafNodes. Runs all nodes through the minheap priority queque.
     * Takes the the first two nodes in the minheap and compares them. After the
     * comparison the nodes weight are combined to create an internal node. This
     * allows for all nodes of equal weight to be be balanced.
     *
     * @return
     */
    private Encoder buildHuffmanTree() {
        //Builds the frequency hashmap.
        findFrequency();
        HuffmanNode[] unsortedLeafs = new HuffmanNode[hm.size()];
        int i = 0;
        //Iterates though all items in the hashmap and creates a huffman node.
        //Adds all nodes into a node array.
        for (Map.Entry<Character, Integer> entry : hm.entrySet()) {
            //System.out.println(entry.getKey() + " : " + entry.getValue());
            unsortedLeafs[i] = new HuffmanLeafNode(entry.getKey(), entry.getValue());
            i++;
        }

        // Pases the Node Array into the minheap to be sorted.
        MinHeap<HuffmanNode<Character>> minHeap = new MinHeap<>(unsortedLeafs, unsortedLeafs.length);
        // Pulls out the first two nodes and combines them to create an internal node.
        while (minHeap.heapsize() > 2) {
            final HuffmanNode node1 = minHeap.removeMin();
            final HuffmanNode node2 = minHeap.removeMin();

            minHeap.insert(new InternalNode<>(node1, node2, node1.weight() + node2.weight()));
        }

        final HuffmanNode node1 = minHeap.removeMin();
        final HuffmanNode node2 = minHeap.removeMin();

        HuffmanTree huffTree = new HuffmanTree<>(node1, node2, node1.weight() + node2.weight());

        // Passes HuffTree to the encoder.
        return new Encoder(huffTree);
    }

    /**
     * A test method that is used to test a hashmap.
     */
    public void testHashMap() {
        //int charTest = 0;
        for (HashMap.Entry<Character, Integer> entry : hm.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
            //charTest = charTest + entry.getValue();
        }

    }

    /**
     *
     * @return Returns the ciphertext.
     */
    public String encode() {
        return this.encoder.encode(this.input);
    }

    /**
     *
     * @param encoded
     * @return
     */
    public String decode(String encoded) {
        return this.encoder.decode(encoded);
    }

}
