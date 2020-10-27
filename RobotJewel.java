public class RobotJewel extends Tile
{
  private int xPos, yPos;
  private String colour;
  
  public RobotJewel(int x, int y, String c)
  {
	super(x, y);
    this.setColour(c);
    this.setTileType("Robot Jewel");
  }
  public String getJewelColour()
  {
	  return colour;
  }

}
