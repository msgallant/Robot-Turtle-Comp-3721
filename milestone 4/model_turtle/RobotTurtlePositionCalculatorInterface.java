package model_turtle;

public interface RobotTurtlePositionCalculatorInterface {
	boolean checkIfTileAheadExists();
	int[] calculateNewPosition();
	int directionShiftRight();
	int directionShiftLeft();
}
