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

import eu.wauz.wauzraycaster.entity.GameCamera;
import eu.wauz.wauzraycaster.entity.MovingEntity;
import eu.wauz.wauzraycaster.entity.TestEntity;
import eu.wauz.wauzraycaster.util.WrayUtils;

public class GameWindow extends JFrame implements Runnable {

	private static final long serialVersionUID = 5941362699534781057L;
	
	private Thread mainThread = new Thread(this);
	
	private boolean isMainThreadRunning;
	
	private int width = 640;
	
	private int height = 480;
	
	private String title;
	
	private BufferedImage display;
	
	private int[] pixels;
	
	private int fps = 30;
	
	private GameMap currentMap;
	
	private GameCamera currentCamera;
	
	private List<MovingEntity> entities = new ArrayList<>();
	
	private Clip bgm;
	
	private String bgmPath;
	
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
	}
	
	public synchronized void start() {
		isMainThreadRunning = true;
		mainThread.start();
	}
	
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
	
	public void placeCamera(int matrixX, int matrixY) {
		if(currentCamera != null) {
			entities.remove(currentCamera);
			removeKeyListener(currentCamera);
		}
		currentCamera = new GameCamera(matrixY + 0.5, currentMap.getMapWidth() - (matrixX + 0.5), 1, 0, 0, -0.7);
		entities.add(currentCamera);
		addKeyListener(currentCamera);
	}
	
	public void placeEntity(int matrixX, int matrixY) {
		MovingEntity entity = new TestEntity(matrixY + 0.5, currentMap.getMapWidth() - (matrixX + 0.5), 1, 0, 0, -0.7);
		entities.add(entity);
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int[] getPixels() {
		return pixels;
	}

	public void setPixels(int[] pixels) {
		this.pixels = pixels;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		super.setTitle(title);
	}

	public int getFps() {
		return fps;
	}

	public void setFps(int fps) {
		this.fps = fps;
	}
	
	public GameMap getCurrentMap() {
		return currentMap;
	}

	public GameCamera getCurrentCamera() {
		return currentCamera;
	}

	public String getBgmPath() {
		return bgmPath;
	}

	public void setBgmPath(String bgmPath) {
		this.bgmPath = bgmPath;
	}

}
