package com.terry.game.entity.mob;

import com.terry.game.entity.Entity;
import com.terry.game.gfx.Render;
import com.terry.game.gfx.Sprite;
import com.terry.game.level.Level;

public class Mob extends Entity {

	public int x, y;
	public Sprite sprite;
	public int dir;
	public boolean isWalking = false;

	public void move(int xa, int ya) {
		if (xa < 0) dir = 4;
		if (xa > 0) dir = 1;
		if (ya < 0) dir = 2;
		if (ya > 0) dir = 3;

		if (!collision(xa, ya)) {
			x += xa;
			y += ya;
		}

	}

	public boolean collision(int xa, int ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * 7 - 4) / 32;
			int yt = ((y + ya) + c / 2 * 10 + 3) / 32;
			if (y < 0) y = 0;
			if (x <= 0) x = 0;
			if (level.getTile(xt, yt).Solid()) {
				return solid = true;
			}
		}

		return solid;
	}

	public void tick() {
	}

	public void render(Render render) {
	}

}
