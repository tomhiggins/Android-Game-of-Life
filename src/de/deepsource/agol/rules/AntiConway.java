package de.deepsource.agol.rules;

import de.deepsource.agol.Agol;

public class AntiConway extends RuleSet {

	/**
	 * Game of Life -- Anti Conway Rules
	 */
	public AntiConway() {
		int[] gameRule = new int[9];
		gameRule[0] = Agol.BIRTH_RULE;
		gameRule[1] = Agol.BIRTH_RULE;
		gameRule[2] = Agol.UNDEFINED;
		gameRule[3] = Agol.DEATH_RULE;
		gameRule[4] = Agol.BIRTH_RULE;
		gameRule[5] = Agol.BIRTH_RULE;
		gameRule[6] = Agol.BIRTH_RULE;
		gameRule[7] = Agol.BIRTH_RULE;
		gameRule[8] = Agol.BIRTH_RULE;

		Agol.setGameRule(gameRule);
	}

}
