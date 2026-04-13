package edu.ttap.lootgenerator;

public class Armor {
    private String name;
    private int minac;
    private int maxac;

    Armor(String name, int minac, int maxac) {
        this.name = name;
        this.minac = minac;
        this.maxac = maxac;
    }

    public String getName() {
        return name;
    }
    
    public int getMin() {
        return minac;
    }

    public int getMax() {
        return maxac;
    }
}
