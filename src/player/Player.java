package player;

import java.util.ArrayList;
import tile.City;
import tile.Tile;

public class Player {
	private ArrayList<Tile> hand;
	private City leftCity;
	private City rightCity;
	private String name;
	private String token;
	
	public Player(City leftCity, City rightCity, String name, String token) {
		hand = new ArrayList<Tile>();
		this.leftCity = leftCity;
		this.rightCity = rightCity;
		this.name = name;
		this.token = token;
	}
	
	public void drawTile(Tile t) {
		hand.add(t);
	}
	
	public boolean playTile(City c, int row, int col, Tile t) {
		if (c.addTile(row, col, t)) {
			hand.remove(t);
			return true;
		}
		return false;
	}
	
	public Tile discardTile(Tile t) {
		int index = hand.indexOf(t);
		if (index < 0) {
			return null;
		}
		return hand.remove(index);
	}
}
