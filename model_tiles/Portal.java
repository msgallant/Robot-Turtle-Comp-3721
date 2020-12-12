package model_tiles;

public class Portal extends Tile {

	private Tile attachedPortal = null;
	
	public Portal(int x, int y) {
		super(x, y);
		
		this.setTileType("Portal");
		
		
	}
	public void setAttachedPortal(Tile p)
	{
		this.setETile(p);
		this.setWTile(p);
		this.setSTile(p);
		this.setNTile(p);
		attachedPortal = p;
	}
	public Tile getAttachedPortal()
	{
		return attachedPortal;
	}
	public void deepCopy(Tile t)
	{
		Portal p = (Portal) t;
		super.deepCopy(t);
		this.setAttachedPortal(p.getAttachedPortal());
	}

}
