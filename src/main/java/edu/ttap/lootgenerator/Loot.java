package edu.ttap.lootgenerator;

import java.util.Random;

/**
 * Represents a piece of generated loot consisting of a base Armor and
 * optional prefix/suffix affixes.
 */
public class Loot {
    private Affix prefix;

    private Affix suffix;

    private Armor base;

    private String stat;

    /**
     * Create a Loot object.
     *
     * @param prefix optional prefix affix 
     * @param suffix optional suffix affix 
     * @param base   the base armor item
     * @param stat   the base stat string for the armor 
     */
    Loot(Affix prefix, Affix suffix, Armor base, String stat) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.base = base;
        this.stat = stat;
    }

    /**
     * @return the full display name for the loot including any affixes
     */
    public String getFullName() {
        String result = "";

        if (prefix != null) {
            result += prefix.getName() + " ";
        }

        result += base.getName() + " ";

        if (suffix != null) {
            result += suffix.getName();
        }

        return result;
    }

    /**
     * @return a string describing the base stats for this loot
     */
    public String getBaseStats() {
        return "Defense: " + stat;
    }

    /**
     * @return a string describing the randomly generated affix stats
     */
    public String getAffixStats() {
        String result = "";

        if (prefix != null) {
            Random r = new Random();
            int stat = r.nextInt(prefix.getMax() - prefix.getMin() + 1) + prefix.getMin();
            result += "Prefix: " + stat + " " + prefix.getMod();
            if (suffix != null) {
                result += "\n";
            }
        }

        if (suffix != null) {
            Random r = new Random();
            int stat = r.nextInt(suffix.getMax() - suffix.getMin() + 1) + suffix.getMin();
            result += "Suffix: " + stat + " " + suffix.getMod();
        }

        return result;
    }
}
