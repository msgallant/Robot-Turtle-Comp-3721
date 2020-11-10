package model_tiles;

public interface CratePositionCalculatorInterface {
	public boolean[] findMovableDirectionsForCrates(Tile t);
	public int[] findNewCratePosition(int[] oldPos, int directionPushed);
}
