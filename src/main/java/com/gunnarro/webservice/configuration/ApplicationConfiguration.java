package com.gunnarro.webservice.configuration;

import com.gunnarro.webservice.endpoint.EmployeeEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.Bus;
import org.apache.cxf.annotations.SchemaValidation;
import org.apache.cxf.feature.validation.DefaultSchemaValidationTypeProvider;
import org.apache.cxf.feature.validation.SchemaValidationFeature;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.validation.BeanValidationInInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.xml.ws.Endpoint;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@ImportResource(locations = {"classpath:META-INF/cxf/cxf.xml"})
public class ApplicationConfiguration {

    @Autowired
    private Bus bus;

    @Bean
    public Endpoint endpoint(EmployeeEndpoint employeeEndpoint) {
        log.info("init cxf endpoint....");
        bus.setProperty("schema-validation-enabled", "true");
        bus.setProperty("set-jaxb-validation-event-handler", "com.gunnarro.webservice.configuration.CustomValidationEventHandler");
       // bus.getInFaultInterceptors().add(new CustomSoapFaultInterceptor());
       // Map<String, SchemaValidation.SchemaValidationType> map = new HashMap<>();
       // bus.getFeatures().add(new SchemaValidationFeature(new DefaultSchemaValidationTypeProvider(map)));
        EndpointImpl endpoint = new EndpointImpl(bus, employeeEndpoint);
        // map.put("", SchemaValidation.SchemaValidationType.BOTH);
        //new BeanValidationInInterceptor();
        endpoint.setWsdlLocation("wsdl/EmployeeServices.wsdl");
        endpoint.publish("/service/employee");
        return endpoint;
    }
}
