package com.gdssoft.cqjt.pojo.enums;

public enum ScoreItem {

	Send("send", 0.5), 
	Published("published", 1);
	
	//构造枚举值  
    private ScoreItem(String name, double score){  
    	this.itemName = name;
    	this.itemScore = score;
    }
    
	public String getItemName() {
		return itemName;
	}
	public double getItemScore() {
		return itemScore;
	}
	
	private String itemName;
    private double itemScore;
}