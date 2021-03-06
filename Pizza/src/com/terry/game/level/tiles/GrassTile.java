package com.terry.game.level.tiles;

import com.terry.game.gfx.Render;
import com.terry.game.gfx.Sprite;
import com.terry.game.level.Tile;

public class GrassTile extends Tile {

	public GrassTile(int id) {
		super(id);
	}

	protected void render(int x, int y, Render render) {
		render.render(x << 5, y << 5, Sprite.grass, false, false, false);
	}

	public boolean Solid() {
		return false;
	}
}
