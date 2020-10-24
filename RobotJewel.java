public class RobotJewel extends Tile
{
  private int xPos, yPos;
  private String colour;
  
  public RobotJewel(int x, int y, String c)
  {
	super(x, y);
    colour = c;
    this.setTileType("Jewel");
  }
  public String getJewelColour()
  {
	  return colour;
  }

}
