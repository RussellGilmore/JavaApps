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

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Russell Gilmore
 * 
 * This class is used to handle the encoding and decoding of the
 * plaintext that has already been accessed by the Handle. Takes in a
 * HuffmanTree and creates a Map of the proper paths to take to find the
 * characters.
 *
 */
public class Encoder {

    private final HuffmanTree huffTree;
    private final Map<Character, String> routes;

    /**
     *
     * @param huffTree A pre-generated Huffman tree that has been set up for all
     * possible characters in the plaintext.
     */
    public Encoder(HuffmanTree huffTree) {
        this.huffTree = huffTree;
        routes = new HashMap<>();
        InternalNode internal = (InternalNode) huffTree.root();
        // Starts at the root and begins path as a blank string.
        createMap(internal, "");
    }

    /**
     * Takes an internal node and a path to recursively map all paths to each
     * element in the Huffman Tree.
     *
     * @param n The internal node.
     * @param path The path where each element is located.
     */
    public void createMap(InternalNode n, String path) {
        // 0 is for a left traversal
        if (n.left().isLeaf()) {
            HuffmanLeafNode node = (HuffmanLeafNode) n.left();
            routes.put((Character) node.element(), path + "0");
        } else {
            InternalNode internal = (InternalNode) n.left();
            createMap(internal, path + "0");
        }
        // 1 is for a right traversal.
        if (n.right().isLeaf()) {
            HuffmanLeafNode node = (HuffmanLeafNode) n.right();
            routes.put((Character) node.element(), path + "1");
        } else {
            InternalNode internal = (InternalNode) n.right();
            createMap(internal, path + "1");
        }

    }

    private void printMap(Map<Character, String> m) {
        for (Map.Entry<Character, String> entry : m.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
            //charTest = charTest + entry.getValue();
        }
    }

    /**
     * Once the paths have been found this method will take in the plaintext and
     * replace each character (Key) with each route (Value).
     *
     * @param text The text to being encoded.
     * @return Returns an encoded string of 1's and 0's
     */
    public String encode(String text) {
        // Used to append the strings.
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            sb.append(this.routes.get(text.charAt(i)));
        }
        return sb.toString();
    }

    /**
     * Takes the ciphertext and takes each character (1 or 0) as a direction to
     * go the tree and then finds the original element.
     *
     * @param encoded Takes the Huffman generated ciphertext.
     * @return The original plaintext.
     */
    public String decode(String encoded) {
        StringBuilder sb = new StringBuilder();

        HuffmanNode n = this.huffTree.root();
        for (int i = 0; i < encoded.length(); i++) {
            if (n.isLeaf()) {
                HuffmanLeafNode leaf = (HuffmanLeafNode) n;
                sb.append(leaf.element());
                n = this.huffTree.root();
            }
            // Check both cases to determine with route should be taken.
            final InternalNode in = (InternalNode) n;
            switch (encoded.charAt(i)) {
                case '0':
                    n = in.left();
                    break;
                case '1':
                    n = in.right();
                    break;
            }
        }
        return sb.toString();
    }

}
