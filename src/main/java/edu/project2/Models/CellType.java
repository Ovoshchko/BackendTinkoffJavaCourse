package edu.project2.Models;

public enum CellType {
    PASS("   "),
    WALL("XXX");

    private String value;

    CellType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
