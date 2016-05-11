package tile;

/**
 * This class represents a tile used for placing a building.
 * @author Zachary Wooten, Sam Crider
 * @version 2016-05-10
 */
public class Tile {
	/** The height of the tile. */
	private int height;
	/** The width of the tile. */
	private int width;
	/** The building type for this tile. */
	private BuildingType type;
	
	/**
	 * 
	 * @param height
	 * @param width
	 * @param type
	 */
	public Tile(int height, int width, BuildingType type) {
		this.height = height;
		this.width = width;
		this.type = type;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	
}
