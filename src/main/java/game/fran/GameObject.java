package game.fran;

import java.awt.Color;
import java.awt.Graphics;

public abstract class GameObject {
    protected int x, y;
    protected int velX, velY;
    protected int keyUp, keyDown;
    protected int width, height;

    public GameObject() {
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRect(x, y, width, height);
    }

    public synchronized void tick() {
        updateMovement();
        x += velX;
        y += velY;
    };

    public abstract void updateMovement();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
