package controller;

import model_tiles.Tile;

import java.util.ArrayList;

import model.Card;
import model.ModelDataInterface;
public interface ConverterModelDataInterface extends ModelDataInterface {
	public int getGameBoardDimension();
	public Tile[][] getTileList();
	
	public ArrayList<Card> getProgram();
	public ArrayList<Card> getFunctionFrog();
	public String getCardType(Card c);
}
