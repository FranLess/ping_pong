package game.fran;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
    public static final int WIDTH = 640, HEIGHT = 400;
    public static final int FPS_SET = 60;

    private Thread gameThread;
    private boolean running = false;
    private Handler handler;

    public Game() {
        synchronized (this) {
            this.handler = new Handler();
            this.addKeyListener(new KeyInput());
            new Window(WIDTH, HEIGHT, "GAME", this);
        }
    }

    public synchronized void start() {
        gameThread = new Thread(this);
        gameThread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            gameThread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        gameThread.start();
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        long lastFrame = System.nanoTime();
        long now = System.nanoTime();

        int frames = 0;
        long lastCheck = System.currentTimeMillis();

        while (running) {

            now = System.nanoTime();
            if (now - lastFrame >= timePerFrame) {
                synchronized (this) {
                    render();
                    tick();
                }
                lastFrame = now;
                frames++;
            }
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                // System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();

    }

    private void tick() {
        handler.tick();
    }

    private synchronized void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        g.dispose();
        bs.show();
    }
}
