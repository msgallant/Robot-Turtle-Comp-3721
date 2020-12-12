package model_tiles;

public class IceWall extends Tile {

	public IceWall(int x, int y) {
		super(x, y);
		this.setSMovable(false);
		this.setNMovable(false);
		this.setWMovable(false);
		this.setEMovable(false);
		
		this.setTileType("Ice Wall");
	}

}
