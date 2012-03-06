package de.deepsource.agol.database;

import de.deepsource.agol.rules.RuleEditorActivity;

/**
 * Represents our model, that contains all data being saved in the database
 * and shown in {@link RuleEditorActivity}.
 * 
 * @author Jan Pretzel (jan.pretzel@deepsource.de)
 */
public class RuleSet {
	
	/**
	 * The id the rule has in the database.
	 */
	private long id;
	
	/**
	 * The name of the rule.
	 */
	private String name;
	
	/**
	 * The rule itself presented as integer array.
	 */
	private int[] ruleSet = new int[9];
	
	/**
	 * Getter for {@link RuleSet#id}.
	 * 
	 * @return {@link RuleSet#id}.
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Setter for {@link RuleSet#id}.
	 * 
	 * @param id The new {@link RuleSet#id}.
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * Getter for {@link RuleSet#name}.
	 * 
	 * @return {@link RuleSet#name}.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter for {@link RuleSet#name}.
	 * 
	 * @param id The new {@link RuleSet#name}.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter for {@link RuleSet#ruleSet}.
	 * 
	 * @return {@link RuleSet#ruleSet}.
	 */
	public int[] getRuleSet() {
		return ruleSet;
	}
	
	/**
	 * Setter for {@link RuleSet#ruleSet}.
	 * 
	 * @param id The new {@link RuleSet#ruleSet}.
	 */
	public void setRuleSet(int[] ruleSet) {
		this.ruleSet = ruleSet;
	}
}
