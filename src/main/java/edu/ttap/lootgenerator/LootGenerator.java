package edu.ttap.lootgenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.io.File;

/**
 * A  loot generation program that reads dataset files and produces
 * randomized loot drops for monsters.
 *
 */
public class LootGenerator {
    /** The path to the dataset (either the small or large set). */
    private static final String DATA_SET = "data/small";

    private static ArrayList<Monster> monsters = new ArrayList<>();

    private static HashMap<String, String[]> treasures = new HashMap<>();

    private static HashMap<String, String[]> armors = new HashMap<>();

    private static ArrayList<String[]> prefixes = new ArrayList<>();

    private static ArrayList<String[]> suffixes = new ArrayList<>();

    /**
     * Read the TreasureClassEx.txt file and populate treasure mappings.
     */
    public static void scanTCs() {
        try {
            Scanner s = new Scanner(new File(DATA_SET + "/TreasureClassEx.txt"));

            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] parts = line.split("\t");
                String name = parts[0];
                String[] drops = {parts[1], parts[2], parts[3]};

                treasures.put(name, drops);
            }

            s.close();
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    /**
     * Read monstats.txt and populate the monsters list.
     */
    public static void scanMonsters() {
        try {
            Scanner s = new Scanner(new File(DATA_SET + "/monstats.txt"));

            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] parts = line.split("\t");
                String name = parts[0];
                String type = parts[1];
                int level = Integer.parseInt(parts[2]);
                String TC = parts[3];

                Monster mon = new Monster(name, type, level, TC);
                monsters.add(mon);
            } 

            s.close();
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    /**
     * Read armor.txt and populate the armors map.
     */
    public static void scanArmors() {
        try {
            Scanner s = new Scanner(new File(DATA_SET + "/armor.txt"));

            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] parts = line.split("\t");
                String name = parts[0];
                String minac = parts[1];
                String maxac = parts[2];
                String[] range = {minac, maxac};

                armors.put(name, range);
            }

            s.close();
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    /**
     * Read MagicPrefix.txt and populate prefixes.
     */
    public static void scanPrefixes() {
        try {
            Scanner s = new Scanner(new File(DATA_SET + "/MagicPrefix.txt"));

            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] parts = line.split("\t");
                String[] data = {parts[0], parts[1], parts[2], parts[3]};
                
                prefixes.add(data);
            }

            s.close();
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    /**
     * Read MagicSuffix.txt and populate suffixes.
     */
    public static void scanSuffixes() {
        try {
            Scanner s = new Scanner(new File(DATA_SET + "/MagicSuffix.txt"));

            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] parts = line.split("\t");
                String[] data = {parts[0], parts[1], parts[2], parts[3]};
                
                suffixes.add(data);
            }

            s.close();
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    /**
     * Pick a random monster from the loaded monster list.
     *
     * @return a random Monster
     */
    public static Monster pickMonster() {
        Random r = new Random();
        return monsters.get(r.nextInt(monsters.size()));
    }

    /**
     * Create a TreasureClass object for the given monster.
     *
     * @param monster the monster whose treasure class to fetch
     * @return the corresponding TreasureClass
     */
    public static TreasureClass fetchTreasureClass(Monster monster) {
        TreasureClass tc = new TreasureClass(monster.getTC(), treasures.get(monster.getTC()));
        return tc;
    }

    /**
     * Helper routine that generates a base item name from a treasure class
     * name, following chained treasure classes if necessary.
     *
     * @param tc the treasure class name
     * @return the generated base item name
     */
    public static String generateBaseItemH(String tc) {
        Random r = new Random();
        String selection = treasures.get(tc)[r.nextInt(3)];
        if (treasures.containsKey(selection)) {
            selection = generateBaseItemH(selection);
        }

        return selection;
    }

    /**
     * Generate a base item name for a TreasureClass.
     *
     * @param tc the TreasureClass
     * @return the generated base item name
     */
    public static String generateBaseItem(TreasureClass tc) {
        return generateBaseItemH(tc.getName());
    }

    /**
     * Generate an actual base stat (AC) for the provided Armor using its
     * recorded min/max range.
     *
     * @param a the Armor to generate a stat for
     * @return the generated stat as a string
     */
    public static String generateBaseStats(Armor a) {
        Random r = new Random();
        String min = a.getMin();
        String max = a.getMax();

        int stat = r.nextInt(Integer.parseInt(max) - Integer.parseInt(min) + 1) + Integer.parseInt(min);
        return Integer.toString(stat);
    } 

    /**
     * Generate up to two random affixes (prefix and suffix). Each is
     * present with 50% probability.
     *
     * @return an Affix[] of length 2 where index 0 is prefix and index 1 is suffix
     */
    public static Affix[] generateAffixes() {
        Affix[] result = new Affix[2];
        Random r = new Random();

        // affix: (string) {name, mod, min, max}

        // prefix
        if (r.nextBoolean()) {
            String[] data = prefixes.get(r.nextInt(prefixes.size()));
            Prefix prefix = new Prefix(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]));
            result[0] = prefix;
        }

        // suffix
        if (r.nextBoolean()) {
            String[] data = suffixes.get(r.nextInt(suffixes.size()));
            Suffix suffix = new Suffix(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]));
            result[1] = suffix;
        }

        return result;
    }

    /**
     * Perfoms a full loot generation flow: pick monster, resolve item,
     * create Armor and affixes, and return a Loot object.
     *
     * @return generated Loot
     */
    public static Loot generateLoot() {
        Monster monster = pickMonster();

        System.out.println("Fighting " + monster.getName() + "...");
        System.out.println("You have slain " + monster.getName() + "!");
        System.out.println(monster.getName() + " dropped:\n");

        String item = generateBaseItem(fetchTreasureClass(monster));
        Armor armor = new Armor(item, armors.get(item)[0], armors.get(item)[1]);
        String stat = generateBaseStats(armor);
        Affix[] affixes = generateAffixes();

        Loot result = new Loot(affixes[0], affixes[1], armor, stat);
        return result;
    }

    /**
     * Entry point for running the loot generator as a small interactive
     * console program. Scans data files then repeatedly generates drops
     * until the user chooses to stop.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        boolean ingame = true;
        String answer = "";

        // scan data files
        scanTCs();
        scanMonsters();
        scanArmors();
        scanPrefixes();
        scanSuffixes();

        try {
            Scanner s = new Scanner(System.in);

            // ========== START GAME LOOP ==========
            while (ingame) {
                answer = "";

                Loot loot = generateLoot();

                System.out.println(loot.getFullName());
                System.out.println(loot.getBaseStats());
                System.out.println(loot.getAffixStats());

                while (!answer.toLowerCase().equals("y") && !answer.toLowerCase().equals("n")) {
                    System.out.println("Fight again [y/n]?");

                    answer = s.next();
                    if (answer.toLowerCase().equals("n")) {
                        ingame = false;
                    }
                }
            }
            // ========== END GAME LOOP ==========

            s.close();
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }

    }
}