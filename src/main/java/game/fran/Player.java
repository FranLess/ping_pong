package game.fran;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Player extends GameObject {
    private static final Map<String, Player> instances = new HashMap<>();
    public static final int PLAYER_WIDTH = 50, PLAYER_HEIGHT = 200;
    protected Set<Integer> pressedKeys = new HashSet<>();

    private Player() {
    }

    @Override
    public void updateMovement() {
        if (this.y <= 0)
            this.setY(0);
        if (this.y + this.height >= Game.HEIGHT)
            this.setY(Game.HEIGHT - this.height);
        if (pressedKeys.contains(getKeyUp()))
            this.setVelY(-5);
        else if (pressedKeys.contains(getKeyDown()))
            this.setVelY(5);
        else
            this.setVelY(0);
    }

    public static Player getInstance(String key) {
        if (!instances.containsKey(key))
            instances.put(key, new Player());
        return instances.get(key);
    }

    public Set<Integer> getPressedKeys() {
        return pressedKeys;
    }
    public int getKeyUp() {
        return this.keyUp;
    }

    public int getKeyDown() {
        return this.keyDown;
    }

    public void setKeyUp(int keyUp) {
        this.keyUp = keyUp;
    }

    public void setKeyDown(int keyDown) {
        this.keyDown = keyDown;
    }
}
