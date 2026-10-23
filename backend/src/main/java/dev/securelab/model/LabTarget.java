package dev.securelab.model;

import java.util.Arrays;
import java.util.Optional;

public enum LabTarget {

    MINIDOCS("minidocs"),
    MINISHOP("minishop"),
    MINIBOARD("miniboard");

    private final String key;

    LabTarget(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public static Optional<LabTarget> fromKey(String key) {

        if (key == null) {
            return Optional.empty();
        }

        return Arrays.stream(values())
                .filter(target -> target.getKey().equalsIgnoreCase(key.trim()))
                .findFirst();
    }
}