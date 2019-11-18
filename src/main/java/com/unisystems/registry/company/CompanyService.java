package com.unisystems.registry.company;

import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;
import com.unisystems.registry.InvalidIdException;
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

    public CompanyService(CompanyMapper mapper, CompanyRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public GenericResponse<MultipleCompaniesResponse> getAllCompany() {
        Iterable<Company> retrievedCompanies = repository.findAll();
        List<CompanyResponse> companies = new ArrayList<>();

        for (Company company : retrievedCompanies){
            companies.add(mapper.mapCompanyResponseFromCompany(company));
        }
        return new GenericResponse<>(new MultipleCompaniesResponse(companies));
    }

    public GenericResponse<CompanyResponse> getCompanyWithId(long id) {
        try {
            return new GenericResponse<>(mapper.mapCompanyResponseFromCompany( repository.findById(id).orElseThrow(()
                    -> new InvalidIdException("Company", id))));
        } catch (InvalidIdException e) {
            return new GenericResponse<>(new GenericError(1, "Invalid id", e.getMessage()));
        }
    }
}
