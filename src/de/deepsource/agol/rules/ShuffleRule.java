package de.deepsource.agol.rules;

/**
 * @author Sebastian Ullrich
 */
public class ShuffleRule extends RuleSet{
	
	/**
	 * Game of Life -- Conway Rules
	 */
	public ShuffleRule(){
		gameRule[0] = RuleSet.DEATH_RULE;
		gameRule[1] = RuleSet.BIRTH_RULE;
		gameRule[2] = RuleSet.DEATH_RULE;
		gameRule[3] = RuleSet.BIRTH_RULE;
		gameRule[4] = RuleSet.DEATH_RULE;
		gameRule[5] = RuleSet.BIRTH_RULE;
		gameRule[6] = RuleSet.DEATH_RULE;
		gameRule[7] = RuleSet.BIRTH_RULE;
		gameRule[8] = RuleSet.DEATH_RULE;
	}
	
}
