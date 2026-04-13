package edu.ttap.lootgenerator;

public class TreasureClass {
    private String TCName;
    private String[] drops;

    TreasureClass(String TCName, String[] drops) {
        this.TCName = TCName;
        this.drops = drops;
    }

    public String getName() {
        return TCName;
    }

    public String[] getDrops() {
        return drops;
    }
}
