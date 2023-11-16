package edu.hw6.Task6;

public enum Protocol {
    TCP("Tcp"),
    UDP("Udp");

    private final String name;

    Protocol(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
