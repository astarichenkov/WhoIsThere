package ru.whoisthere.model;

import ru.whoisthere.utils.Loging;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import javax.imageio.ImageIO;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class Person {
    private static Loging logs = new Loging();
    private String name;
    private String surname;

    private String department;

    private String post;
    private BufferedImage ph;

    public Person(String name, String surname,
                  String department, String post, BufferedImage ph) {
        this.name = name;
        this.surname = surname;
        this.department = department;
        this.post = post;
        this.ph = ph;
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