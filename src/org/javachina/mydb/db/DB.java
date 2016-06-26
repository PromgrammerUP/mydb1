package org.javachina.mydb.db;

import java.io.Serializable;
import java.util.ArrayList;

import org.javachina.mydb.db.dto.Person;
import org.javachina.mydb.db.dto.Pet;

public class DB implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1762197198369833363L;
	private DB(){}
	private static DB instance = new DB();
	public static DB getInstance(){
		return instance;
	}
	public ArrayList<Person> getPersonTable() {
		return personTable;
	}

	public ArrayList<Pet> getPetTable() {
		return petTable;
	}
	private ArrayList<Person> personTable = new ArrayList<Person>();
	
	private ArrayList<Pet> petTable = new ArrayList<Pet>();
}
