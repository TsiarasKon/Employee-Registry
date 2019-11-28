package com.unisystems.registry;

import com.unisystems.registry.authority.Authority;
import com.unisystems.registry.authority.AuthorityRepository;
import com.unisystems.registry.business_unit.BusinessUnit;
import com.unisystems.registry.business_unit.BusinessUnitRepository;
import com.unisystems.registry.company.Company;
import com.unisystems.registry.company.CompanyRepository;
import com.unisystems.registry.department.Department;
import com.unisystems.registry.department.DepartmentRepository;
import com.unisystems.registry.employee.Employee;
import com.unisystems.registry.employee.EmployeeContractType;
import com.unisystems.registry.employee.EmployeePosition;
import com.unisystems.registry.employee.EmployeeRepository;
import com.unisystems.registry.unit.Unit;
import com.unisystems.registry.unit.UnitRepository;
import com.unisystems.registry.user.User;
import com.unisystems.registry.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

@SpringBootApplication
public class RegistryApplication implements CommandLineRunner {

	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	BusinessUnitRepository buRepository;
	@Autowired
    DepartmentRepository deptRepository;
	@Autowired
	UnitRepository unitRepository;
	@Autowired
    EmployeeRepository employeeRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	AuthorityRepository authorityRepository;

	public static void main(String[] args) {
		SpringApplication.run(RegistryApplication.class, args);
	}

	@Override
	public void run(String... args) {
		// ideas for company structure taken from: https://www.unisystems.com/markets
		Company uniSystems = new Company("UniSystems");

		BusinessUnit financeBU = new BusinessUnit("Finance");
		financeBU.setCompany(uniSystems);
		BusinessUnit telecomBU = new BusinessUnit("Telecommunications");
        telecomBU.setCompany(uniSystems);

		Department bankingD = new Department("Banking");
		bankingD.setBu(financeBU);
		Department infrastructureD = new Department("Infrastructure");
		infrastructureD.setBu(telecomBU);
		Department networkingD = new Department("Networking");
		networkingD.setBu(telecomBU);

		Unit coreBankingU = new Unit("Core Banking");
		coreBankingU.setDept(bankingD);
		Unit paymentU = new Unit("Payment");
		paymentU.setDept(bankingD);
		Unit storageU = new Unit("Storage Solutions");
		storageU.setDept(infrastructureD);
		Unit servicesU = new Unit("Managed Services");
		servicesU.setDept(infrastructureD);
		Unit fileU = new Unit("File Archiving");
		fileU.setDept(infrastructureD);
		Unit t4gU = new Unit("4G");
		t4gU.setDept(networkingD);
		Unit t5gU = new Unit("5G");
		t5gU.setDept(networkingD);

		// ideas for employees definitely not taken from: https://www.unisystems.com
		Employee[] employeeArr = new Employee[10];
		employeeArr[0] = new Employee("Konstantinos", "Tsiaras", "Kolokotroni 11, Agia Paraskevi", "6980429197", LocalDate.of(2019, 11, 2), null, true, EmployeeContractType.INTERNAL, EmployeePosition.PROGRAMMER);
		employeeArr[0].setUnit(coreBankingU);
		// using simplified constructor for the rest of the employees for simplicity's sake:
		employeeArr[1] = new Employee("Scrooge", "McDuck", "6931331313", LocalDate.of(2000, 2, 13), EmployeeContractType.INTERNAL, EmployeePosition.ACCOUNTANT);
		employeeArr[1].setUnit(coreBankingU);
		employeeArr[2] = new Employee("Steve", "Rogers", "6912345678", LocalDate.of(2012, 6, 22), EmployeeContractType.EXTERNAL, EmployeePosition.SCRUM_MASTER);
		employeeArr[2].setUnit(paymentU);
		employeeArr[3] = new Employee("Lecter", "Hannibal", "6987654321", LocalDate.of(2018, 3, 15), EmployeeContractType.INTERNAL, EmployeePosition.DELIVERY_MANAGER);
		employeeArr[3].setUnit(storageU);
		employeeArr[4] = new Employee("Dennis", "Ritchie", "6900000000", LocalDate.of(1970, 1, 1), EmployeeContractType.INTERNAL, EmployeePosition.PROGRAMMER);
		employeeArr[4].setStatus(false);
		employeeArr[4].setExitDate(LocalDate.of(2000, 1, 1));
		employeeArr[4].setUnit(storageU);
		employeeArr[5] = new Employee("Benedict", "Cucumber", "6988596340", LocalDate.of(2013, 3, 5), EmployeeContractType.INTERNAL, EmployeePosition.ACCOUNTANT);
		employeeArr[5].setUnit(fileU);
		employeeArr[6] = new Employee("Keanu", "Reeves", "6935978201", LocalDate.of(2011, 1, 30), EmployeeContractType.EXTERNAL, EmployeePosition.DELIVERY_MANAGER);
		employeeArr[6].setUnit(fileU);
		employeeArr[7] = new Employee("Bruce", "Dickinson", "6966666666", LocalDate.of(2015, 11, 10), EmployeeContractType.INTERNAL, EmployeePosition.SCRUM_MASTER);
		employeeArr[7].setUnit(t4gU);
		employeeArr[8] = new Employee("Freddie", "Mercury", "6912457869", LocalDate.of(2013, 12, 8), EmployeeContractType.INTERNAL, EmployeePosition.SENIOR_ANALYST);
		employeeArr[8].setStatus(false);
		employeeArr[8].setExitDate(LocalDate.of(2019, 10, 22));
		employeeArr[8].setUnit(t4gU);
		employeeArr[9] = new Employee("Obi Wan", "Kenobi", "6911111111", LocalDate.of(1973, 4, 4), EmployeeContractType.EXTERNAL, EmployeePosition.SENIOR_ANALYST);
		employeeArr[9].setUnit(t5gU);

		//Define all HashSets
		HashSet<Authority> admin_auth = new HashSet<Authority>();
		HashSet<Authority> companyManager_auth = new HashSet<Authority>();
		HashSet<Authority> businessUnitManager_auth = new HashSet<Authority>();
		HashSet<Authority> departmentManager_auth = new HashSet<Authority>();
		HashSet<Authority> unitManager_auth = new HashSet<Authority>();
		HashSet<Authority> employee_auth = new HashSet<Authority>();

		//Add users and authorities
		User[] allUsers = new User[6];
		allUsers[0] = new User("admin","123456",true,admin_auth);
		allUsers[1] = new User("companyManager","123456",true,companyManager_auth);
		allUsers[2] = new User("businessUnitManager","123456",true,businessUnitManager_auth);
		allUsers[3] = new User("departmentManager","123456",true,departmentManager_auth);
		allUsers[4] = new User("unitManager","123456",true,unitManager_auth);
		allUsers[5] = new User("employee","123456",true,employee_auth);

		Authority[] allAuthorities = new Authority[6];
		allAuthorities[0] = new Authority("ROLE_ADMIN",allUsers[0]);
		admin_auth.add(allAuthorities[0]);
		allAuthorities[1] = new Authority("ROLE_COMPANY_MANAGER",allUsers[1]);
		companyManager_auth.add(allAuthorities[1]);
		allAuthorities[2] = new Authority("ROLE_BUSINESS_MANAGER",allUsers[2]);
		businessUnitManager_auth.add(allAuthorities[2]);
		allAuthorities[3] = new Authority("ROLE_DEPARTMENT_MANAGER",allUsers[3]);
		departmentManager_auth.add(allAuthorities[3]);
		allAuthorities[4] = new Authority("ROLE_UNIT_MANAGER",allUsers[4]);
		unitManager_auth.add(allAuthorities[4]);
		allAuthorities[5] = new Authority("ROLE_EMPLOYEE",allUsers[5]);
		employee_auth.add(allAuthorities[5]);


		companyRepository.save(uniSystems);
		buRepository.saveAll(Arrays.asList(financeBU, telecomBU));
		deptRepository.saveAll(Arrays.asList(bankingD, infrastructureD, networkingD));
		unitRepository.saveAll(Arrays.asList(coreBankingU, paymentU, storageU, servicesU, fileU, t4gU, t5gU));
		employeeRepository.saveAll(Arrays.asList(employeeArr));
		userRepository.saveAll(Arrays.asList(allUsers));
		authorityRepository.saveAll(Arrays.asList(allAuthorities));

	}
}
