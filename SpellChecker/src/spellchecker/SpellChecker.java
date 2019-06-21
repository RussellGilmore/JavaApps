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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class interacts with the hash set and attempts to correct a word if it
 * is incorrectly spelled. All potential corrections are stored then passed to
 * an interface for the user to read.
 *
 * @author Russell Gilmore
 */
public class SpellChecker {

    // This contains all the words taken from the dictionary file.
    private final List<String> lines;
    // The hash set object.
    private MyHashSet<String> lex;
    // Possible corrected versions of the word.
    private final List<String> corrections;
    private final char[] alphabet;

    /**
     * Instantiates the the lines containing the dictionary words. Creates the
     * lexicon, and creates an alphabet array that is used in some of word
     * manipulation methods.
     */
    public SpellChecker() {

        lines = new ArrayList<>();
        corrections = new ArrayList<>();
        alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        readDictionary();
        makeLexicon();

    }

    /**
     * Checks to see if the given word is spelled correctly or not.
     *
     * @param word Pass a string that will be checked against the lexicon.
     * @return Returns true if the word is spelled correctly.
     */
    public boolean wordValid(String word) {
        String wordExist = lex.get(word);
        return wordExist != null;
    }

    /**
     * Takes an incorrectly spelled word and runs it against multiply simple
     * solutions in attempt to correct the word.
     *
     * @param word The incorrectly spelled word.
     * @return Returns an array of all the possible corrections.
     */
    public String[] wordFix(String word) {
        // Runs the word through 4 methods in attempt to fix the word.
        if (word.length() >= 1) {
            adjacentSwap(word);
            deleteChar(word);
            insertChar(word);
            replaceChar(word);
        }
        if (corrections.isEmpty()) {
            return null;
        }
        String[] fixes = new String[corrections.size()];
        for (int i = 0; i < fixes.length; i++) {
            fixes[i] = corrections.get(i);
        }
        // Clears for reuse.
        corrections.clear();

        return fixes;

    }

    /**
     * Swaps the characters of the word with the characters beside it.
     *
     * @param word Take in the misspelled word.
     */
    private void adjacentSwap(String word) {
        char[] charWord = word.toCharArray();
        char temp;
        for (int i = 0; i < word.length() - 1; i++) {
            temp = charWord[i];
            charWord[i] = charWord[i + 1];
            charWord[i + 1] = temp;
            String tempWord = new String(charWord);
            // If word is good and is not already present in the corrections then it will be added to the corrections arraylist.
            if (lex.get(tempWord) != null) {
                if (corrections.contains(tempWord) != true) {
                    corrections.add(tempWord);
                }

            }

            temp = charWord[i];
            charWord[i] = charWord[i + 1];
            charWord[i + 1] = temp;
        }

    }

    /**
     * Removes a single character for the word and then checks the words
     * validity. Will then revert the word back to the previous state and then
     * try the next character.
     *
     * @param word
     */
    private void deleteChar(String word) {
        char[] wordArray = word.toCharArray();
        List<Character> wordList = new ArrayList<>();
        for (int i = 0; i < wordArray.length; i++) {
            wordList.add(wordArray[i]);
        }
        int i = 0;
        while (i < wordList.size()) {
            char temp = wordList.get(i);
            wordList.remove(i);

            char[] returnCharWord = new char[word.length() - 1];
            for (int j = 0; j < wordList.size(); j++) {
                returnCharWord[j] = wordList.get(j);
            }
            String returnWord = new String(returnCharWord);
            // If word is good and is not already present in the corrections then it will be added to the corrections arraylist.
            if (lex.get(returnWord) != null) {
                if (corrections.contains(returnWord) != true) {
                    corrections.add(returnWord);
                }

            }
            wordList.add(i, temp);
            i++;
        }
    }

    /**
     * Iterates through the alphabet inserting every letter at every position in
     * the array.
     *
     * @param word The misspelled word.
     */
    private void insertChar(String word) {
        char[] wordArray = word.toCharArray();
        List<Character> wordList = new ArrayList<>();
        for (int i = 0; i < wordArray.length; i++) {
            wordList.add(wordArray[i]);
        }
        int i = 1;
        //Starts at the first valid insertion point.
        while (i < wordArray.length) {
            // A the current insertion point it interates though the entire alphabet.
            for (int j = 0; j < alphabet.length; j++) {
                wordList.add(i, alphabet[j]);

                char[] returnCharWord = new char[wordList.size()];
                for (int z = 0; z < wordList.size(); z++) {
                    returnCharWord[z] = wordList.get(z);
                }
                String returnWord = new String(returnCharWord);
                // If word is good and is not already present in the corrections then it will be added to the corrections arraylist.
                if (lex.get(returnWord) != null) {
                    if (corrections.contains(returnWord) != true) {
                        corrections.add(returnWord);
                    }

                }
                wordList.remove(i);
            }
            // Increment to new insertion point.
            i++;

        }
    }

    /**
     * Replaces each individual character in the array with all the letters one
     * by one in the alphabet. Then shifts to the next insertion point after
     * reseting the word.
     *
     * @param word The misspelled word.
     */
    private void replaceChar(String word) {
        char[] wordArray = word.toCharArray();
        int i = 0;
        // Insertion point
        while (i < wordArray.length) {
            char temp = wordArray[i];
            // Interates though every letter in the alphabet.
            for (int j = 0; j < alphabet.length; j++) {
                wordArray[i] = alphabet[j];
                String returnWord = new String(wordArray);
                if (lex.get(returnWord) != null) {
                    if (corrections.contains(returnWord) != true) {
                        corrections.add(returnWord);
                    }
                }
            }
            wordArray[i] = temp;
            i++;
        }
    }

    /**
     * Takes in a file and reads in all the the lines on it. Gets the words that
     * will be used in the lexicon.
     */
    private void readDictionary() {
        BufferedReader br = null;
        File file = new File("dictionary.txt");
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SpellChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        String line;
        try {
            int i = 0;
            while ((line = br.readLine()) != null) {

                lines.add(i, line);
                i++;
            }

        } catch (IOException ex) {
            Logger.getLogger(SpellChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(SpellChecker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Adds all lines to the lexicon.
     */
    private void makeLexicon() {
        lex = new MyHashSet<>(lines.size());
        for (String line : lines) {
            lex.add(line);
        }
    }

}
