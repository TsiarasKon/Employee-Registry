package com.unisystems.registry.task.search_task_strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchTaskStrategyFactory {
    @Autowired
    SearchEasyTaskStrategy easyTaskStrategy;

    @Autowired
    SearchMediumTaskStrategy mediumTaskStrategy;

    @Autowired
    SearchHardTaskStrategy hardTaskStrategy;

    public SearchTaskStrategy makeStrategyForDifficulty(String difficulty) {
        switch (difficulty.toLowerCase()) {
            case "medium":
                return mediumTaskStrategy;
            case "hard":
                return hardTaskStrategy;
            default:
                return easyTaskStrategy;
        }
    }
}
