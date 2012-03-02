//package de.deepsource.agol.rules;
//
//import de.deepsource.agol.Agol;
//
///**
// * @author Sebastian Ullrich
// * TODO: do we still need this class?
// */
//public abstract class RuleSetUtil {
//	
//	
////	protected static int[] gameRule = Agol.getRuleSet();
//
//	public RuleSetUtil() {
//		/**
//		 * Nihilism Rule...
//		 */
////		gameRule[0] = Agol.UNDEFINED;
////		gameRule[1] = Agol.UNDEFINED;
////		gameRule[2] = Agol.UNDEFINED;
////		gameRule[3] = Agol.UNDEFINED;
////		gameRule[4] = Agol.UNDEFINED;
////		gameRule[5] = Agol.UNDEFINED;
////		gameRule[6] = Agol.UNDEFINED;
////		gameRule[7] = Agol.UNDEFINED;
////		gameRule[8] = Agol.UNDEFINED;
//	}
//	
//	public RuleSetUtil(int[] rule) {
//		
//	}
//
//	/**
//	 * This will apply the game rule considering the neigbourhood.
//	 * 
//	 * @param region
//	 *            neigbourhood
//	 * @return state of cell in next cycle
//	 */
//	public static boolean apply(boolean[][] region) {
//		int[] gameRule = Agol.getRuleSet();
//		
//		/**
//		 * initial status of cell.
//		 */
//		boolean status = region[1][1];
//
//		/**
//		 * counting neighbours.
//		 */
//		int neighbours = 0;
//		if (status)
//			neighbours--;
//		for (int x = 0; x < 3; x++)
//			for (int y = 0; y < 3; y++)
//				if (region[x][y])
//					neighbours++;
//
//		/**
//		 * apply rules
//		 */
//		int destiny = gameRule[neighbours];
//
//		if (destiny == Agol.DEATH_RULE)
//			return false;
//
//		if (destiny == Agol.BIRTH_RULE)
//			return true;
//
//		/**
//		 * destiny is undefined.
//		 */
//		return status;
//	}
//}
