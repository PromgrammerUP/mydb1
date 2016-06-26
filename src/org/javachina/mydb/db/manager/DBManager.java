package org.javachina.mydb.db.manager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.javachina.mydb.db.DB;
import org.javachina.mydb.db.dto.Person;
import org.javachina.mydb.db.parsersql.SQLparser;
import org.javachina.mydb.db.parsersql.model.InsertModel;
import org.javachina.mydb.db.serializble.PersistDB;

public class DBManager {
	public void execute(String sql) throws ClassNotFoundException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchFieldException, SecurityException{
		sql = sql.trim();
		if(sql.indexOf("insert")==0){
			InsertModel model = new SQLparser().insertParser(sql);
			this.insert(model);
		}else if(sql.indexOf("update")==0){
			
		}else if(sql.indexOf("delete")==0){
			
		}
		
	}
	
	public void insert(InsertModel model) throws ClassNotFoundException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchFieldException, SecurityException{
		String tableName = model.getTableName();
		Map<String, String> params = model.getParams();
		
		PersistDB dbBuilder = new PersistDB();
		DB db = null;
		try {
			db = dbBuilder.readDB();
		} catch (FileNotFoundException e) {
			db = DB.getInstance();
		}
		
		Class clazz = Class.forName("org.javachina.mydb.db.DB");
		
		Method[] methods = clazz.getDeclaredMethods();
		
		for (Method method : methods) {
			String methodName = method.getName().toLowerCase();
			
			String temp = "get"+tableName+"table";
			if(temp.equals(methodName)){
//				System.out.println(temp+":"+methodName);
				ArrayList table = (ArrayList)method.invoke(db);
				
				String dto = "org.javachina.mydb.db.dto."+tableName.substring(0, 1).toUpperCase()+tableName.substring(1,tableName.length());
				
				Class clazzInput = Class.forName(dto);
				Object objectInput = clazzInput.newInstance();
				
				Set<String> keys = params.keySet();
				for (String key : keys) {
					Field field = clazzInput.getDeclaredField(key);
					String value = params.get(key);
					String type = field.getType().getName();
					field.setAccessible(true);
					if(type.equals("int")){
						field.set(objectInput, Integer.parseInt(value));
					}else if(type.equals("double")){
						field.set(objectInput, Double.parseDouble(value));
					}else{
						field.set(objectInput,value);
					}
					field.setAccessible(false);
				}
				table.add(objectInput);
				
				
//				table.add(o);
			}
			
		}
		dbBuilder.writeDB(db);
	}
	public static void main(String[] args) throws Exception {
	for(int i=0;i<100;i++){
		
		new DBManager().execute("insert into person(id,name,age) values(1001,爱因斯坦,62)");
	}	
		PersistDB pdb = new PersistDB();
	DB db = pdb.readDB();
	//db.getPersonTable().remove(1);
	ArrayList<Person> list = db.getPersonTable();
	
	//pdb.writeDB(db);
	for (Person person : list) {
		System.out.println(person.getId()+":"+person.getName()+":"+person.getAge());
	}
	}
}
