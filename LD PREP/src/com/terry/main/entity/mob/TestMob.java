package com.terry.main.entity.mob;

import java.util.Random;

import com.terry.main.entity.input.Input;
import com.terry.main.gfx.Render;

public class TestMob extends Mob {

	public int speed;
	public static int size;
	public int time = 0;
	public int tickCount = 0;
	private Random random = new Random();
	int xa = 0, ya = 0;

	public TestMob(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void update() {
		speed = 2;
		// tickCount++;
		// if ((tickCount % 12) == 10) time++;
		size = 20;
		time++;

		if (time % (random.nextInt(120) + 30) == 0) {
			xa = (random.nextInt(3) - 1);
			ya = (random.nextInt(3) - 1);
		}

		System.out.println((random.nextInt(3) - 1));

		if (xa != 0 || ya != 0) {
			move(xa, 0);
			move(0, ya);
		}
	}

	public void render(Render render) {
		render.renderRect(x, y, size, size, 0xDB6363);
	}

}
