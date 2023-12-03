package edu.hw8.Task3;

import java.util.HashMap;
import java.util.Map;

public class PasswordDB {

    public final static Map<String, String> PASSWORDS = new HashMap<>();

    static {
        PASSWORDS.put("893b56e3cfe153fb770a120b83bac20c", "a.v.petrov");
        PASSWORDS.put("2b62d10f36589e0bf7cc6508afcac1df", "v.v.belov");
        PASSWORDS.put("4dac9d9b526cdbea9414ef6408fde364", "a.s.ivanov");
        PASSWORDS.put("ceca0f37001353c2c62c9ce29b85275d", "k.p.maslov");
    }

    private PasswordDB() {}
}
