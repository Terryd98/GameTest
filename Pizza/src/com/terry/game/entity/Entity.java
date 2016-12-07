package com.terry.game.entity;

import java.util.Random;

import com.terry.game.gfx.Render;
import com.terry.game.level.Level;

public class Entity {

	private boolean removed;
	protected Level level;
	protected static Random random = new Random();

	public void tick() {
	}

	public void render(Render render) {
	}

	public boolean isRemoved() {
		return removed;
	}

	public void init(Level level) {
		this.level = level;
	}

}
