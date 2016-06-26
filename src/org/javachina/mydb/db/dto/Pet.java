package org.javachina.mydb.db.dto;

import java.io.Serializable;

public class Pet implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8009973024437049240L;
	private String id;
	private String type;
	private int age;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getAge() {
		return age;
	}
	public Pet() {
		super();
	}
	public Pet(String id, String type, int age) {
		super();
		this.id = id;
		this.type = type;
		this.age = age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
