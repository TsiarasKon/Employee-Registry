package com.unisystems.registry.task;

public enum Status {
    NEW ("New"),
    STARTED("Started"),
    DONE("Done");

    private String str;
    Status(String str){this.str = str;}

    @Override
    public String toString(){return str;}
}
