package edu.mcc.codeschool.graphql.models;

import java.util.UUID;

public class Author {
    private UUID id;
    private String firstName;
    private String lastName;
    private Integer numOfBooksPublished;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Integer getNumOfBooksPublished() {
        return numOfBooksPublished;
    }

    public void setNumOfBooksPublished(Integer numOfBooksPublished) {
        this.numOfBooksPublished = numOfBooksPublished;
    }
}
