package com.unisystems.registry;

public class InvalidIdException extends Exception {
    public InvalidIdException(String section, long id) {
        super(section + " with id " + id + " does not exist");
    }
}
