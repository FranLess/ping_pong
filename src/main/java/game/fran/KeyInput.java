package game.fran;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {
    private Player player1;
    private Player player2;

    public KeyInput() {
        this.player1 = ObjectsFactory.getFirstPlayer();
        this.player2 = ObjectsFactory.getSecondPlayer();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        player1.getPressedKeys().add(e.getKeyCode());
        player2.getPressedKeys().add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        player1.getPressedKeys().remove(e.getKeyCode());
        player2.getPressedKeys().remove(e.getKeyCode());
    }

}
