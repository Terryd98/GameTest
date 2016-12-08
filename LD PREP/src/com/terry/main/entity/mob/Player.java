package com.terry.main.entity.mob;

import java.util.Random;

import com.terry.main.Game;
import com.terry.main.entity.input.Input;
import com.terry.main.gfx.Render;

public class Player extends Mob {

	private Input input;
	public int speed;
	public static int size;
	public int time = 0;
	public int tickCount = 0;
	private int col = 0;
	private Random random = new Random();

	public Player(int x, int y, Input input) {
		this.x = x + random.nextInt(500);
		this.y = y + random.nextInt(200);
		this.input = input;
	}

	public void update() {
		int xa = 0, ya = 0;
		speed = 3;
		tickCount++;
		if ((tickCount % 50) == 10) time++;
		size = 10 + time;
		if (input.up) ya -= speed;
		else if (input.down) ya += speed;
		if (input.left) xa -= speed;
		else if (input.right) xa += speed;

		if (tickCount % 60 == 0) col = random.nextInt(0xffffff);

		if (xa != 0 || ya != 0) {
			move(xa, 0);
			move(0, ya);
		}
	}

	public void render(Render render) {
		render.renderRect(x, y, size, size, col);
	}

}
