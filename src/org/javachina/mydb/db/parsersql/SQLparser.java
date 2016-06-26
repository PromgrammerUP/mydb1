package org.javachina.mydb.db.parsersql;

import java.util.HashMap;
import java.util.Map;

import org.javachina.mydb.db.parsersql.model.InsertModel;

public class SQLparser {
	public InsertModel insertParser(String sql){
		InsertModel model = new InsertModel();
		// insert into person(id,name,age) values(1001,爱因斯坦，62);
		int tableNameLeft = sql.indexOf("into")+"into".length();
		int tableNameRight = sql.indexOf("(");
		String tableName = sql.substring(tableNameLeft, tableNameRight).trim();
		//System.out.println(tableName);
		model.setTableName(tableName);
		int paramLeft = sql.indexOf("(")+1;
		int paramRight = sql.indexOf(")");
		String queryParam = sql.substring(paramLeft,paramRight).trim();
		//System.out.println(queryParam);
		String[] params = queryParam.split(",");
//		for (String param : params) {
//			String paramWithNoSpace = param.trim();
//			//System.out.println(paramWithNoSpace);
//		}
		
		int valueLeft = sql.lastIndexOf("(")+1;
		int valueRight = sql.lastIndexOf(")");
		String valueString = sql.substring(valueLeft, valueRight).trim();
		String[] values = valueString.split(",");
//		for (String value : values) {
//			String valueWithNoSpace = value.trim();
//			//System.out.println(valueWithNoSpace);
//		}
		Map<String, String> paramMap = new HashMap<String ,String>();
		for(int i= 0;i<values.length;i++){
			String param = params[i].trim();
			String value = values[i].trim();
			paramMap.put(param, value);
		}
		model.setParams(paramMap);
		return model;
	}
    public void updateParser(String sql){
		
	}
    public void deleteParser(String sql){
	
    }
    public void selectParser(String sql){
	
    }
    public static void main(String[] args) {
		InsertModel model = new SQLparser().insertParser("insert into person(id,name,age) values(1001,爱因斯坦,62)");
	    System.out.println(model.getTableName());
	    Map map = model.getParams();
	    System.out.println(map);
    }
}
