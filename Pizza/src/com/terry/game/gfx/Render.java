package com.terry.game.gfx;

public class Render {

	public int width, height;
	private int[] pixels;
	private int xOffset = 0, yOffset = 0;

	public Render(int width, int height, int[] pixels) {
		this.height = height;
		this.width = width;
		this.pixels = pixels;
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void render(int xp, int yp, Sprite sprite, boolean xFlip, boolean yFlip, boolean fixed) {
		if (!fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < sprite.SIZE; y++) {
			int ya = y + yp;
			int ys = y;
			if (yFlip) ys = (sprite.SIZE - 1) - y;
			for (int x = 0; x < sprite.SIZE; x++) {
				int xa = x + xp;
				int xs = x;
				if (xFlip) xs = (sprite.SIZE - 1) - x;
				if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int col = sprite.pixels[xs + ys * sprite.SIZE];
				if (col != 0xffff00ff) pixels[xa + ya * width] = col;
			}
		}
	}

	public void setOffsets(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

}
