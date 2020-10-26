public class GameBoard
{
	private Tile[][] tileList;
	private int GAME_BOARD_DIMENSION = 8;
	public GameBoard()
	{
		tileList = new Tile[GAME_BOARD_DIMENSION][GAME_BOARD_DIMENSION];
		createEmptyGameBoard();
	}
	
	//x and y are position on gameboard, valid coordinates are 0-7
	public Tile[][] addStoneWall(int x, int y)
	{
		StoneWall t = new StoneWall(x, y);
		tileList[x][y] = t;
		return tileList;
	}
	
	public Tile[][] addCrate(int x, int y)
	{
		Crate t = new Crate(x, y);
		tileList[x][y] =t;
		return tileList;
	}
	public Tile[][] addRobotJewel(int[] pos, String color)
	{

		RobotJewel r = new RobotJewel(pos[0], pos[1], color);
		tileList[pos[0]][pos[1]] = r;
		return tileList;
	}
	public Tile[][] addRobotTurtle(int[] pos, RobotTurtle rt)
	{
		tileList[pos[0]][pos[1]].setOccupied(true);
		tileList[pos[0]][pos[1]].setRobotTurtle(rt);
		return tileList;
	}
	public Tile[][] createEmptyGameBoard()
	{
		for (int row =0; row < GAME_BOARD_DIMENSION; row++ )
		{
			for (int col =0; col < GAME_BOARD_DIMENSION; col++)
			{
				Tile t = new Tile(col, row);
				tileList[col][row] = t;
			}
			
		}
		return tileList;
	}
	
	// need to update occupied status when turtle is moved.
	public Tile[][] updateTurtlePositionOnBoard(int[] newPos, int[] oldPos)
	{
		tileList[oldPos[0]][oldPos[1]].setOccupied(false);
		tileList[newPos[0]][newPos[1]].setOccupied(true);
		return tileList;
	}
	public Tile[][] getTileList()
	{
		return tileList;
	}
	

}
