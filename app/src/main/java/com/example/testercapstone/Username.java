package com.example.testercapstone;

public class Username {
    private static Username instance;
    private String username;

    private Username() {}

    public static synchronized Username getInstance() {
        if (instance == null) {
            instance = new Username();
        }
        return instance;
    }

    public String getSharedVariable() {
        return username;
    }

    public void setSharedVariable(String value) {
        this.username = value;
    }
}

