package com.gunnarro.webservice.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Person {

    private LocalDateTime created;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Person() {
    }


    public static final class PersonBuilder {
        private LocalDateTime created;
        private String firstName;
        private String lastName;
        private LocalDate birthDate;

        private PersonBuilder() {
        }

        public static PersonBuilder aPerson() {
            return new PersonBuilder();
        }

        public PersonBuilder withCreated(LocalDateTime created) {
            this.created = created;
            return this;
        }

        public PersonBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public PersonBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PersonBuilder withBirthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Person build() {
            Person person = new Person();
            person.setCreated(created);
            person.setFirstName(firstName);
            person.setLastName(lastName);
            person.setBirthDate(birthDate);
            return person;
        }
    }
}
