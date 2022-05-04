package com.gunnarro.webservice.service;

import com.gunnarro.employee.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.annotations.SchemaValidation;
import org.apache.cxf.feature.Features;
import org.springframework.stereotype.Service;

/**
 * github: https://github.com/jpontdia/ws-employee-soapcxf
 * ref: https://cxf.apache.org/docs/annotations.html
 */
@Service
@Slf4j
@Features(features = "org.apache.cxf.ext.logging.LoggingFeature")
@SchemaValidation(type = SchemaValidation.SchemaValidationType.BOTH, schemas = "wsdl/EmployeeServices.wsdl")
//(classes = SchemaValidationErrorInterceptor.class)
public class EmployeeEndpoint implements EmployeeServicePortType {

    BackendService backendService;

    public EmployeeEndpoint(BackendService backendService) {
        this.backendService = backendService;
    }

    @Override
    public EmployeesResponse getEmployeesByName(EmployeeByNameRequest request) {
        log.info("request: {}", request);
        EmployeesResponse employeesResponse = new EmployeesResponse();
        try {
            employeesResponse.getEmployee().addAll(backendService.getEmployeesByName(request.getFirstname(), request.getLastname()));
        } catch (Exception e) {
            log.error("Error while setting values for employee object", e);
        }
        return employeesResponse;
    }

    @Override
    public EmployeeResponse getEmployeeById(EmployeeByIdRequest parameters) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        try {
            employeeResponse.setEmployee(backendService.getEmployeeById(parameters.getId()));
        } catch (Exception e) {
            log.error("Error while setting values for employee object", e);
        }
        return employeeResponse;
    }
}
