package com.unisystems.registry.employee.search_employee_strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchEmployeeStrategyFactory {
    @Autowired
    SearchEmployeeByCompanyStrategy companyStrategy;

    @Autowired
    SearchEmployeeByBusinessUnitStrategy buStrategy;

    @Autowired
    SearchEmployeeByDepartmentStrategy deptStrategy;

    @Autowired
    SearchEmployeeByUnitStrategy unitStrategy;

    public SearchEmployeeStratrgy makeStrategyForCriteria(String criteria) {
        switch (criteria.toLowerCase()) {
            case "company":
                return companyStrategy;
            case "businessunit":
                return buStrategy;
            case "department":
                return deptStrategy;
            case "unit":
            default:
                return unitStrategy;
        }
    }
}
