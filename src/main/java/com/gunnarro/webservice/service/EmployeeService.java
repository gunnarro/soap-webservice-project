package com.gunnarro.webservice.service;

import com.gunnarro.employee.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeFactory;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class EmployeeService {

    public List<Employee> getEmployeesByName(String firstName, String lastName) throws Exception{
            log.info("get: {}, {}", firstName, lastName);
            Employee employee1 = new Employee();
            employee1.setId(1);
            employee1.setFirstname("Jo");
            employee1.setLastname("Lewis");
            employee1.setGender("M");
            employee1.setBirthdate(DatatypeFactory.newInstance().newXMLGregorianCalendar("2000-01-01"));

            Employee employee2 = new Employee();
            employee2.setId(2);
            employee2.setFirstname("Francis");
            employee2.setLastname("Stevens");
            employee2.setGender("M");
            employee2.setBirthdate(DatatypeFactory.newInstance().newXMLGregorianCalendar("1999-01-01"));

            return Arrays.asList(employee1, employee2);
    }

    public Employee getEmployeeById(long id) throws Exception{
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstname("John");
        employee.setLastname("Miller");
        employee.setBirthdate(DatatypeFactory.newInstance()
                .newXMLGregorianCalendar("1999-01-01"));
        return employee;
    }
}
