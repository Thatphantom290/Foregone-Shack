package com.barnacle.foregone_shack.client.render.variants;

import java.util.Arrays;
import java.util.Comparator;

public enum PopimVariant {

    RED(0),
    ORANGE(1),
    BLUE(2);

    private static final PopimVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(PopimVariant::getId)).toArray(PopimVariant[]::new);
    private final int id;

    PopimVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static PopimVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}