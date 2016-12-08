package com.terry.game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.terry.game.entity.mob.Player;
import com.terry.game.gfx.Render;
import com.terry.game.input.Keyboard;
import com.terry.game.level.Level;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	private int width = 350, height = width/16*10;
	private int scale = 2;
	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private Level level;
	private Render render;
	private String title = "Untitled";
	private boolean running = false;

	public BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	public int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		render = new Render(width, height, pixels);
		frame = new JFrame();
		level = new Level(8, 8);
		key = new Keyboard();
		addKeyListener(key);
		level.add(new Player(4, 4, key, level));
	}

	private void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	private void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0;
		final double ns = 1000000000.0 / 60.0;
		int frames = 0;
		int ups = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta > 1) {
				ups++;
				tick();
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(ups + " ticks, " + frames + " FPS");
				frame.setTitle(title + " | " + " ticks, " + frames + " FPS");
				frames = 0;
				ups = 0;
			}
		}
		stop();
	}

	private void tick() {
		key.tick();
		level.tick();

	}

	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		render.clear();

		int xScroll = level.player.x - render.width / 2;
		int yScroll = level.player.y - render.height / 2;
		level.render(xScroll, yScroll, render);

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}

	public static void main(String args[]) {
		Game game = new Game();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setLocationRelativeTo(null);
		game.frame.setResizable(false);
		game.frame.setVisible(true);
		game.start();
	}
}
