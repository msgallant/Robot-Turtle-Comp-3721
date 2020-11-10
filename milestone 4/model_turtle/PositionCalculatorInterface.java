package model_turtle;

public interface PositionCalculatorInterface extends RobotTurtlePositionCalculatorInterface{
	boolean checkIfTileAheadExists();
	int[] calculateNewPosition();
}
