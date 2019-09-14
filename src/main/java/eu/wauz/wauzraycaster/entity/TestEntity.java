package eu.wauz.wauzraycaster.entity;

public class TestEntity extends MovingEntity {
	
	public TestEntity(double xPos, double yPos, double xDir, double yDir, double xPlane, double yPlane) {
		super(xPos, yPos, xDir, yDir, xPlane, yPlane);
	}
	
	public void updatePosition(int[][] map) {
		if(true) {
			moveForward(map);
		}
		if(true) {
			rotate(ROTATION_SPEED);
		}
	}

}
