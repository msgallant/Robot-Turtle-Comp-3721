public class GameBoard
{
	private Tile[][] tileList;
	
	public GameBoard()
	{
		tileList = new Tile[8][8];
		
	}
	
	//x and y are position on gameboard, valid coordinates are 0-7
	public Tile[][] addStoneWall(int x, int y)
	{
		StoneWall t = new StoneWall(x, y);
		tileList[x][y] =t;
		return tileList;
	}
	
	public Tile[][] addCrate(int x, int y)
	{
		Crate t = new Crate(x, y);
		tileList[x][y] =t;
		return tileList;
	}
	public Tile[][] addJewel(int[] pos, String color)
	{

		RobotJewel r = new RobotJewel(pos[0], pos[1], color);
		tileList[pos[0]][pos[1]] = r;
		return tileList;
	}
	public Tile[][] addTurtle(int[] pos)
	{

		tileList[pos[0]][pos[1]].setOccupied(true);
		return tileList;
	}
	
	//need move turtle the updates occupied status
	public Tile[][] updateTurtlePositionOnBoard(int[] newPos, int[] oldPos)
	{
		tileList[oldPos[0]][oldPos[1]].setOccupied(false);
		tileList[newPos[0]][newPos[1]].setOccupied(true);
		return tileList;
	}
	

}
