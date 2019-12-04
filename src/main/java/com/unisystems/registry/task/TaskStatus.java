package com.unisystems.registry.task;

public enum TaskStatus {
    NEW("New"),
    STARTED("Started"),
    DONE("Done");

    private String str;

    TaskStatus(String str) {this.str = str;}

    @Override
    public String toString() {
        return str;
    }
}
