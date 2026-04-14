package edu.ttap.lootgenerator;

public class Armor {
    private String name;
    private String minac;
    private String maxac;

    Armor(String name, String minac, String maxac) {
        this.name = name;
        this.minac = minac;
        this.maxac = maxac;
    }

    public String getName() {
        return name;
    }
    
    public String getMin() {
        return minac;
    }

    public String getMax() {
        return maxac;
    }
}
