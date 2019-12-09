package com.unisystems.registry.task.search_task_strategy;

public class difficultyComparison {
    private String[] structure = {"easy", "medium", "hard"};

    public boolean checkIfInStructure(String difficulty) {
        for (String s : structure) {
            if (s.equalsIgnoreCase(difficulty)) {
                return true;
            }
        }
        return false;
    }
}
