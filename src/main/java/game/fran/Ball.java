package game.fran;

import java.awt.Color;
import java.awt.Graphics;

import javax.sound.sampled.AudioInputStream;

public class Ball extends GameObject {
    public static final int BALL_WIDTH = 10, BALL_HEIGHT = 10, SPEED = 6;
    private Player player1, player2;
    private AudioPlayer audioPlayer = ObjectsFactory.getAudioPlayer();

    public synchronized static Ball getInstance() {
        return new Ball();
    }

    private Ball() {
        this.player1 = ObjectsFactory.getFirstPlayer();
        this.player2 = ObjectsFactory.getSecondPlayer();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, BALL_WIDTH, BALL_HEIGHT); // Use fillOval for circular shape
    }

    @Override
    public void updateMovement() {

        if (this.y <= 0) {
            changeYDirection();
            setY(0 + 5);
            AudioPlayer.wall();
        }

        if (this.y >= Game.HEIGHT) {
            changeYDirection();
            setY(Game.HEIGHT - 5);
            AudioPlayer.wall();
        }
        if (this.x <= 0) {
            // TODO reset the game and sum 1 point to player 2
            setX(0);
            changeXDirection();
            // killMovement();
            // return;
        }
        if (this.x >= Game.WIDTH - this.width) {
            // TODO reset the game and sum 1 point to player 1
            setX(Game.WIDTH - this.width - 10);
            changeXDirection();
            // killMovement();
            // return;
        }
        if (isTouchingPlayerVertically(player2)) {
            System.out.println("PLAYER 2 VERTICAL!!");
            setVelY(getAngledVelY(player2));
            setVelX(getAngledVelX(player2));
            changeXDirection();
            AudioPlayer.player2();
        }
        if (isTouchingPlayerVertically(player1)) {
            System.out.println("PLAYER 1 VERTICAL!!");
            setVelY(getAngledVelY(player1));
            setVelX(getAngledVelX(player1));
            changeXDirection();
            AudioPlayer.player1();
        }
        if (isTouchingPlayerHorizontally(player2)) {
            // TODO update the score of player 1 plus 1 point
            System.out.println("PLAYER 2 Horizontal!!");
            changeYDirection();
            // killMovement();
        }
        if (isTouchingPlayerHorizontally(player1)) {
            // TODO update the score of player 2 plus 1 point
            // killMovement();
            changeYDirection();
        }
    }

    private int getAngledVelX(Player player) {
        double ballMid = (getY() + getHeight() / 2);
        double playerRectMid = (player.getY() + Player.PLAYER_HEIGHT / 2);
        double hitPosition = (playerRectMid - ballMid) / (Player.PLAYER_HEIGHT / 2);
        double angledVelX = Math.toDegrees(Math.acos(hitPosition)) * 0.10;
        if (getVelX() < 0)
            angledVelX = -angledVelX;

        if (angledVelX > 8)
            angledVelX = 8;
        if (angledVelX < -8)
            angledVelX = -8;

        return (int) (angledVelX);
    }

    private int getAngledVelY(Player player) {
        double ballMid = (getY() + getHeight() / 2);
        double playerRectMid = (player.getY() + Player.PLAYER_HEIGHT / 2);
        double hitPosition = (playerRectMid - ballMid) / (Player.PLAYER_HEIGHT / 2);
        double angledVelY = -Math.toDegrees(Math.asin(hitPosition)) * 0.10;

        if (angledVelY > 8)
            angledVelY = 8;
        if (angledVelY < -8)
            angledVelY = -8;
        return (int) (angledVelY);
    }

    private boolean isTouchingPlayerVertically(Player player) {
        // Calculate the bounding box of the player
        int playerLeft = player.getX();
        int playerRight = player.getX() + player.getWidth();
        int playerTop = player.getY();
        int playerBottom = player.getY() + player.getHeight();

        // Calculate the bounding box of the ball
        int ballLeft = getX();
        int ballRight = getX() + Ball.BALL_WIDTH;
        int ballTop = getY();
        int ballBottom = getY() + Ball.BALL_HEIGHT;

        // Check for intersection between the bounding boxes
        boolean intersectX = ballRight > playerLeft && ballLeft < playerRight;
        boolean intersectY = ballBottom > playerTop && ballTop < playerBottom;

        return intersectX && intersectY;
    }

    private boolean isTouchingPlayerHorizontally(Player player) {
        // Calculate the bounding box of the player
        int playerLeft = player.getX();
        int playerRight = player.getX() + player.getWidth();
        int playerTop = player.getY();
        int playerBottom = player.getY() + player.getHeight();

        // Calculate the bounding box of the ball
        int ballLeft = getX();
        int ballRight = getX() + Ball.BALL_WIDTH;
        int ballTop = getY();
        int ballBottom = getY() + Ball.BALL_HEIGHT;

        // Check for intersection along the horizontal axis
        boolean intersectX = (ballRight > playerLeft && ballLeft < playerRight) ||
                (ballLeft < playerRight && ballRight > playerLeft);

        // Check if the ball's vertical position is within the player's height range
        boolean withinYRange = ballBottom == playerTop || ballTop == playerBottom;

        return intersectX && withinYRange;
    }

    private void killMovement() {
        setVelX(0);
        setVelY(0);
    }

    private void changeYDirection() {
        this.setVelY(-this.getVelY());
    }

    private void changeXDirection() {
        this.setVelX(-this.getVelX());
    }
}
