package game;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;

import game.fran.Game;

public class Main {
    public static void main(String[] args) {
        // new Game();
        // IntStream.range(0, 5).forEach(System.out::println);
        Consumer<String> function = string -> System.out.println(string);
        function.accept("HOla");

        System.out.println();
    }
}