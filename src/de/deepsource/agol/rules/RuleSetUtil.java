package de.deepsource.agol.rules;

import de.deepsource.agol.Agol;

/**
 * @author Sebastian Ullrich
 */
public abstract class RuleSetUtil {
	/**
	 * Map and cell size.
	 */
	public static final int CELL_SIZE = 10;
	public static final int HEIGHT = Agol.getViewportHeight() / CELL_SIZE;
	public static final int WIDTH = Agol.getViewportWidth() / CELL_SIZE;
	
	protected int[] gameRule = new int[9];

	public RuleSetUtil() {
		/**
		 * Nihilism Rule...
		 */
		gameRule[0] = Agol.UNDEFINED;
		gameRule[1] = Agol.UNDEFINED;
		gameRule[2] = Agol.UNDEFINED;
		gameRule[3] = Agol.UNDEFINED;
		gameRule[4] = Agol.UNDEFINED;
		gameRule[5] = Agol.UNDEFINED;
		gameRule[6] = Agol.UNDEFINED;
		gameRule[7] = Agol.UNDEFINED;
		gameRule[8] = Agol.UNDEFINED;
	}
	
	public RuleSetUtil(int[] rule) {
		
	}

	/**
	 * This will apply the game rule considering the neigbourhood.
	 * 
	 * @param region
	 *            neigbourhood
	 * @return state of cell in next cycle
	 */
	public boolean apply(boolean[][] region) {
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
