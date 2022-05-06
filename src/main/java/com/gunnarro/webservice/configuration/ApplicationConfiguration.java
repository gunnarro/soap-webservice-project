package com.gunnarro.webservice.configuration;

import com.gunnarro.webservice.endpoint.EmployeeEndpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.validation.BeanValidationInInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class ApplicationConfiguration {

    @Autowired
    private Bus bus;

    @Bean
    public Endpoint endpoint(EmployeeEndpoint employeeEndpoint) {
        EndpointImpl endpoint = new EndpointImpl(bus, employeeEndpoint);
        new BeanValidationInInterceptor();
        endpoint.publish("/service/employee");
        return endpoint;
    }
}
