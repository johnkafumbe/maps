package edu.ttap.lootgenerator;

public abstract class Affix {
    private String name;
    private String mod;
    private int min;
    private int max;

    Affix(String name, String mod, int min, int max) {
        this.name = name;
        this.mod = mod;
        this.min = min;
        this.max = max;
    }

    public String getName() {
        return name;
    }

    public String getMod() {
        return mod;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
