package ru.whoisthere.model;

import ru.whoisthere.utils.Logging;

import java.awt.image.BufferedImage;
import java.util.Objects;

public class Person {
    private static Logging logs = new Logging();
    private String name;
    private String surname;
    private String department;
    private String post;
    private BufferedImage ph;
    private boolean isPresent;


    public Person(String name, String surname,
                  String department, String post, BufferedImage ph) {
        this.name = name;
        this.surname = surname;
        this.department = department;
        this.post = post;
        this.ph = ph;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setPhoto(BufferedImage ph) {
        this.ph = ph;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getDepartment() {
        return this.department;
    }

    public BufferedImage getPhoto() {
        return ph;
    }

    public String getPost() {
        return post;
    }

    @Override
    public String toString() {
        return "Person{"
                + "name='" + name + '\''
                + ", surname='" + surname + '\''
                + ", department='" + department + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return name.equals(person.name)
                && surname.equals(person.surname)
                && department.equals(person.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, department);
    }
}