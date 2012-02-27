package de.deepsource.agol.rules;

import de.deepsource.agol.Agol;

/**
 * @author Sebastian Ullrich
 */
public class Conway extends RuleSet {

	/**
	 * Game of Life -- Conway Rules
	 */
	public Conway() {
		int[] gameRule = new int[9];
		gameRule[0] = Agol.DEATH_RULE;
		gameRule[1] = Agol.DEATH_RULE;
		gameRule[2] = Agol.UNDEFINED;
		gameRule[3] = Agol.BIRTH_RULE;
		gameRule[4] = Agol.DEATH_RULE;
		gameRule[5] = Agol.DEATH_RULE;
		gameRule[6] = Agol.DEATH_RULE;
		gameRule[7] = Agol.DEATH_RULE;
		gameRule[8] = Agol.DEATH_RULE;

		Agol.setGameRule(gameRule);
	}

}
