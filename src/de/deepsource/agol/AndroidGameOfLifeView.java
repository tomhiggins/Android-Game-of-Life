package de.deepsource.agol;

import de.deepsource.agol.rules.RuleSet;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import java.util.Random;

/**
 * @author Sebastian Ullrich
 * @mood self-doubting
 * 
 * This is the super unified jack of all trades class. 
 * No more confusing Model-View-Controller madness.
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
	 * @param context Context
	 */
	public AndroidGameOfLifeView(Context context) {
		super(context);
		paint.setColor(Color.GREEN);
		white.setColor(Color.BLACK);
		initMap();
	}

	/**
	 * This will draw our Game of Life.
	 */
	public void onDraw(Canvas canvas) {
		canvas.drawRect((float) 0, (float) 0, (float) getWidth(),
				(float) getHeight(), white);

		for (int h = 0; h < RuleSet.HEIGHT; h++)
			for (int w = 0; w < RuleSet.WIDTH; w++)
				if (map[h][w])
					canvas.drawRect(w * RuleSet.CELL_SIZE, h
							* RuleSet.CELL_SIZE, (w * RuleSet.CELL_SIZE)
							+ RuleSet.CELL_SIZE, (h * RuleSet.CELL_SIZE)
							+ RuleSet.CELL_SIZE, paint);
	}

	/**
	 * Method to draw a boolean array.
	 * @param m array of boolean
	 */
	private void drawMap(boolean[][] m) {
		this.map = m;
		postInvalidate();
	}

	/**
	 * This method will initiate our first map.
	 */
	public void initMap() {
		map = new boolean[RuleSet.HEIGHT][RuleSet.WIDTH];
		
		/**
		 * Fills the map with (don't tell anybody) random values. 
		 */
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < RuleSet.WIDTH; j++){
				map[i][j] = new Random().nextBoolean();
			}
		}
	}

	/**
	 * Super techy android stuff!
	 */
	public boolean onTouchEvent(MotionEvent event) {
		
		if (event.getAction() == MotionEvent.ACTION_DOWN
				| event.getAction() == MotionEvent.ACTION_MOVE) {
			map[(int) event.getY() / RuleSet.CELL_SIZE][(int) event.getX()
					/ RuleSet.CELL_SIZE] = true;
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
	 * @return Game of Life map.
	 */
	public boolean[][] getMap() {
		return map;
	}

	/**
	 * MAIN GAME LOGIC. This method will draw a lot of screenmagic.
	 * @param rule Game of Life RuleSet
	 */
	public void runLoop(final RuleSet rule) {
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
					boolean[][] nextGen = new boolean[RuleSet.HEIGHT][RuleSet.WIDTH];
					
					/**
					 * AFTER HOURS OF SERIOUS BRAIN MALFUNCTIONS,
					 * I CAME UP WITH THIS ... ¯\_(ツ)_/¯ !!!
					 */
					for (int h = 0; h < RuleSet.HEIGHT; h++) {
						for (int w = 0; w < RuleSet.WIDTH; w++) {

							// first row
							// 00
							if (h - 1 >= 0 & w - 1 >= 0)
								region[0][0] = map[h - 1][w - 1];
							else
								region[0][0] = false;

							// 01
							if (h - 1 >= 0)
								region[0][1] = map[h - 1][w];
							else
								region[0][1] = false;

							// 02
							if (h - 1 >= 0 & w < RuleSet.WIDTH - 1)
								region[0][2] = map[h - 1][w + 1];
							else
								region[0][2] = false;

							// middle row
							// 10
							if (w - 1 >= 0)
								region[1][0] = map[h][w - 1];
							else
								region[1][0] = false;

							// 11
							region[1][1] = map[h][w];

							// 12
							if (w < RuleSet.WIDTH - 1)
								region[1][2] = map[h][w + 1];
							else
								region[1][2] = false;

							// last row
							// 20
							if (h < RuleSet.HEIGHT - 1 & w - 1 >= 0)
								region[2][0] = map[h + 1][w - 1];
							else
								region[2][0] = false;

							// 21
							if (h < RuleSet.HEIGHT - 1)
								region[2][1] = map[h + 1][w];
							else
								region[2][1] = false;

							// 22
							if (h < RuleSet.HEIGHT - 1 & w < RuleSet.WIDTH - 1)
								region[2][2] = map[h + 1][w + 1];
							else
								region[2][2] = false;

							// ----------------------------
							nextGen[h][w] = rule.apply(region);
						}
					}
					map = nextGen;
					drawMap(nextGen);
				}
			}
		}).start();
	}
}
