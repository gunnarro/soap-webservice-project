package com.gunnarro.webservice.endpoint;

import com.gunnarro.employee.*;
import com.gunnarro.webservice.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.annotations.SchemaValidation;
import org.apache.cxf.feature.Features;
import org.springframework.stereotype.Controller;

import javax.jws.WebService;

/**
 * github: https://github.com/jpontdia/ws-employee-soapcxf
 * ref: https://cxf.apache.org/docs/annotations.html
 */
@Controller
@Slf4j
@WebService(targetNamespace = "http://www.gunnarro.com/employee",
        name = "EmployeeServicePortType",
        endpointInterface = "com.gunnarro.employee.EmployeeServicePortType",
        serviceName = "EmployeeService",
        wsdlLocation = "wsdl/EmployeeServices.wsdl")
@Features(features = "org.apache.cxf.ext.logging.LoggingFeature")
@SchemaValidation(type = SchemaValidation.SchemaValidationType.BOTH, schemas = "wsdl/EmployeeServices.wsdl")
public class EmployeeEndpoint implements EmployeeServicePortType {

    private final EmployeeService employeeService;

    public EmployeeEndpoint(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public EmployeesResponse getEmployeesByGender(EmployeeByGenderRequest request) {
        log.info("request: {}", request);
        EmployeesResponse employeesResponse = new EmployeesResponse();
        try {
            employeesResponse.getEmployee().addAll(employeeService.getEmployeeByGender(request.getGender().value()));
        } catch (Exception e) {
            log.error("Error while setting values for employee object", e);
        }
        return employeesResponse;
    }

    @Override
    public EmployeesResponse getEmployeesByAddress(EmployeeByAddressRequest request) {
        log.info("request: {}", request);
        EmployeesResponse employeesResponse = new EmployeesResponse();
        try {
            employeesResponse.getEmployee().addAll(employeeService.getEmployeeByAddress(request.getPostcode(), request.getStreetName()));
        } catch (Exception e) {
            log.error("Error while setting values for employee object", e);
        }
        return employeesResponse;
    }

    @Override
    public EmployeesResponse getEmployeesByName(EmployeeByNameRequest request) {
        log.info("request: {}", request);
        EmployeesResponse employeesResponse = new EmployeesResponse();
        try {
            employeesResponse.getEmployee().addAll(employeeService.getEmployeesByName(request.getFirstname(), request.getLastname()));
        } catch (Exception e) {
            log.error("Error while setting values for employee object", e);
        }
        return employeesResponse;
    }

    @Override
    public EmployeeResponse getEmployeeById(EmployeeByIdRequest request) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        try {
            employeeResponse.setEmployee(employeeService.getEmployeeById(request.getId()));
        } catch (Exception e) {
            log.error("Error while setting values for employee object", e);
        }
        return employeeResponse;
    }
}
