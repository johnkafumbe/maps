package edu.ttap.lootgenerator;

/**
 * Represents a generic affix that can modify an item's stats.
 */
public abstract class Affix {
    private String name;
    
    private String mod;

    private int min;

    private int max;

    /**
     * Create a new affix.
     *
     * @param name readable name of the affix 
     * @param mod  the stat modified by this affix 
     * @param min  minimum value for the affix modifier
     * @param max  maximum value for the affix modifier
     */
    Affix(String name, String mod, int min, int max) {
        this.name = name;
        this.mod = mod;
        this.min = min;
        this.max = max;
    }

    /**
     * @return the affix name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the stat modified by this affix
     */
    public String getMod() {
        return mod;
    }

    /**
     * @return the minimum modifier value for this affix
     */
    public int getMin() {
        return min;
    }

    /**
     * @return the maximum modifier value for this affix
     */
    public int getMax() {
        return max;
    }
}
