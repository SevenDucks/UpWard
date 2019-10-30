package eu.wauz.wauzraycaster.entity.isaac;

import java.awt.Color;

import eu.wauz.wauzraycaster.game.GameWindow;
import eu.wauz.wauzraycaster.textures.GameTexture;

/**
 * TODO: Document me!
 * 
 * @author Wauzmons
 */
public class IsaacEntityFactory {
	
	public static void placeCamera(GameWindow game, double xPos, double yPos) {
		IsaacCamera camera = new IsaacCamera(xPos + pixelsToBlock(8), yPos + pixelsToBlock(6));
		camera.setTexture(new GameTexture("images/isaac/joe.png", 32));
		camera.setShotTexture(new GameTexture(Color.CYAN, 8));
		camera.setShotSize(0.25);
		camera.setOffsetTop(pixelsToBlock(6));
		camera.setOffsetBottom(pixelsToBlock(1));
		camera.setOffsetLeft(pixelsToBlock(8));
		camera.setOffsetRight(pixelsToBlock(8)); 
		game.placeCamera(camera);
	}
	
	public static void placeTestEntity(GameWindow game, double xPos, double yPos) {
		IsaacTestEntity entity = new IsaacTestEntity(xPos + pixelsToBlock(5), yPos + pixelsToBlock(5));
		entity.setTexture(new GameTexture("images/isaac/flungus.png", 32));
		entity.setOffsetTop(pixelsToBlock(5));
		entity.setOffsetBottom(pixelsToBlock(1));
		entity.setOffsetLeft(pixelsToBlock(5));
		entity.setOffsetRight(pixelsToBlock(5)); 
		game.placeEntity(entity);
	}
	
	private static double pixelsToBlock(int pixels) {
		return 0.03125 * pixels;
	}

}
