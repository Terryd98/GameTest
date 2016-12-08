package com.terry.main.gfx;

import com.terry.main.entity.mob.Player;

public class Render {

	public int width, height;
	public int[] pixels;

	public Render(int width, int height, int[] pixels) {
		this.width = width;
		this.height = height;
		this.pixels = pixels;

	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void renderRect(int x, int y, int x1, int y1, int color) {
		for (int yt = 0; yt < y1; yt++) {
			for (int xt = 0; xt < x1; xt++) {
				int xo = (x + xt);
				int yo = (y + yt);
				if (xo < -Player.size || xo >= width || yo < 0 || yo >= height) break;
				if (xo < 0) xo = 0;
				pixels[xo + yo * width] = color;
			}
		}
	}

	float x = 0;
	float y = 0;

	public void renderLine(int x0, int y0, int x1, int y1) {
		int steps;
		int dx = x1 - x0;
		int dy = y1 - y0;
		if (Math.abs(dx) > Math.abs(dy)) {
			steps = Math.abs(dx);
		} else {
			steps = Math.abs(dy);
		}
		float xI = dx / (float) steps;
		float yI = dy / (float) steps;

		for (int v = 0; v < steps; v++) {
			x = (x + xI);
			y = (y + yI);
			//pixels[] = 0xff00ff;

		}

	}

}
