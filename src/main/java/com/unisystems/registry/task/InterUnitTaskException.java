package com.unisystems.registry.task;

public class InterUnitTaskException extends Exception {
    public InterUnitTaskException() {
        super("The same Task cannot be assigned to Employees from different Units!");
    }
}
