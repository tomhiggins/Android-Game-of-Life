package de.deepsource.agol;

import de.deepsource.agol.rules.RuleSetUtil;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author Sebastian Ullrich
 * @mood self-doubting
 * 
 *       This is the super unified jack of all trades class. No more confusing
 *       Model-View-Controller madness.
 */
public final class AndroidGameOfLifeView extends View {

	private Paint paint = new Paint();
	private Paint white = new Paint();
	private boolean[][] map;

	/**
	 * Semaphore, you've probably heard of them.
	 */
	private static boolean LOCKED = false;

	/**
	 * Constructor.
	 * 
	 * @param context
	 *            Context
	 */
	public AndroidGameOfLifeView(Context context) {
		super(context);
		init();
	}
	
	public AndroidGameOfLifeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	public AndroidGameOfLifeView(Context context, AttributeSet attrs, int defSytle) {
		super(context, attrs, defSytle);
		init();
	}
	
	private void init() {
		paint.setColor(Color.GREEN);
		white.setColor(Color.BLACK);
		initMap();
		
		// set Conway as initial ruleset
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
		Agol.setRuleSet(gameRule);
	}

	/**
	 * This will draw our Game of Life.
	 */
	public void onDraw(Canvas canvas) {
		canvas.drawRect((float) 0, (float) 0, (float) getWidth(),
				(float) getHeight(), white);

		for (int h = 0; h < RuleSetUtil.HEIGHT; h++)
			for (int w = 0; w < RuleSetUtil.WIDTH; w++)
				if (map[h][w])
					canvas.drawRect(w * RuleSetUtil.CELL_SIZE, h
							* RuleSetUtil.CELL_SIZE, (w * RuleSetUtil.CELL_SIZE)
							+ RuleSetUtil.CELL_SIZE, (h * RuleSetUtil.CELL_SIZE)
							+ RuleSetUtil.CELL_SIZE, paint);
	}

	/**
	 * Method to draw a boolean array.
	 * 
	 * @param m
	 *            array of boolean
	 */
	private void drawMap(boolean[][] m) {
		this.map = m;
		postInvalidate();
	}

	/**
	 * This method will initiate our first map.
	 */
	public void initMap() {
		map = new boolean[RuleSetUtil.HEIGHT][RuleSetUtil.WIDTH];
		
		int xOffset = RuleSetUtil.HEIGHT / 2 - 10;
		int yOffset = RuleSetUtil.WIDTH / 2 - 7;
		
		/**
		 * Printing Logo screen (16 * 14 cells)
		 */
		map[xOffset + 0][yOffset + 0] = true;
		map[xOffset + 0][yOffset + 1] = true;
		map[xOffset + 0][yOffset + 2] = true;
		map[xOffset + 0][yOffset + 4] = true;
		map[xOffset + 0][yOffset + 5] = true;
		map[xOffset + 0][yOffset + 6] = true;
		map[xOffset + 0][yOffset + 8] = true;
		map[xOffset + 0][yOffset + 10] = true;
		map[xOffset + 0][yOffset + 12] = true;
		map[xOffset + 0][yOffset + 13] = true;
		map[xOffset + 0][yOffset + 14] = true;
		map[xOffset + 1][yOffset + 0] = true;
		map[xOffset + 1][yOffset + 4] = true;
		map[xOffset + 1][yOffset + 6] = true;
		map[xOffset + 1][yOffset + 8] = true;
		map[xOffset + 1][yOffset + 9] = true;
		map[xOffset + 1][yOffset + 10] = true;
		map[xOffset + 1][yOffset + 12] = true;
		map[xOffset + 2][yOffset + 0] = true;
		map[xOffset + 2][yOffset + 2] = true;
		map[xOffset + 2][yOffset + 4] = true;
		map[xOffset + 2][yOffset + 5] = true;
		map[xOffset + 2][yOffset + 6] = true;
		map[xOffset + 2][yOffset + 8] = true;
		map[xOffset + 2][yOffset + 10] = true;
		map[xOffset + 2][yOffset + 12] = true;
		map[xOffset + 2][yOffset + 13] = true;
		map[xOffset + 3][yOffset + 0] = true;
		map[xOffset + 3][yOffset + 2] = true;
		map[xOffset + 3][yOffset + 4] = true;
		map[xOffset + 3][yOffset + 6] = true;
		map[xOffset + 3][yOffset + 8] = true;
		map[xOffset + 3][yOffset + 10] = true;
		map[xOffset + 3][yOffset + 12] = true;
		map[xOffset + 4][yOffset + 0] = true;
		map[xOffset + 4][yOffset + 1] = true;
		map[xOffset + 4][yOffset + 2] = true;
		map[xOffset + 4][yOffset + 4] = true;
		map[xOffset + 4][yOffset + 6] = true;
		map[xOffset + 4][yOffset + 8] = true;
		map[xOffset + 4][yOffset + 10] = true;
		map[xOffset + 4][yOffset + 12] = true;
		map[xOffset + 4][yOffset + 13] = true;
		map[xOffset + 4][yOffset + 14] = true;
		map[xOffset + 6][yOffset + 4] = true;
		map[xOffset + 6][yOffset + 5] = true;
		map[xOffset + 6][yOffset + 6] = true;
		map[xOffset + 6][yOffset + 8] = true;
		map[xOffset + 6][yOffset + 9] = true;
		map[xOffset + 6][yOffset + 10] = true;
		map[xOffset + 7][yOffset + 4] = true;
		map[xOffset + 7][yOffset + 6] = true;
		map[xOffset + 7][yOffset + 8] = true;
		map[xOffset + 8][yOffset + 4] = true;
		map[xOffset + 8][yOffset + 6] = true;
		map[xOffset + 8][yOffset + 8] = true;
		map[xOffset + 8][yOffset + 9] = true;
		map[xOffset + 9][yOffset + 4] = true;
		map[xOffset + 9][yOffset + 6] = true;
		map[xOffset + 9][yOffset + 8] = true;
		map[xOffset + 10][yOffset + 4] = true;
		map[xOffset + 10][yOffset + 5] = true;
		map[xOffset + 10][yOffset + 6] = true;
		map[xOffset + 10][yOffset + 8] = true;
		map[xOffset + 12][yOffset + 0] = true;
		map[xOffset + 12][yOffset + 4] = true;
		map[xOffset + 12][yOffset + 5] = true;
		map[xOffset + 12][yOffset + 6] = true;
		map[xOffset + 12][yOffset + 8] = true;
		map[xOffset + 12][yOffset + 9] = true;
		map[xOffset + 12][yOffset + 10] = true;
		map[xOffset + 12][yOffset + 12] = true;
		map[xOffset + 12][yOffset + 13] = true;
		map[xOffset + 12][yOffset + 14] = true;
		map[xOffset + 13][yOffset + 0] = true;
		map[xOffset + 13][yOffset + 5] = true;
		map[xOffset + 13][yOffset + 8] = true;
		map[xOffset + 13][yOffset + 12] = true;
		map[xOffset + 14][yOffset + 0] = true;
		map[xOffset + 14][yOffset + 5] = true;
		map[xOffset + 14][yOffset + 8] = true;
		map[xOffset + 14][yOffset + 9] = true;
		map[xOffset + 14][yOffset + 12] = true;
		map[xOffset + 14][yOffset + 13] = true;
		map[xOffset + 15][yOffset + 0] = true;
		map[xOffset + 15][yOffset + 5] = true;
		map[xOffset + 15][yOffset + 8] = true;
		map[xOffset + 15][yOffset + 12] = true;
		map[xOffset + 16][yOffset + 0] = true;
		map[xOffset + 16][yOffset + 1] = true;
		map[xOffset + 16][yOffset + 2] = true;
		map[xOffset + 16][yOffset + 4] = true;
		map[xOffset + 16][yOffset + 5] = true;
		map[xOffset + 16][yOffset + 6] = true;
		map[xOffset + 16][yOffset + 8] = true;
		map[xOffset + 16][yOffset + 12] = true;
		map[xOffset + 16][yOffset + 13] = true;
		map[xOffset + 16][yOffset + 14] = true;
	}

	/**
	 * Super techy android stuff!
	 */
	public boolean onTouchEvent(MotionEvent event) {

		if (event.getAction() == MotionEvent.ACTION_DOWN
				| event.getAction() == MotionEvent.ACTION_MOVE) {
			if(!map[(int) event.getY() / RuleSetUtil.CELL_SIZE][(int) event.getX()
					/ RuleSetUtil.CELL_SIZE])
			map[(int) event.getY() / RuleSetUtil.CELL_SIZE][(int) event.getX()
					/ RuleSetUtil.CELL_SIZE] = true;
			else
				map[(int) event.getY() / RuleSetUtil.CELL_SIZE][(int) event.getX()
				                        					/ RuleSetUtil.CELL_SIZE] = false;

		}

		invalidate();
		return false;
	}

	/**
	 * Semaphore locker.
	 */
	public void lock() {
		LOCKED = true;
	}

	/**
	 * Semaphore unlocker.
	 */
	public void unlock() {
		LOCKED = false;
	}

	/**
	 * This will return the current map
	 * 
	 * @return Game of Life map.
	 */
	public boolean[][] getMap() {
		return map;
	}

	/**
	 * MAIN GAME LOGIC. This method will draw a lot of screenmagic.
	 * 
	 * @param rule
	 *            Game of Life RuleSet
	 */
	public void runLoop() {
		new Thread(new Runnable() {

			@Override
			public void run() {

				/**
				 * This will be the neigbourhood of interest.
				 */
				boolean[][] region = new boolean[3][3];

				/**
				 * The Animation loop.
				 */
				while (LOCKED) {

					/**
					 * Generation that will life the next cycle.
					 */
					boolean[][] nextGen = new boolean[RuleSetUtil.HEIGHT][RuleSetUtil.WIDTH];

					/*
					 * AFTER HOURS OF SERIOUS BRAIN MALFUNCTIONS,
					 * I CAME UP WITH THIS ... !!!
					 * 00 01 02
					 * 10 11 12		<- 11 is our Cell everything else it's neighbours
					 * 20 21 22
					 */
					for (int h = 0; h < RuleSetUtil.HEIGHT; h++) {
						for (int w = 0; w < RuleSetUtil.WIDTH; w++) {

							// first row
							// 00
							if (h - 1 >= 0 && w - 1 >= 0)
								region[0][0] = map[h - 1][w - 1];
							else if (h - 1 < 0 && w - 1 >= 0)
								region[0][0] = map[RuleSetUtil.HEIGHT - 1][w - 1];
							else if (h - 1 >= 0 && w - 1 < 0)
								region[0][0] = map[h - 1][RuleSetUtil.WIDTH - 1];
							else if (h - 1 < 0 && w - 1 < 0)
								region[0][0] = map[RuleSetUtil.HEIGHT - 1][RuleSetUtil.WIDTH - 1];

							// 01
							if (h - 1 >= 0)
								region[0][1] = map[h - 1][w];
							else
								region[0][1] = map[RuleSetUtil.HEIGHT - 1][w];

							// 02
							if (h - 1 >= 0 && w < RuleSetUtil.WIDTH - 1)
								region[0][2] = map[h - 1][w + 1];
							else if (h - 1 < 0 && w < RuleSetUtil.WIDTH - 1)
								region[0][2] = map[RuleSetUtil.HEIGHT - 1][w + 1];
							else if (h - 1 >= 0 && w >= RuleSetUtil.WIDTH - 1)
								region[0][2] = map[h - 1][0];
							else if (h - 1 < 0 && w >= RuleSetUtil.WIDTH - 1)
								region[0][2] = map[RuleSetUtil.HEIGHT - 1][0];

							// middle row
							// 10
							if (w - 1 >= 0)
								region[1][0] = map[h][w - 1];
							else
								region[1][0] = map[h][RuleSetUtil.WIDTH - 1];

							// 11
							region[1][1] = map[h][w];

							// 12
							if (w < RuleSetUtil.WIDTH - 1)
								region[1][2] = map[h][w + 1];
							else
								region[1][2] = map[h][0];

							// last row
							// 20
							if (h < RuleSetUtil.HEIGHT - 1 && w - 1 >= 0)
								region[2][0] = map[h + 1][w - 1];
							else if (h >= RuleSetUtil.HEIGHT - 1 && w - 1 >= 0)
								region[2][0] = map[0][w - 1];
							else if (h < RuleSetUtil.HEIGHT - 1 && w - 1 < 0)
								region[2][0] = map[h + 1][RuleSetUtil.WIDTH - 1];
							else if (h >= RuleSetUtil.HEIGHT - 1 && w - 1 < 0)
								region[2][0] = map[0][RuleSetUtil.WIDTH - 1];

							// 21
							if (h < RuleSetUtil.HEIGHT - 1)
								region[2][1] = map[h + 1][w];
							else
								region[2][1] = map[0][w];

							// 22
							if (h < RuleSetUtil.HEIGHT - 1 && w < RuleSetUtil.WIDTH - 1)
								region[2][2] = map[h + 1][w + 1];
							else if (h >= RuleSetUtil.HEIGHT - 1 && w < RuleSetUtil.WIDTH - 1)
								region[2][2] = map[0][w + 1];
							else if (h < RuleSetUtil.HEIGHT - 1 && w >= RuleSetUtil.WIDTH - 1)
								region[2][2] = map[h + 1][0];
							else if (h >= RuleSetUtil.HEIGHT - 1 && w >= RuleSetUtil.WIDTH - 1)
								region[2][2] = map[0][0];

							// ----------------------------
							nextGen[h][w] = RuleSetUtil.apply(region);
						}
					}
					map = nextGen;
					drawMap(nextGen);
				}
			}
		}).start();
	}
}
