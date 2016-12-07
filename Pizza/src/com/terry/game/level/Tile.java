package com.terry.game.level;

import com.terry.game.gfx.Render;
import com.terry.game.level.tiles.GrassTile;
import com.terry.game.level.tiles.StoneTile;
import com.terry.game.level.tiles.VoidTile;

public class Tile {

	public static Tile[] tiles = new Tile[100];
	protected int id;

	public static Tile voidTile = new VoidTile(0);
	public static Tile grass = new GrassTile(1);
	public static Tile stone = new StoneTile(2);


	public Tile(int id) {
		this.id = id;
		tiles[id] = this;
	}

	protected void render(int x, int y, Render render) {
	}

	public boolean Solid() {
		return false;
	}

}
