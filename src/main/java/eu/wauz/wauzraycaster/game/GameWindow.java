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
import javax.swing.JFrame;

import eu.wauz.wauzraycaster.WrayUtils;
import eu.wauz.wauzraycaster.entity.GameCamera;
import eu.wauz.wauzraycaster.entity.MovingEntity;
import eu.wauz.wauzraycaster.entity.TestEntity;

public class GameWindow extends JFrame implements Runnable {

	private static final long serialVersionUID = 5941362699534781057L;
	
	private Thread mainThread = new Thread(this);
	
	private boolean isMainThreadRunning;
	
	private int width = 640;
	
	private int height = 480;
	
	private BufferedImage display;
	
	private int[] pixels;
	
	private int fps = 30;
	
	private GameMap currentMap;
	
	private GameCamera currentCamera;
	
	private List<MovingEntity> entities = new ArrayList<>();
	
	public GameWindow(int width, int height) {
		this.width = width;
		this.height = height;
		initialize();
	}
	
	public GameWindow() {
		initialize();
	}
	
	private void initialize() {
		display = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) display.getRaster().getDataBuffer()).getData();
		
		setSize(width, height);
		setResizable(false);
		setTitle("WauzRaycaster");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.BLACK);
		setLocationRelativeTo(null);
		setVisible(true);
		start();
	}
	
	private synchronized void start() {
		isMainThreadRunning = true;
		mainThread.start();
	}
	
	public synchronized void stop() {
		isMainThreadRunning = false;
		try {
			mainThread.join();
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		long lastRun = System.nanoTime();
		final double interval = 1000000000.0 / (double) fps;
		double delta = 0;
		requestFocus();
		
		try {
			AudioInputStream audioIn;
			audioIn = AudioSystem.getAudioInputStream(WrayUtils.getResource("sound/doom/d_e1m1.mid"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		while(isMainThreadRunning) {
			long thisRun = System.nanoTime();
			delta = delta + ((thisRun - lastRun) / interval);
			lastRun = thisRun;
			
			while (delta >= 1) {
				if(currentCamera != null && currentMap != null) {
					currentMap.render(currentCamera, pixels, width, height);
					for(MovingEntity entity : entities) {
						entity.updatePosition(currentMap.getMapMatrix());
					}
				}
				delta--;
			}
			render();
		}
	}
	
	public void render() {
		BufferStrategy bufferStrategy = getBufferStrategy();
		if(bufferStrategy == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics graphics = bufferStrategy.getDrawGraphics();
		graphics.drawImage(display, 0, 0, display.getWidth(), display.getHeight(), null);
		bufferStrategy.show();
	}
	
	public void loadMap(GameMap map) {
		currentMap = map;
	}
	
	public void placeCamera(int xPos, int yPos) {
		if(currentCamera != null) {
			entities.remove(currentCamera);
			removeKeyListener(currentCamera);
		}
		currentCamera = new GameCamera(xPos + 0.5, yPos + 0.5, 1, 0, 0, -0.7);
		entities.add(currentCamera);
		addKeyListener(currentCamera);
	}
	
	public void placeEntity(int xPos, int yPos) {
		MovingEntity entity = new TestEntity(xPos, yPos, 1, 0, 0, -0.7);
		entities.add(entity);
	}

	public int getFps() {
		return fps;
	}

	public void setFps(int fps) {
		this.fps = fps;
	}

}
