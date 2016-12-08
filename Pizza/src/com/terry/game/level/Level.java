package com.terry.game.level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.terry.game.entity.Entity;
import com.terry.game.entity.mob.Player;
import com.terry.game.gfx.Render;
import com.terry.game.gfx.Sprite;

public class Level {

	public int width, height;
	public int[] tiles;
	private Random random = new Random();

	public static List<Entity> entities = new ArrayList<Entity>();
	public static List<Player> players = new ArrayList<Player>();

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new int[width * height];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (random.nextInt(10) > 1) tiles[x + y * width] = Tile.grass.id;
				else tiles[x + y * width] = Tile.stone.id;
			}
		}
	}

	public void tick() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).tick();
		}

		for (int i = 0; i < players.size(); i++) {
			players.get(i).tick();
		}
	}

	public Player player;

	public void add(Entity e) {
		if (e instanceof Player) {
			player = (Player) e;
		}
		entities.add(e);
		e.init(this);
	}

	public void clear(Entity e) {
		if (e instanceof Player) {
			for (int i = 0; i < players.size(); i++) {
				player = (Player) e;
			}
		}
		entities.remove(e);
	}

	public void render(int xScroll, int yScroll, Render render) {
		render.setOffsets(xScroll, yScroll);
		int x0 = xScroll >> 5;
		int x1 = (xScroll + render.width + Sprite.TSIZE) >> 5;
		int y0 = yScroll >> 5;
		int y1 = (yScroll + render.height + Sprite.TSIZE) >> 5;

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, render);
			}
		}

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(render);
		}

		for (int i = 0; i < players.size(); i++) {
			players.get(i).render(render);
		}
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height) return Tile.voidTile;
		return Tile.tiles[tiles[x + y * width]];

	}
}
