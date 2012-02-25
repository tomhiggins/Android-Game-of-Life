package de.deepsource.agol.rules;

/**
 * @author Sebastian Ullrich
 */
public class Conway extends RuleSet{
	
	/**
	 * Game of Life -- Conway Rules
	 */
	public Conway(){
		gameRule[0] = RuleSet.DEATH_RULE;
		gameRule[1] = RuleSet.DEATH_RULE;
		gameRule[2] = RuleSet.UNDEFINED;
		gameRule[3] = RuleSet.BIRTH_RULE;
		gameRule[4] = RuleSet.DEATH_RULE;
		gameRule[5] = RuleSet.DEATH_RULE;
		gameRule[6] = RuleSet.DEATH_RULE;
		gameRule[7] = RuleSet.DEATH_RULE;
		gameRule[8] = RuleSet.DEATH_RULE;
	}
	
}
