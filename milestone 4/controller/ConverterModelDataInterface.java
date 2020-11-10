package controller;

import model_tiles.Tile;
import model.ModelDataInterface;
public interface ConverterModelDataInterface extends ModelDataInterface {
	public int getGameBoardDimension();
	public Tile[][] getTileList();
}
