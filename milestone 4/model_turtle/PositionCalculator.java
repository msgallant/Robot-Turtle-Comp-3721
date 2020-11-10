package model_turtle;

public interface PositionCalculator {
	boolean checkIfTileAheadExists();
	int[] calculateNewPosition();
}
