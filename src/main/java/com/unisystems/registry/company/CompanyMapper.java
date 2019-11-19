package com.unisystems.registry.company;

import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

        public CompanyResponse mapCompanyResponseFromCompany(Company company){

            return new CompanyResponse(
                    company.getId(),
                    company.getName()
            );
        }

}
