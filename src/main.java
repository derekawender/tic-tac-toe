import java.util.Scanner;

public class main {
    public static void main (String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        Game game = new Game();
        while (!Game.gameOver(game)) {
            System.out.println("What player are you? (e.g. X or O): ");
            String player = input.next();
            System.out.println("Where would you like to play? (e.g. 1 for top left and 9 for bottom right): ");
            int move = input.nextInt();
            game.play(player, move);

        }
        System.out.println(game.getWinner());
    }
}
