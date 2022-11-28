package com.denysborozenets.secondtask;

import java.util.Map;

public class FinesRoot {
    private Map<String, Integer> fines;

    public Map<String, Integer> getFines() {
        return fines;
    }

    public void setFines(Map<String, Integer> fines) {
        this.fines = fines;
    }

    @Override
    public String toString() {
        return "FinesRoot{" +
                "fines=" + fines +
                '}';
    }
}
