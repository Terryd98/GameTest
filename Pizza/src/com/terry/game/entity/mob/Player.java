package com.terry.game.entity.mob;

import com.terry.game.gfx.Render;
import com.terry.game.gfx.Sprite;
import com.terry.game.input.Keyboard;
import com.terry.game.level.Level;

public class Player extends Mob {

	private Keyboard key;

	public Player(int x, int y, Keyboard key, Level level) {
		this.key = key;
		findStartPos(level);
	}

	public void tick() {
		int xa = 0, ya = 0;

		if (key.up) ya--;
		if (key.down) ya++;
		if (key.left) xa--;
		if (key.right) xa++;

		if (xa != 0 || ya != 0) {
			move(xa, 0);
			move(0, ya);
		}

	}

	public void render(Render render) {
		render.render(x - (32 / 2), y - (32 / 2), Sprite.player_down, false, false, false);
	}

	public void findStartPos(Level level) {
		while (true) {
			int x = random.nextInt(level.width);
			int y = random.nextInt(level.height);
			if (!level.getTile(x, y).Solid()) {
				this.x = (x * 32) + 16;
				this.y = (y * 32) + 16;
				return;
			}
		}
	}
}
