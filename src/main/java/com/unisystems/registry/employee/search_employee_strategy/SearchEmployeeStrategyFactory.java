package com.unisystems.registry.employee.search_employee_strategy;

import org.springframework.stereotype.Component;

@Component
public class SearchEmployeeStrategyFactory {
    public SearchEmployeeStratrgy makeStrategyForCriteria(String criteria) {
        switch (criteria.toLowerCase()) {
            case "company":
                return new SearchEmployeeByCompanyStrategy();
            case "businessunit":
                return new SearchEmployeeByBusinessUnitStrategy();
            case "department":
                return new SearchEmployeeByDepartmentStrategy();
            case "unit":
            default:
                return new SearchEmployeeByUnitStrategy();
        }
    }
}
