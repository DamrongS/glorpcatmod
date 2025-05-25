package net.damrongmc.entity;

import java.util.Arrays;
import java.util.Comparator;

public enum GlorpVariant {
    NORMAL(0),
    FAT(1),
    MAID(2);

    private static final GlorpVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(GlorpVariant::getId)).toArray(GlorpVariant[]::new);
    private final int id;

    GlorpVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static GlorpVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}