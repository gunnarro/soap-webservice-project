package com.gunnarro.webservice.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.gunnarro.webservice.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonTest {

    @Test
    void toJson() throws JsonProcessingException {
        Person person = Person.PersonBuilder.aPerson()
                .withCreated(LocalDateTime.of(2022, 4, 8, 12, 23, 56))
                .withBirthDate(LocalDate.of(2022, 4, 8))
                .withFirstName("gunnar")
                .withLastName("rønneberg")
                .build();
        assertEquals("{\"created\":\"2022-04-08 12:23:56\",\"firstName\":\"gunnar\",\"lastName\":\"rønneberg\",\"birthDate\":\"2022-04-08\"}", jsonMapper().writeValueAsString(person));
    }

    public ObjectMapper jsonMapper() {
        return new Jackson2ObjectMapperBuilder()
                .serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                .modules(new JavaTimeModule())
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .build();
    }
}
