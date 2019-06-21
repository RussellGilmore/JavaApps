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

import java.util.Scanner;

/**
 * This class handles the user interaction by asking the user to supply words
 * that need spell checking. Once the user supplies a word the word is checked
 * and the user is given a status on if the word is spelled correctly. If the
 * word is spelled incorrectly the user is given a few suggestions on how the
 * word is spelled incorrectly.
 * @author Russell Gilmore
 *
 */
public class WordHandle {

    private final SpellChecker spell;
    private final Scanner scan;

    /**
     * Constructor creates the spell checker object and scanner object.
     */
    public WordHandle() {
        spell = new SpellChecker();
        scan = new Scanner(System.in);
    }

    /**
     * Start application.
     *
     * @param args
     */
    public static void main(String[] args) {
        WordHandle handle = new WordHandle();
        handle.wordLoop();
    }

    /**
     * Enables an environment that loops asking the user for words that need
     * spell checked.
     */
    public void wordLoop() {
        System.out.println("Spell Checker");
        boolean cont = true;
        String word;
        System.out.println("Please supply a word to check if the word is spelled correctly.");
        do {
            // Gets a word and then checks to see if the word is formated properly.
            System.out.println("Please type a word and press enter.");
            word = scan.nextLine();
            word = word.toLowerCase();
            word = word.trim();
            // If the word is spelled correctly then the status is displayed.
            boolean isValid = spell.wordValid(word);
            if (isValid) {
                System.out.println("The word " + word + " is spelled correctly.");
            } else {

                String[] fixes = spell.wordFix(word);
                spell.wordFix(word);

                if (fixes.length >= 1) {
                    System.out.println("The word is misspelled. Below is possible correctly spelled words.");
                    for (String line : fixes) {
                        System.out.print(line + " ");
                    }
                    System.out.println();

                } else {
                    // This happens when there is no spelling suggestions.
                    System.out.println("The word is wrong. Give up. No Hope.");
                }
            }
            // Prompt user if they want to try a new word or exit the applications.
            System.out.println("Would you like to try another word?");
            System.out.println("Type 'no' to end the program, or 'yes' to try another word.");
            word = scan.nextLine();
            word = word.toLowerCase();
            word = word.trim();
            if ("no".equals(word)) {
                cont = false;
            }
        } while (cont);
    }

}
