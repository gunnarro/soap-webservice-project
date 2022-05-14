package com.gunnarro.webservice.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ResourceLoader;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeEndpointTest {
    @LocalServerPort
    private int port;

    @Autowired
    private ResourceLoader resourceLoader;

    /*
    @Test
    void wsdl(){
        given()
            .when()
                .get("http://localhost:" + port +"/soap/service/employee?wsdl")
                .then()
                .assertThat()
                .body(containsString("wsdl:definitions xmlns:xsd="));
    }

    @Test
    void listOfServices(){
        given()
                .when()
                .get("http://localhost:" + port +"/soap")
                .then()
                .assertThat()
                .body(containsString("Available SOAP services:"));
    }

    @Test
    void getEmployeeById(){
        Resource res = resourceLoader.getResource("classpath:getEmployeeById.xml");
        given()
            .contentType("text/xml;charset=UTF-8")
            .body(asString(res))
        .when()
            .post("http://localhost:" + port +"/soap/service/employee")
        .then()
            .assertThat()
            .body(containsString("EmployeeResponse"));
    }

    @Test
    void getEmployeeByName(){
        Resource res = resourceLoader.getResource("classpath:getEmployeeByName.xml");
        given()
                .contentType("text/xml;charset=UTF-8")
                .body(asString(res))
                .when()
                .post("http://localhost:" + port +"/soap/service/employee")
                .then()
                .assertThat()
                .body(containsString("EmployeesResponse"));
    }

    @Test
    void getEmployeeByName_validation_fault(){
        Resource res = resourceLoader.getResource("classpath:getEmployeeByName-validation-fault.xml");
        given()
                .contentType("text/xml;charset=UTF-8")
                .body(asString(res))
                .when()
                .post("http://localhost:" + port +"/soap/service/employee")
                .then()
                .assertThat()
                .body(containsString("cvc-minLength-valid"));
    }

    @Test
    void getEmployeeByName_missing_attribute(){
        Resource res = resourceLoader.getResource("classpath:getEmployeeByName-missing-attribute.xml");
        given()
                .contentType("text/xml;charset=UTF-8")
                .body(asString(res))
                .when()
                .post("http://localhost:" + port +"/soap/service/employee")
                .then()
                .assertThat()
                .body(containsString("Marshalling Error: cvc-complex-type.4: Attribute 'firstname' must appear on element"));
    }

    @Test
    void getEmployeeByAddress(){
        Resource res = resourceLoader.getResource("classpath:getEmployeeByAddress.xml");
        given()
                .contentType("text/xml;charset=UTF-8")
                .body(asString(res))
                .when()
                .post("http://localhost:" + port +"/soap/service/employee")
                .then()
                .assertThat()
                .body(containsString("EmployeesResponse"));
    }

    @Test
    void getEmployeeByGender(){
        Resource res = resourceLoader.getResource("classpath:getEmployeeByGender.xml");
        given()
                .contentType("text/xml;charset=UTF-8")
                .body(asString(res))
                .when()
                .post("http://localhost:" + port +"/soap/service/employee")
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
*/
}
