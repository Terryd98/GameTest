package com.terry.main.entity;

import com.terry.main.gfx.Render;

public class Entity {

	public int x, y;

	private boolean removed = false;

	public boolean remove() {
		return removed = true;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void update() {
	}

	public void render(Render render) {
	}
}
