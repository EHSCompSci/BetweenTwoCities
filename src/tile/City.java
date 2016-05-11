package tile;

public class City {
	
	private Tile[][] city;
	private int westCol;
	private int eastCol;
	private int northRow;
	private int southRow;
	private int size;
	
	public City() {
		city = new Tile[9][9];
		westCol = 4;
		eastCol = 4;
		northRow = 4;
		southRow = 4;
		size = 0;
	}
	
	public boolean addTile(int row, int col, Tile tile) {
		boolean isEmpty = true;
		for (Tile[] tileRow : city) {
			for (Tile t : tileRow) {
				if (t != null) {
					isEmpty = false;
				}
			}
		}
		if (isEmpty) {
			city[4][4] = tile;
			size++;
			return true;
		}
		if (col - westCol > 3 || eastCol - col > 3 || row - northRow > 3 || southRow - row > 3) {
			return false;
		}
		if (city[row][col] != null) {
			return false;
		}
		if (city[row - 1][col] == null && city[row + 1][col] == null && 
				city[row][col - 1] == null && city[row][col + 1] == null) {
			return false;
		}
		if (col > eastCol) {
			eastCol = col;
		} else if (col < westCol) {
			westCol = col;
		} else if (row > southRow) {
			southRow = row;
		} else if (row < northRow) {
			northRow = row;
		}
		city[row][col] = tile;
		size++;
		return true;
	}
}
