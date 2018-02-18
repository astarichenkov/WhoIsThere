package ru.whoisthere;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Person {
	String name;
	String surname;
	String department;
	byte[] photo;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	
	public BufferedImage getPhoto() {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new ByteArrayInputStream(this.photo));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return img;
	}
	
	
}
