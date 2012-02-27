package de.deepsource.agol;

import android.app.Application;

/**
 * Agol Application implementation is used for global variables and
 * constants.
 */
public class Agol extends Application {
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
}
