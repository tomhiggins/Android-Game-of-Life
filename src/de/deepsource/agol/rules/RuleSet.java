package de.deepsource.agol.rules;

import de.deepsource.agol.Agol;

/**
 * @author Sebastian Ullrich
 */
public abstract class RuleSet {
	/**
	 * Map and cell size.
	 */
	public static final int CELL_SIZE = 10;
	public static final int HEIGHT = Agol.getViewportHeight() / CELL_SIZE;
	public static final int WIDTH = Agol.getViewportWidth() / CELL_SIZE;

	public RuleSet() {
		int[] rule = new int[9];
		/**
		 * Nihilism Rule...
		 */
		rule[0] = Agol.UNDEFINED;
		rule[1] = Agol.UNDEFINED;
		rule[2] = Agol.UNDEFINED;
		rule[3] = Agol.UNDEFINED;
		rule[4] = Agol.UNDEFINED;
		rule[5] = Agol.UNDEFINED;
		rule[6] = Agol.UNDEFINED;
		rule[7] = Agol.UNDEFINED;
		rule[8] = Agol.UNDEFINED;

		Agol.setGameRule(rule);
	}

	/**
	 * This will apply the game rule considering the neigbourhood.
	 * 
	 * @param region
	 *            neigbourhood
	 * @return state of cell in next cycle
	 */
	public boolean apply(boolean[][] region) {
		int[] gameRule = Agol.getGameRule();

		/**
		 * initial status of cell.
		 */
		boolean status = region[1][1];

		/**
		 * counting neighbours.
		 */
		int neighbours = 0;
		if (status)
			neighbours--;
		for (int x = 0; x < 3; x++)
			for (int y = 0; y < 3; y++)
				if (region[x][y])
					neighbours++;

		/**
		 * apply rules
		 */
		int destiny = gameRule[neighbours];

		if (destiny == Agol.DEATH_RULE)
			return false;

		if (destiny == Agol.BIRTH_RULE)
			return true;

		/**
		 * destiny is undefiened.
		 */
		return status;
	}
}
