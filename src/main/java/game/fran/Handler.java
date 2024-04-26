package game.fran;

import java.awt.Graphics;

public class Handler {
    // private LinkedList<GameObject> objects = new LinkedList<>();
    private Player player1, player2;
    private Ball ball;

    public Handler() {
        this.player1 = ObjectsFactory.getFirstPlayer();
        this.player2 = ObjectsFactory.getSecondPlayer();
        this.ball = ObjectsFactory.getBall();
    }

    public void tick() {
        player1.tick();
        player2.tick();
        ball.tick();
    }

    public void render(Graphics g) {
        player1.render(g);
        player2.render(g);
        ball.render(g);
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }
}
