package org.javachina.mydb.db.parsersql.model;

import java.util.HashMap;
import java.util.Map;

public class InsertModel {
	private String tableName;
	private Map<String,String> params = new HashMap<String,String>();
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	
}
