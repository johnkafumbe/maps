package edu.ttap.lootgenerator;

/**
 * Represents a base armor item with a name and an AC range.
 */
public class Armor {
    private String name;

    private String minac;

    private String maxac;

    /**
     * Construct a new Armor.
     *
     * @param name  the armor name
     * @param minac the minimum AC value (as string from dataset)
     * @param maxac the maximum AC value (as string from dataset)
     */
    Armor(String name, String minac, String maxac) {
        this.name = name;
        this.minac = minac;
        this.maxac = maxac;
    }

    /**
     * @return the armor name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return the minimum AC value (as string)
     */
    public String getMin() {
        return minac;
    }

    /**
     * @return the maximum AC value (as string)
     */
    public String getMax() {
        return maxac;
    }
}
