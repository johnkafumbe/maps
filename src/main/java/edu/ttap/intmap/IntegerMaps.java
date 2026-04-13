package edu.ttap.intmap;

import java.io.File;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;


/**
 * 
 */
public class IntegerMaps {

// Part 1

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

// 1. Pride and Prejudice
// link: https://www.gutenberg.org/cache/epub/1342/pg1342.txt
// It is consistent with the known frequency of letters because E appears most frequently.
//

// 2. Moby Dick
// link: https://www.gutenberg.org/ebooks/2701
// Similary Moby Dick is also consistent with the frequency of letters becuase E appears most
// frequently.


// Part 2

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



    public static void main(String args[]) {
        reportCounts(args[0]);
        countChars(args[0]);
    }

}

// Part 3 

class LetterCounter {
    private static class Pair{
        char key;
        int value;

        Pair(char key, int value){
            this.key = key;
            this.value = value;
        }
    }

    private static final int N_BUCKETS = 128;

    private List<Pair>[] buckets;

    public LetterCounter(){
        buckets = new List[N_BUCKETS];
    }

    // Private helpers 

    private int bucketIndex(char ch){
        return(int) ch % N_BUCKETS;
    }

    private List<Pair> getBucket(char ch){
        int index = bucketIndex(ch);
        if(buckets[index] == null){
            buckets[index] = new ArrayList<>();
        }
        return buckets[index];
    }

    private Pair findPair(char ch){
        List<Pair> bucket = getBucket(ch);
        for(Pair p : bucket){
            if (p.key == ch) {
                return p;
            }
        }
        return null;
    }
    
    // Public helpers

    public boolean hasKey(char ch){
        return findPair(ch) != null;
    }

    public void put(char ch, int v){
        Pair existing = findPair(ch);
        if(existing != null){
            existing.value = v;
        } else {
            getBucket(ch).add(new Pair(ch, v));
        }
    }

    public int get(char ch){
        Pair p = findPair(ch);
        if(p == null){
            throw new IllegalArgumentException("No valid entry for character");
        }
        return p.value;
    }
}