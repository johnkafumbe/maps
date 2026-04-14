package edu.ttap.lootgenerator;

/**
 * Simple data object representing a monster with a name, type, level and
 * associated treasure class.
 */
public class Monster {
    private String name;

    private String type;

    private int level;

    private String tc;

    /**
     * Construct a Monster.
     *
     * @param name  the monster name
     * @param type  the monster type
     * @param level the monster level
     * @param tc    the treasure class name for this monster
     */
    Monster(String name, String type, int level, String tc) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.tc = tc;
    }

    /**
     * @return the monster name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the monster type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the monster level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return the treasure class name for the monster
     */
    public String getTC() {
        return tc;
    }
}
