package edu.ttap.lootgenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.io.File;

public class LootGenerator {
    /** The path to the dataset (either the small or large set). */
    private static final String DATA_SET = "data/small";

    private static ArrayList<Monster> monsters = new ArrayList<>();
    private static HashMap<String, String[]> treasures = new HashMap<>();
    private static HashMap<String, String[]> armors = new HashMap<>();
    private static HashMap<String, String[]> prefixes = new HashMap<>();
    private static HashMap<String, String[]> suffixes = new HashMap<>();

    public static void scanTCs(HashMap<String, String[]> treasures) {
        try {
            Scanner s = new Scanner(new File(DATA_SET + "/monstats.txt"));
            s.useDelimiter("\t");

            while (s.hasNextLine()) {
                String name = s.next();
                String i1 = s.next();
                String i2 = s.next();
                String i3 = s.next();
                String[] drops = {i1, i2, i3};

                TreasureClass tc = new TreasureClass(name, drops);
                treasures.put(name, drops);
            }

            s.close();
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    public static void scanMonsters(ArrayList<Monster> monsters) {
        try {
            Scanner s = new Scanner(new File(DATA_SET + "/monstats.txt"));
            s.useDelimiter("\t");

            while (s.hasNextLine()) {
                String name = s.next();
                String type = s.next();
                int level = s.nextInt();
                String TC = s.next();

                Monster mon = new Monster(name, type, level, TC);
                monsters.add(mon);
            } 

            s.close();
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    public static void scanArmors(HashMap<String, String[]> armors) {
        try {
            Scanner s = new Scanner(new File(DATA_SET + "/armor.txt"));
            s.useDelimiter("\t");

            while (s.hasNextLine()) {
                String name = s.next();
                String minac = s.next();
                String maxac = s.next();
                String[] range = {minac, maxac};

                armors.put(name, range);
            }

            s.close();
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    public static void scanPrefixes(HashMap<String, String[]> prefixes) {
        try {
            Scanner s = new Scanner(new File(DATA_SET + "/MagicPrefix.txt"));
            s.useDelimiter("\t");

            while (s.hasNextLine()) {
                String name = s.next();
                String mod = s.next();
                String minac = s.next();
                String maxac = s.next();
                String[] data = {mod, minac, maxac};
                
                prefixes.put(name, data);
            }

            s.close();
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    public static void scanSuffixes(HashMap<String, String[]> suffixes) {
        try {
            Scanner s = new Scanner(new File(DATA_SET + "/MagicSuffix.txt"));
            s.useDelimiter("\t");

            while (s.hasNextLine()) {
                String name = s.next();
                String mod = s.next();
                String minac = s.next();
                String maxac = s.next();
                String[] data = {mod, minac, maxac};
                
                suffixes.put(name, data);
            }

            s.close();
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    public static Monster pickMonster() {
        Random r = new Random();
        return monsters.get(r.nextInt(monsters.size()));
    }

    public static TreasureClass fetchTreasureClass(Monster monster) {
        TreasureClass tc = new TreasureClass(monster.getTC(), treasures.get(monster.getTC()));
        return tc;
    }

    public static String generateBaseItemH(String tc) {
        Random r = new Random();
        String selection = treasures.get(tc)[r.nextInt(3)];
        if (treasures.containsKey(selection)) {
            selection = generateBaseItemH(selection);
        }

        return selection;
    }

    public static String generateBaseItem(TreasureClass tc) {
        return generateBaseItemH(tc.getName());
    }

    public static String generateBaseStats(Armor a) {
        Random r = new Random();
        int min = a.getMin();
        int max = a.getMax();

        int stat = r.nextInt(max - min + 1) + min;
        return Integer.toString(stat);
    } 

    public static String generateAffix() {
        Random r = new Random();
        
    }

    public static Loot generateLoot() {
        // Pick Monster
        // Get TC
        // Get Base Item
        // Get Base Stats
        // Get Affixes
        // Create Loot Item

        Monster monster = pickMonster();
        String item = generateBaseItem(fetchTreasureClass(monster));
    }

    public static void main(String[] args) {
        boolean ingame = true;
        String answer = "";

        // scan data files
        scanTCs(treasures);
        scanMonsters(monsters);
        scanArmors(armors);
        scanPrefixes(prefixes);
        scanSuffixes(suffixes);

        try {
            Scanner s = new Scanner(System.in);

            // ========== START GAME LOOP ==========
            while (ingame) {
                answer = "";
                System.out.println("Fighting <monster name>...");
                System.out.println("You have slain <monster name>!");
                System.out.println("<monster name> dropped:\n");

                System.out.println("<complete item name>");
                System.out.println("<base item statistic>");
                System.out.println("<additional affix statistics>");

                while (!answer.toLowerCase().equals("y") && !answer.toLowerCase().equals("n")) {
                    System.out.println("Fight again [y/n]?");

                    answer = s.next();
                    if (answer.toLowerCase().equals("n"))
                        ingame = false;
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