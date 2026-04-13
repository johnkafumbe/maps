package edu.ttap.maps;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.io.File;

/**
 * A substitution cipher is a simple encryption scheme that associates each
 * letter of the alphabet with a different letter.
 */
public class SubstitutionCipher {
    /**
     * Creates a substitution cipher by reading a mapping of characters from the
     * given
     * file. Each mapping of the file should be of the form "a b", where 'a' is
     * mapped to
     * 'b' in the cipher. We require
     * 
     * @param filename the name of the file containing the mapping
     * @return the cipher as a mapping between characters
     */
    public static Map<Character, Character> createCipher(String filename) {
        Map<Character, Character> cipher = new AssociationList<>();

        try {
            Scanner scanner = new Scanner(new File(filename));

            while (scanner.hasNextLine()) {
                char[] line = scanner.nextLine().toCharArray();

                cipher.put(line[0], line[2]);
            }

            scanner.close();
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
        return cipher;
    }

    /**
     * Determines whether the given mapping is a valid substitution cipher. A cipher
     * is
     * valid if (a) it maps every letter of the alphabet (aâ€“z) and (b) it is a
     * bijection,
     * i.e., no two letters map to the same letter (so that we can roundtrip
     * encode/decode
     * a message without loss of fidelity).
     * 
     * @param cipher
     * @return true iff the given mapping is a valid substitution cipher
     */
    public static boolean isValidCipher(Map<Character, Character> cipher) {
        Collection<Character> values = cipher.values();

        Set<Character> hashValues = new HashSet<>();
        for (Character ch : values) {
            if (hashValues.contains(ch)) {
                return false;
            }
            hashValues.add(ch);
        }

        return true;
    }

    /**
     * Given a valid substitution cipher, produces the inverse mapping, which
     * can be used to decode the encoded massage. For example, if the cipher
     * maps 'a' to 'b', then the inverse mapping should map 'b' to 'a'.
     * 
     * @param cipher the cipher to invert
     * @return the inverse mapping of the given cipher
     */
    public static Map<Character, Character> invertCipher(Map<Character, Character> cipher) {
        Map<Character, Character> result = new AssociationList<>();

        for (Character key : cipher.keySet()) {
            result.put(cipher.get(key), key);
        }

        return result;
    }

    /**
     * Translates the given string using the provided mapping.
     * 
     * @param s       the string to translate
     * @param mapping the mapping to use
     * @return the translated string
     */
    public static String translate(String s, Map<Character, Character> mapping) {
        String result = "";
        char[] ch = s.toCharArray();

        for (char c : ch) {
            if (c == ' ') {
                result += " ";
            } else {
                result += mapping.get(c);
            }
        }

        return result;
    }

    /**
     * The main driver for the substitution cipher program.
     * 
     * @param args the driver's command-line arguments
     */
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println(
                    "Usage: java SubstitutionCipher <encode|decode> <cipherfile> <filename>");
            System.exit(1);
        }

        // set cipher for encrypt vs. decrypt
        Map<Character, Character> map = new AssociationList<>();
        if (args[0].trim().equals("encrypt")) {
            map = createCipher(args[1]);
        } else if (args[0].trim().equals("decrypt")) {
            map = invertCipher(createCipher(args[1]));
        } else {
            System.err.println("Select either encryption or decryption");
            System.exit(1);
        }

        // scan file and translate lines
        try {
            Scanner scanner = new Scanner(new File(args[2]));
            while (scanner.hasNextLine()) {
                System.out.println(translate(scanner.nextLine(), map));
            }

            scanner.close();
        } catch (Exception e) {
            System.err.println("Error scanning text");
            System.exit(1);
        }
    }
}