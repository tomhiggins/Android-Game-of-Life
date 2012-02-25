package de.deepsource.agol.rules;

public class AntiConway extends RuleSet{
	
	/**
	 * Game of Life -- Anti Conway Rules
	 */
	public AntiConway(){
		gameRule[0] = RuleSet.BIRTH_RULE;
		gameRule[1] = RuleSet.BIRTH_RULE;
		gameRule[2] = RuleSet.UNDEFINED;
		gameRule[3] = RuleSet.DEATH_RULE;
		gameRule[4] = RuleSet.BIRTH_RULE;
		gameRule[5] = RuleSet.BIRTH_RULE;
		gameRule[6] = RuleSet.BIRTH_RULE;
		gameRule[7] = RuleSet.BIRTH_RULE;
		gameRule[8] = RuleSet.BIRTH_RULE;
	}
	
}
