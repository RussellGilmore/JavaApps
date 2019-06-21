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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;

/**
 * The class is used to instantiate all objects needed for the Huffman tree to
 * work. Takes in an arg that is used to specify the file path on the plaintext.
 *
 * @author Russell Gilmore
 */
public class Driver {

    public static void main(String[] args) throws IOException {
        String filename = args[0];
        Handle encoder = new Handle(filename);
        String encoded = encoder.encode();
        String decoded = encoder.decode(encoded);
        Map<Character, Integer> hm = encoder.getFrequencyTable();
        // I have been doing this wrong for years.. I might still be, but this is sooooooo much more elegant than what I was doing back in the day.. err.. semester.
        PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
        System.setOut(out);
        System.out.println("Frequency Table Begin");
        for (Map.Entry<Character, Integer> entry : hm.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println("Frequency Table End");
        System.out.println("-------------------------------------------------");
        System.out.println("-------------------------------------------------");
        System.out.println("Encoded Text Begin");
        System.out.println(encoded);
        System.out.println("End Encoded Text");
        System.out.println("-------------------------------------------------");
        System.out.println("-------------------------------------------------");
        System.out.println("Decoded Text Begin");
        System.out.println(decoded);
        System.out.println("End Decoded Text");
        System.out.println("-------------------------------------------------");

        //PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
    }
}
