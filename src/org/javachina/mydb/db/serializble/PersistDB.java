package org.javachina.mydb.db.serializble;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import org.javachina.mydb.db.DB;
import org.javachina.mydb.db.dto.Person;
import org.javachina.mydb.db.dto.Pet;

public class PersistDB {
	private String path = "F:/exercisemydb/db.dat";
	
	public void writeDB(DB db) throws IOException{
		//DB db = DB.getInstance();
		OutputStream os = new FileOutputStream(path);
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(db);
		oos.flush();
		oos.close();
		os.close();
	}
	
	public DB readDB() throws  ClassNotFoundException, IOException{
		InputStream is = new FileInputStream(path);
		ObjectInputStream ois = new ObjectInputStream(is);
		Object obj = ois.readObject();
		 return (DB)obj;
	}
	
	public static void main(String[] args) {
		ArrayList<Person> list = DB.getInstance().getPersonTable();
		list.add(new Person("1101", "张三", 30));
		ArrayList<Pet> list2 = DB.getInstance().getPetTable();
		list2.add(new Pet("111", "白色", 2));
		
		try {
			new PersistDB().writeDB(DB.getInstance());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			DB d = new PersistDB().readDB();
			System.out.println(d);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
}
