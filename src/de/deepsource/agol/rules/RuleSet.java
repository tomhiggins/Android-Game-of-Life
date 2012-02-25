package de.deepsource.agol.rules;

/**
 * @author Sebastian Ullrich
 */
public abstract class RuleSet {
	/**
	 * Map and cell size.
	 */
	public static final int HEIGHT = 80;
	public static final int WIDTH = 48;
	public static final int CELL_SIZE = 10;
	
	/**
	 * cell stages.
	 */
	public static final float UNDEFINED = 0;
	public static final float BIRTH_RULE = 1;
	public static final float DEATH_RULE = 2;
	
	protected float[] gameRule = new float[9];
	
	public RuleSet(){
		/**
		 * Nihilism Rule...
		 */
		gameRule[0] = RuleSet.UNDEFINED;
		gameRule[1] = RuleSet.UNDEFINED;
		gameRule[2] = RuleSet.UNDEFINED;
		gameRule[3] = RuleSet.UNDEFINED;
		gameRule[4] = RuleSet.UNDEFINED;
		gameRule[5] = RuleSet.UNDEFINED;
		gameRule[6] = RuleSet.UNDEFINED;
		gameRule[7] = RuleSet.UNDEFINED;
		gameRule[8] = RuleSet.UNDEFINED;
	}
	
	/**
	 * This will apply the game rule considering the neigbourhood.
	 * @param region neigbourhood
	 * @return state of cell in next cycle 
	 */
	public boolean apply(boolean[][] region){
			/**
			 * initial status of cell.
			 */
			boolean status = region[1][1];
			
			/**
			 * counting neighbours.
			 */
			int neighbours = 0;
			if(status)
				neighbours--;
			for(int x=0; x<3; x++)
				for(int y=0; y<3; y++)
					if(region[x][y])
						neighbours++;
			
			/**
			 * apply rules
			 */
			float destiny = gameRule[neighbours];

			if(destiny == RuleSet.DEATH_RULE)
				return false;
			
			if(destiny == RuleSet.BIRTH_RULE)
				return true;
			
			/**
			 * destiny is undefiened.
			 */
			return status;
		}
	}
