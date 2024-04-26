package game.fran;

import java.awt.event.KeyEvent;

public class ObjectsFactory {
    public static final Player getFirstPlayer() {
        Player player1 = Player.getInstance("1");
        player1.setHeight(Player.PLAYER_HEIGHT);
        player1.setWidth(Player.PLAYER_WIDTH);
        player1.setX(30);
        player1.setY((int) Game.HEIGHT / 2 - Player.PLAYER_HEIGHT);
        player1.setKeyUp(KeyEvent.VK_A);
        player1.setKeyDown(KeyEvent.VK_D);
        return player1;
    }

    public static final Player getSecondPlayer() {
        Player player2 = Player.getInstance("2");
        player2.setHeight(Player.PLAYER_HEIGHT);
        player2.setWidth(Player.PLAYER_WIDTH);
        player2.setX(580);
        player2.setY((int) Game.HEIGHT / 2 - Player.PLAYER_HEIGHT);
        player2.setKeyUp(KeyEvent.VK_J);
        player2.setKeyDown(KeyEvent.VK_L);
        return player2;
    }

    public static final Ball getBall() {
        Ball ball = Ball.getInstance();
        ball.setX((int) Game.WIDTH / 2);
        ball.setY((int) Game.HEIGHT / 2);
        ball.setHeight(Ball.BALL_HEIGHT);
        ball.setWidth(Ball.BALL_WIDTH);
        ball.setVelX(Ball.SPEED);
        ball.setVelY(0);
        return ball;
    }

    public static final AudioPlayer getAudioPlayer() {
        return AudioPlayer.getInstance();
    }
}
