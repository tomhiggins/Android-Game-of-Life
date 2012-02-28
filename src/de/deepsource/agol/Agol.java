package de.deepsource.agol;

import de.deepsource.agol.database.RuleSet;
import android.app.Application;

/**
 * Agol Application implementation is used for global variables and
 * constants.
 */
public class Agol extends Application {
	
	/**
	 * Identifier for the fact, that the rule is undefined.
	 */
	public static final int UNDEFINED = 0;
	
	/**
	 * Identifier for the birth rule.
	 */
	public static final int BIRTH_RULE = 1;
	
	/**
	 * Identifier for the death rule.
	 */
	public static final int DEATH_RULE = 2;
	
	private static RuleSet ruleSet;
	
	/**
	 * The width of the device.
	 */
	private static int viewportWidth;

	/**
	 * The height of the device.
	 */
	private static int viewportHeight;

	/**
	 * Getter for {@link Agol#viewportWidth}.
	 *
	 * @return {@link Agol#viewportWidth}.
	 */
	public static int getViewportWidth() {
		return viewportWidth;
	}

	/**
	 * Setter for {@link Agol#viewportWidth}.
	 *
	 * @param newViewportWidth The new width for the device.
	 */
	public static void setViewportWidth(final int newViewportWidth) {
		viewportWidth = newViewportWidth;
	}

	/**
	 * Getter for {@link Agol#viewportHeight}.
	 *
	 * @return {@link Agol#viewportHeight}.
	 */
	public static int getViewportHeight() {
		return viewportHeight;
	}

	/**
	 * Setter for {@link Agol#viewportHeight}.
	 *
	 * @param newViewportHeight The new height for the device.
	 */
	public static void setViewportHeight(final int newViewportHeight) {
		viewportHeight = newViewportHeight;
	}

	public static RuleSet getRuleSet() {
		return ruleSet;
	}

	public static void setRuleSet(RuleSet ruleSet) {
		Agol.ruleSet = ruleSet;
	}
}
