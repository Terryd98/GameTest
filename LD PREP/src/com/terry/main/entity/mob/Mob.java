package com.terry.main.entity.mob;

import com.terry.main.entity.Entity;
import com.terry.main.gfx.Render;

public class Mob extends Entity {

	public int dir;
	public boolean isMoving = false;

	public void move(int xa, int ya) {
		isMoving = true;

		if (!collision()) {
			x += xa;
			y += ya;
		}
	}

	public boolean collision() {
		return false;
	}

	public void update() {
	}

	public void render(Render render) {
	}

}
