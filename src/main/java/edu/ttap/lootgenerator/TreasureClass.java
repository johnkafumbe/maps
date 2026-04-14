package edu.ttap.lootgenerator;

/**
 * Represents a treasure class with a name and three drop entries.
 */
public class TreasureClass {
    private String tcName;

    private String[] drops;

    /**
     * Construct a TreasureClass.
     *
     * @param tcName the name of the treasure class
     * @param drops  an array of three entries describing possible drops
     */
    TreasureClass(String tcName, String[] drops) {
        this.tcName = tcName;
        this.drops = drops;
    }

    /**
     * @return the treasure class name
     */
    public String getName() {
        return tcName;
    }

    /**
     * @return the array of drops for this treasure class
     */
    public String[] getDrops() {
        return drops;
    }
}
