package com.gunnarro.webservice.endpoint;

import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;

import static io.restassured.RestAssured.given;
import static io.restassured.config.XmlConfig.xmlConfig;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.containsString;

/**
 * Integration tests
 */
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeEndpointTest {
    @LocalServerPort
    private int port;

    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    void wsdl() {
        given()
                .when()
                .get("http://localhost:" + port + "/service/employee?wsdl")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("wsdl:definitions xmlns:xsd="));
    }

    @Test
    void listOfServices() {
        given()
                .when()
                .get("http://localhost:" + port + "/")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("Available SOAP services:"));
    }

    @Test
    void getEmployeeById() {
        Resource res = resourceLoader.getResource("classpath:getEmployeeById.xml");
        given()
                .contentType("text/xml;charset=UTF-8")
                .body(asString(res))
                .when()
                .post("http://localhost:" + port + "/service/employee")
                .then()
                .log().ifError()
                .assertThat()
                .statusCode(200)
                .body(containsString("EmployeeResponse"));
    }

    @Test
    void getEmployeeByName_response_validation_fault() {
        Resource res = resourceLoader.getResource("classpath:getEmployeeByName.xml");
        given()
                .contentType("text/xml;charset=UTF-8")
                .body(asString(res))
                .when()
                .post("http://localhost:" + port + "/service/employee")
                .then()
                .assertThat()
                .statusCode(500)
                .body(containsString("Marshalling Error: cvc-minLength-valid: Value 'Jo' with length = '2' is not facet-valid with respect to minLength '3' for type 'PersonNameType'"));
    }

    @Test
    void getEmployeeByName_request_validation_fault() {
        Resource res = resourceLoader.getResource("classpath:getEmployeeByName-validation-fault.xml");
        given()
                .contentType("text/xml;charset=UTF-8")
                .body(asString(res))
                .when()
                .post("http://localhost:" + port + "/service/employee")
                .then()
                .assertThat()
                .log().ifError()
                .statusCode(500)
                .body(containsString("cvc-minLength-valid"));
    }

    @Test
    void getEmployeeByName_request_missing_attribute() {
        Resource res = resourceLoader.getResource("classpath:getEmployeeByName-missing-attribute.xml");
        given()
                .contentType("text/xml;charset=UTF-8")
                .body(asString(res))
                .when()
                .post("http://localhost:" + port + "/service/employee")
                .then()
                .assertThat()
                .body(containsString("Unmarshalling Error: cvc-complex-type.4: Attribute 'firstname' must appear on element 'emp:EmployeeByNameRequest'."));
    }

    @Test
    void getEmployeeByAddress() {
        Resource res = resourceLoader.getResource("classpath:getEmployeeByAddress.xml");
        given()
                .config(RestAssured.config()
                        .xmlConfig(xmlConfig()
                                .declareNamespace("xmlns:soap", "http://schemas.xmlsoap.org/soap/envelope/")
                                .declareNamespace("xmlns:ns2", "http://www.gunnarro.com/employee")))
                .contentType("text/xml;charset=UTF-8")
                .body(asString(res))
                .when()
                .post("http://localhost:" + port + "/service/employee")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"));
        // .body(hasXPath("Envelope"))
        // .body("soap:Envelope.soap:Body.ns2:EmployeesResponse.ns2:employee[@lastname]", equalTo("Hansen"));
    }

    @Test
    void getEmployeeByGender() {
        Resource res = resourceLoader.getResource("classpath:getEmployeeByGender.xml");
        given()
                .contentType("text/xml;charset=UTF-8")
                .body(asString(res))
                .when()
                .post("http://localhost:" + port + "/service/employee")
                .then()
                .assertThat()
                .body(containsString("EmployeesResponse"));
    }

    public static String asString(Resource resource) {
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            String s = FileCopyUtils.copyToString(reader);
            System.out.println(s);
            return s;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
