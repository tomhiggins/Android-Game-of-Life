package de.deepsource.agol.rules;

import de.deepsource.agol.Agol;

/**
 * @author Sebastian Ullrich
 */
public class ShuffleRule extends RuleSet {

	/**
	 * Game of Life -- Conway Rules
	 */
	public ShuffleRule() {
		int[] gameRule = new int[9];
		gameRule[0] = Agol.DEATH_RULE;
		gameRule[1] = Agol.BIRTH_RULE;
		gameRule[2] = Agol.DEATH_RULE;
		gameRule[3] = Agol.BIRTH_RULE;
		gameRule[4] = Agol.DEATH_RULE;
		gameRule[5] = Agol.BIRTH_RULE;
		gameRule[6] = Agol.DEATH_RULE;
		gameRule[7] = Agol.BIRTH_RULE;
		gameRule[8] = Agol.DEATH_RULE;

		Agol.setGameRule(gameRule);
	}

}
