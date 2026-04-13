package edu.ttap.lootgenerator;

public class Loot {
    private Prefix prefix;
    private Suffix suffix;

    private Armor base;

    Loot(Prefix prefix, Suffix suffix, Armor base) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.base = base;
    }

    Loot(Suffix suffix, Armor base) {
        this.prefix = null;
        this.suffix = suffix;
        this.base = base;
    }

     Loot(Prefix prefix, Armor base) {
        this.prefix = prefix;
        this.suffix = null;
        this.base = base;
    }

    Loot(Armor base) {
        this.prefix = null;
        this.suffix = null;
        this.base = base;
    }


}
