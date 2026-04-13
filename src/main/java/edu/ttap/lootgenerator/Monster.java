package edu.ttap.lootgenerator;

public class Monster {
    private String name;
    private String type;
    private int level;
    private String TC;

    Monster(String name, String type, int level, String TC) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.TC = TC;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public String getTC() {
        return TC;
    }
}
