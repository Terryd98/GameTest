package com.terry.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.terry.main.entity.input.Input;
import com.terry.main.entity.mob.Player;
import com.terry.main.entity.mob.TestChaser;
import com.terry.main.entity.mob.TestMob;
import com.terry.main.gfx.Render;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 800, HEIGHT = WIDTH / 16 * 9;
	public static final int SCALE = 1;
	private Thread thread;
	private JFrame frame;
	private Input input;
	private Player player;
	private Render render;
	private TestMob tMob;
	private TestChaser tChaser;
	public boolean running = false;

	BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	public int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public Game() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		render = new Render(WIDTH, HEIGHT, pixels);
		input = new Input();
		addKeyListener(input);
		player = new Player(20, 20, input);
		// tMob = new TestMob(200, 70);
		// tChaser = new TestChaser(500, 70);
	}

	public void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void init() {
		frame = new JFrame();
		frame.setTitle("Untitled");
		frame.setResizable(false);
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void run() {
		requestFocus();
		long timer = System.currentTimeMillis();
		long lastTime = System.nanoTime();
		double delta = 0;
		int ups = 0;
		int frames = 0;
		init();
		while (running) {
			long now = System.nanoTime();
			double ns = 1000000000.0 / 60.0;
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				ups++;
				update();
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				System.out.println("Ups: " + ups + " | " + "FPS: " + frames);
				frame.setTitle("Untitled  - " + "Ups: " + ups + " | " + "FPS: " + frames);
				ups = 0;
				frames = 0;
				timer += 1000;
			}
		}
		stop();
	}

	public void update() {
		input.update();
		player.update();
		// tMob.update();
		// tChaser.update();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		render.clear();
		player.render(render);

		// tMob.render(render);
		// tChaser.render(render);

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);

		g.dispose();
		bs.show();

	}

	public static void main(String[] args) {
		new Game().start();
	}

}
