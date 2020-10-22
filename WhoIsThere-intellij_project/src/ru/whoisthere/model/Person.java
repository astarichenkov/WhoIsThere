package ru.whoisthere.model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import javax.imageio.ImageIO;

public class Person {
    private String name;
    private String surname;
    private String department;
    private String post;
    private byte[] ph;

    public Person(String name, String surname, String department, String post, byte[] ph) {
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

    public void setPhoto(byte[] ph) {
        this.ph = Arrays.copyOf(ph, ph.length);
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

    public String getPost() {
        return post;
    }

    public BufferedImage getPhoto() {
        return biToImage();
    }

    public BufferedImage biToImage() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new ByteArrayInputStream(this.ph));
            double imgWidth = img.getWidth();
            double imgHeight = img.getHeight();
            double imgRatio = imgHeight / imgWidth;
            img = resize(img, (int) (100 * imgRatio), 100);
        } catch (IOException e) {
            System.out.println("error in biToImage()");
        }

        return img;
    }

    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
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