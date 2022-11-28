package com.denysborozenets.secondtask;

public enum Keys {

    TYPE_KEY("type"),
    FINE_AMOUNT_KEY("fine_amount"),
    SPEEDING("SPEEDING"),
    RED_TRAFFIC_LIGHT("RED_TRAFFIC_LIGHT"),
    PARKING("PARKING");

    private final String value;

    Keys(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
