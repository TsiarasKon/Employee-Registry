package com.unisystems.registry.company;

import com.unisystems.registry.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CompanyService {

    @Autowired
    private CompanyMapper mapper;

    @Autowired
    CompanyRepository repository;


    public GenericResponse<MultipleCompaniesResponse> getAllCompany() {
        Iterable<Company> retrivedCompanies = repository.findAll();
        List<CompanyResponse> companies = new ArrayList<>();

        for (Company company : retrivedCompanies){
            companies.add(mapper.mapCompanyResponseFromCompany(company));
        }
        return new GenericResponse<>(new MultipleCompaniesResponse(companies));
    }
}
