package game.fran;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {

    public Window(int width, int height, String title, Game game) {
        Dimension dimension = new Dimension(Game.WIDTH, Game.HEIGHT + Player.PLAYER_HEIGHT);
        JFrame frame = new JFrame(title);

        frame.setPreferredSize(dimension);
        frame.setMaximumSize(dimension);
        frame.setMinimumSize(dimension);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);

        game.start();

    }
}
