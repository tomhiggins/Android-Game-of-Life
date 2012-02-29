package de.deepsource.agol.database;

public class RuleSet {
	private long id;
	private String name;
	private int[] ruleSet = new int[9];
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int[] getRuleSet() {
		return ruleSet;
	}
	
	public void setRuleSet(int[] ruleSet) {
		this.ruleSet = ruleSet;
	}
}
