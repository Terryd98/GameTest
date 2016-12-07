package com.terry.game.gfx;

public class Sprite {

	private SpriteSheet sheet;
	private int x, y;
	public final int SIZE;
	public int[] pixels;
	public static int TSIZE = 32;

	public static Sprite grass = new Sprite(TSIZE, 0, 0, SpriteSheet.sheet);
	public static Sprite stone = new Sprite(TSIZE, 1, 0, SpriteSheet.sheet);
	public static Sprite voidSprite = new Sprite(TSIZE, 0x519CB5);

	public static Sprite player_up = new Sprite(TSIZE, 0, 3, SpriteSheet.sheet);
	public static Sprite player_down = new Sprite(TSIZE, 0, 3, SpriteSheet.sheet);
	public static Sprite player_left = new Sprite(TSIZE, 0, 3, SpriteSheet.sheet);
	public static Sprite player_right = new Sprite(TSIZE, 0, 3, SpriteSheet.sheet);

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		this.sheet = sheet;
		this.SIZE = size;
		this.x = x * size;
		this.y = y * size;

		pixels = new int[SIZE * SIZE];
		load();
	}

	public Sprite(int size, int color) {
		this.SIZE = size;
		pixels = new int[SIZE * SIZE];

		getColor(color);
	}

	public void getColor(int color) {
		for (int i = 0; i < SIZE * SIZE; i++) {
			pixels[i] = color;
		}
	}

	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}

}
