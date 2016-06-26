package org.javachina.mydb.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.javachina.mydb.db.dto.Person;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String className = "org.javachina.mydb.dto.Person";
		Class clazz = Class.forName(className);
		Object obj = clazz.newInstance();
		
		Field[] fields = clazz.getDeclaredFields();
		
		for(Field field :fields){
			String fieldName = field.getName();
			if(fieldName.equals("id")){
				field.setAccessible(true);
				field.set(obj, "1001");
				field.setAccessible(false);
			}else if(fieldName.equals("name")){
				field.setAccessible(true);
				field.set(obj, "李三");
				field.setAccessible(false);
				
			}else if(fieldName.equals("age")){
				field.setAccessible(true);
				field.set(obj, 30);
				field.setAccessible(false);
			}
		
		}
		
		Field f1 = clazz.getDeclaredField("name");
		f1.setAccessible(true);
		f1.set(obj, "王五");
		f1.setAccessible(false);
		
		Method[] methods = clazz.getDeclaredMethods();
//		for (Method method : methods) {
//			String mname = method.getName();
//			System.out.println(mname);
//			Class returnClass = method.getReturnType();
//			System.out.println(returnClass.getName());
//			System.out.println("--------------");
//			if(mname.equals("setName")){
//				Object[] params = new Object[]{"赵六"};
//				method.invoke(obj, params);
//			}
//		}
		Class[] parameterTypes = new Class[]{java.lang.String.class};
		Method method = clazz.getDeclaredMethod("setName", parameterTypes);
		Object[] ags = new Object[]{"张无忌"};
		method.invoke(obj, ags);
		
		Method m1 = clazz.getDeclaredMethod("getName");
		Object value = m1.invoke(obj);
		System.out.println(value);
//		Person p = (Person)obj;
//		System.out.println(p.getId()+":"+p.getName()+"："+p.getAge());
	}

}
