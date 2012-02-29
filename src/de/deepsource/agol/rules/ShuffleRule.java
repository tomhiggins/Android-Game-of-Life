package de.deepsource.agol.rules;

import de.deepsource.agol.Agol;

/**
 * @author Sebastian Ullrich
 */
public class ShuffleRule extends RuleSetUtil {

	/**
	 * Game of Life -- Conway Rules
	 */
	public ShuffleRule() {
		gameRule[0] = Agol.DEATH_RULE;
		gameRule[1] = Agol.BIRTH_RULE;
		gameRule[2] = Agol.DEATH_RULE;
		gameRule[3] = Agol.BIRTH_RULE;
		gameRule[4] = Agol.DEATH_RULE;
		gameRule[5] = Agol.BIRTH_RULE;
		gameRule[6] = Agol.DEATH_RULE;
		gameRule[7] = Agol.BIRTH_RULE;
		gameRule[8] = Agol.DEATH_RULE;
	}

}
