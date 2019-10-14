package eu.wauz.wauzraycaster.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JFrame;

import eu.wauz.wauzraycaster.entity.Controller;
import eu.wauz.wauzraycaster.entity.MovingEntity;
import eu.wauz.wauzraycaster.entity.doom.DoomCamera;
import eu.wauz.wauzraycaster.entity.doom.DoomTestEntity;
import eu.wauz.wauzraycaster.util.WrayUtils;

/**
 * The game window is the most important part of a game.
 * It displays the map and entities and handles user input.
 * It lets events run on a fixed framerate.
 * 
 * @author Wauzmons
 */
public class GameWindow extends JFrame implements Runnable {

	/**
	 * This runnable's UUID.
	 */
	private static final long serialVersionUID = 5941362699534781057L;
	
	/**
	 * The main thread of the game.
	 */
	private Thread mainThread = new Thread(this);
	
	/**
	 * If the main thread is running.
	 */
	private boolean isMainThreadRunning;
	
	/**
	 * The width of the game window.
	 */
	private int width = 640;
	
	/**
	 * The height of the game window.
	 */
	private int height = 480;
	
	/**
	 * The title of the game window.
	 */
	private String title;
	
	/**
	 * The image of the game window.
	 */
	private BufferedImage display;
	
	/**
	 * The pixels of the image of the game window.
	 */
	private int[] pixels;
	
	/**
	 * The targeted frames per second.
	 */
	private int fps = 30;
	
	/**
	 * The current game map.
	 */
	private GameMap currentMap;
	
	/**
	 * The current game camera.
	 */
	private Controller currentCamera;
	
	/**
	 * All moving entities, located on the map.
	 */
	private List<MovingEntity> entities = new ArrayList<>();
	
	/**
	 * The background music, running in a loop.
	 */
	private Clip bgm;
	
	/**
	 * The path of the background music resource.
	 */
	private String bgmPath;
	
	/**
	 * Initializes a game window with given size.
	 * 
	 * @param width
	 * @param height
	 */
	public GameWindow(int width, int height) {
		this.width = width;
		this.height = height;
		initialize();
	}
	
	/**
	 * Initializes an empty game window.
	 */
	public GameWindow() {
		initialize();
	}
	
	/**
	 * Loads the displayed image, aswell as window size, title, background and visibility.
	 */
	private void initialize() {
		display = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) display.getRaster().getDataBuffer()).getData();
		
		setSize(width, height);
		setResizable(false);
		setTitle("WauzRaycaster");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.BLACK);
		setLocationRelativeTo(null);
		this.setUndecorated(true);
		setVisible(true);
	}
	
	/**
	 * Starts the main thread.
	 */
	public synchronized void start() {
		isMainThreadRunning = true;
		mainThread.start();
	}
	
	/**
	 * Stops the main thread.
	 */
	public synchronized void stop() {
		isMainThreadRunning = false;
		try {
			mainThread.join();
			if(bgm != null) {
				bgm.stop();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lets the main thread run a loop with music and images updates every iteration.
	 * The map and entities are updated at the given framerate.
	 */
	@Override
	public void run() {
		long lastRun = System.nanoTime();
		final double interval = 1000000000.0 / (double) fps;
		double delta = 0;
		requestFocus();
		
		try {
			if(bgmPath != null) {
				AudioInputStream audioIn;
				audioIn = AudioSystem.getAudioInputStream(WrayUtils.getResource(bgmPath));
				bgm = AudioSystem.getClip();
				bgm.open(audioIn);
				bgm.setLoopPoints(0, -1);
				FloatControl gainControl = (FloatControl) bgm.getControl(FloatControl.Type.MASTER_GAIN);
				float range = gainControl.getMaximum() - gainControl.getMinimum();
				float gain = (range * 0.75f) + gainControl.getMinimum();
				gainControl.setValue(gain);
				bgm.loop(Clip.LOOP_CONTINUOUSLY);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		while(isMainThreadRunning) {
			long thisRun = System.nanoTime();
			delta = delta + ((thisRun - lastRun) / interval);
			lastRun = thisRun;
			
			while (delta >= 1) {
				currentMap.render(this);
				for(MovingEntity entity : entities) {
					entity.updatePosition(currentMap.getMapMatrix());
				}
				delta--;
			}
			render();
		}
	}
	
	/**
	 * Draws the image onto the window.
	 */
	public void render() {
		try {
			BufferStrategy bufferStrategy = getBufferStrategy();
			if(bufferStrategy == null) {
				createBufferStrategy(3);
				return;
			}
			Graphics graphics = bufferStrategy.getDrawGraphics();
			graphics.drawImage(display, 0, 0, display.getWidth(), display.getHeight(), null);
			bufferStrategy.show();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param map The new game map.
	 */
	public void loadMap(GameMap map) {
		currentMap = map;
	}
	
	/**
	 * Places a pseudo 3D camera, for doom-like levels.
	 * 
	 * @param matrixX The camera's x position on the map matrix.
	 * @param matrixY The camera's y position on the map matrix.
	 */
	public void placeDoomCamera(int matrixX, int matrixY) {
		currentCamera = new DoomCamera(matrixY + 0.5, currentMap.getMapWidth() - (matrixX + 0.5), 1, 0, 0, -0.7);
		if(currentCamera instanceof MovingEntity) {
			entities.add((MovingEntity) currentCamera);
		}
		addKeyListener(currentCamera);
	}
	
	/**
	 * @param camera The new game camera with key listener.
	 */
	public void placeCamera(Controller camera) {
		removeCamera();
		currentCamera = camera;
		if(currentCamera instanceof MovingEntity) {
			entities.add((MovingEntity) currentCamera);
		}
		addKeyListener(currentCamera);
	}
	
	/**
	 * Removes the current camera and its key listener.
	 */
	public void removeCamera() {
		if(currentCamera == null) {
			return;
		}
		if(currentCamera instanceof MovingEntity) {
			entities.remove((MovingEntity) currentCamera);
		}
		removeKeyListener(currentCamera);
	}
	
	/**
	 * Places an entity, for doom-like levels.
	 * 
	 * @param matrixX The entity's x position on the map matrix.
	 * @param matrixY The entity's y position on the map matrix.
	 */
	public void placeDoomEntity(int matrixX, int matrixY) {
		MovingEntity entity = new DoomTestEntity(matrixY + 0.5, currentMap.getMapWidth() - (matrixX + 0.5), 1, 0, 0, -0.7);
		entities.add(entity);
	}
	
	/**
	 * @return The width of the game window.
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * @return The width of the game window.
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * @return The pixels of the image of the game window.
	 */
	public int[] getPixels() {
		return pixels;
	}

	/**
	 * @param pixels The new pixels of the image of the game window.
	 */
	public void setPixels(int[] pixels) {
		this.pixels = pixels;
	}

	/**
	 * @return The title of the game window.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param The new title of the game window.
	 */
	public void setTitle(String title) {
		this.title = title;
		super.setTitle(title);
	}

	/**
	 * @return The targeted frames per second.
	 */
	public int getFps() {
		return fps;
	}

	/**
	 * @param fps The new targeted frames per second.
	 */
	public void setFps(int fps) {
		this.fps = fps;
	}
	
	/**
	 * @return The current game map.
	 */
	public GameMap getCurrentMap() {
		return currentMap;
	}

	/**
	 * @return The current game camera.
	 */
	public Controller getCurrentCamera() {
		return currentCamera;
	}

	/**
	 * @return The path of the background music resource.
	 */
	public String getBgmPath() {
		return bgmPath;
	}

	/**
	 * @param bgmPath The new path of the background music resource.
	 */
	public void setBgmPath(String bgmPath) {
		this.bgmPath = bgmPath;
	}

}
