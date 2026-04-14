package edu.ttap.lootgenerator;

import java.util.Random;

public class Loot {
    private Affix prefix;
    private Affix suffix;

    private Armor base;
    private String stat;

    Loot(Affix prefix, Affix suffix, Armor base, String stat) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.base = base;
        this.stat = stat;
    }

    public String getFullName() {
        String result = "";

        if (prefix != null)
            result += prefix.getName() + " ";

        result += base.getName() + " ";

        if (suffix != null)
            result += suffix.getName();

        return result;
    }

    public String getBaseStats() {
        return "Defense: " + stat;
    }

    public String getAffixStats() {
        String result = "";

        if (prefix != null) {
            Random r = new Random();
            int stat = r.nextInt(prefix.getMax() - prefix.getMin() + 1) + prefix.getMin();
            result += "Prefix: " + stat + " " + prefix.getMod();
            if (suffix != null)
                result += "\n";
        }

        if (suffix != null) {
            Random r = new Random();
            int stat = r.nextInt(suffix.getMax() - suffix.getMin() + 1) + suffix.getMin();
            result += "Suffix: " + stat + " " + suffix.getMod();
        }

        return result;
    }
}
