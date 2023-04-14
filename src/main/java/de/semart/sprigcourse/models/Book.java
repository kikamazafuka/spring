package de.semart.sprigcourse.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {
    private int id;
    private int personId;

    @NotEmpty(message = "Name should not be empty")
    @Size(min=2, max=30, message = "Name should be between 2 and 30 characters")
    private String name;

    @NotEmpty(message = "Author name should not be empty")
    @Size(min=2, max=30, message = "Author name should be between 2 and 30 characters")
    private String author;

    @Min(value = 1800, message = "Year of the book should be greater 1800")
    private int year;



    public Book(){}

    public Book(int personId, String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.personId=personId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


}
