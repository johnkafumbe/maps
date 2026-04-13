package edu.ttap.intmap;

import java.io.File;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;


/**
 * IntegerMaps give three apporoaches to counting characters in txt files
 * 
 * Books tested for Part 1:
 * 
 * 1. Pride and Prejudice by Jane Austen
 * link: https://www.gutenberg.org/cache/epub/1342/pg1342.txt
 * It is consistent with the known frequency of letters because E appears most frequently.
 * 
 * 2. Moby Dick by Herman Melville
 * link: https://www.gutenberg.org/ebooks/2701 
 * Similary Moby Dick is also consistent with the frequency of letters becuase E appears most frequently.
 * 
 */

public class IntegerMaps {

/**
 * Reads the text file at path and prints the frequency of each of
 * the 26 English letters to standard output, one letter per line.
 * 
 * @param path the path to the text file
 */

    public static void reportCounts(String path) {
        int[] counts = new int[26];

        try {
            Scanner scanner = new Scanner(new File(path));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                line = line.toLowerCase();

                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);

                    if (c >= 'a' && c <= 'z') {
                        counts[c - 'a']++;
                    }
                }
            }

            scanner.close();
        } catch (Exception e) {
            System.err.println("Error reading file");
            System.exit(1);
        }

        for (int i = 0; i < counts.length; i++) {
            char letter = (char) ('a' + i);
            System.out.println(letter + ": " + counts[i] + "\n");
        }
    }

/**
 * Counts the number of unique characters in the text file at path
 * 
 * @param path the path to the text file
 * @return the number of unique chracters foung in the text file.
 */


    public static int countChars(String path) {
        TreeSet<Character> uniqueCharacters = new TreeSet<>();

        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                for (int i = 0; i < line.length(); i++) {
                    uniqueCharacters.add(line.charAt(i));
                }
            }

            scanner.close();

        } catch (Exception e) {
            System.err.println("Error reading this file");
            System.exit(1);
        }

        StringBuilder str = new StringBuilder();

        for (Character c : uniqueCharacters) {
            str.append("'").append(c).append("=").append((int) c).append(" ");
        }

        System.out.println(str.toString().trim());
        System.out.println("Total Unique Chracters:" + uniqueCharacters.size());

        return uniqueCharacters.size();

    }

    /**
     * Run both reportCounts and countChars on first command-line arg
     * @param args the path to the tect
     */
    public static void main(String args[]) {
        reportCounts(args[0]);
        countChars(args[0]);
    }

}

/**
 * Hash map from char keys to int values using chaining to resolve collisions
 */

class LetterCounter {
    private static class Pair{
        char key;
        int value;

        /**
         * Contructs a new Pair with the given key and value
         * 
         * @param key the character key
         * @param value the associated integer value
         */
        Pair(char key, int value){
            this.key = key;
            this.value = value;
        }
    }

    private static final int N_BUCKETS = 128;

    private List<Pair>[] buckets;

    @SuppressWarnings("unchecked")
    public LetterCounter(){
        buckets = new List[N_BUCKETS];
    }


    /**
     * Maps a chracter to a bucked index in [0, N_BUCKETS]
     * 
     * @param ch the character to hash
     * @return a valid index into the buckets array
     */
    private int bucketIndex(char ch){
        return(int) ch % N_BUCKETS;
    }

    /**
     * Returns the bucket list for ch
     *  
     * @param ch the chracters whose bucket is needed
     * @return the list for for ch
     */
    private List<Pair> getBucket(char ch){
        int index = bucketIndex(ch);
        if(buckets[index] == null){
            buckets[index] = new ArrayList<>();
        }
        return buckets[index];
    }

    /**
     * Searches the bucket for a pair whose key equals ch
     * 
     * @param ch the chracter to find
     * @return the matching pair or null if it doesn't exist.
     */
    private Pair findPair(char ch){
        List<Pair> bucket = getBucket(ch);
        for(Pair p : bucket){
            if (p.key == ch) {
                return p;
            }
        }
        return null;
    }
    
    /**
     * Returns true if the map contains an entry for ch
     * 
     * @param ch the character to check
     * @return true if the map contains an entry, false otherwise
     */
    public boolean hasKey(char ch){
        return findPair(ch) != null;
    }

    /**
     * Associates ch with the value v in this map.
     * 
     * @param ch the character key
     * @param v the integer value to store.
     */
    public void put(char ch, int v){
        Pair existing = findPair(ch);
        if(existing != null){
            existing.value = v;
        } else {
            getBucket(ch).add(new Pair(ch, v));
        }
    }

    /**
     * Returns the integer value associated with ch
     * 
     * @param ch the chracter to look up
     * @return the value associated with ch
     * @throws IllegalArgumentExeception if ch has not entry in this map
     */
    public int get(char ch){
        Pair p = findPair(ch);
        if(p == null){
            throw new IllegalArgumentException("No valid entry for character");
        }
        return p.value;
    }
}