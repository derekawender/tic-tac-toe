import java.util.Arrays;

public class Game {
    private int[] board;
    private boolean x_won;
    private boolean o_won;

    /**
     * board will be represented as a an array of ints with 1 as X and 2 as O
     */
    public Game() {
        board = new int[9];
        Arrays.fill(board, 0);
        x_won = false;
        o_won = false;

    }
    public static void main (String[] args) {

    }
    public void play(String player, int move) throws Exception {
        if (move < 0 || move > 9) {
            throw new IllegalArgumentException("wrong move");
        }
        if (player.equals("X") ) {

            this.board[move - 1] = 1;
        }
        else if (player.equals("O")) {
            this.board[move - 1] = 2;
        }
        else throw new IllegalArgumentException("input error");
        printBoard();

    }
    private void printBoard() throws Exception {
        for (int i = 0; i < 9; i ++) {
            if (i == 3 || i == 6) {
                System.out.println();
            }
            if (board[i] == 0) {
                System.out.print("* ");
            }
            else if (board[i] == 1) {
                System.out.print("X ");
            }
            else if (board[i] == 2) {
                System.out.print("O ");
            }
            else {
                throw new Exception("invalid board");
            }
        }
        System.out.println();
    }
    public static boolean gameOver(Game game) {

        int currPlayer = 0;
        boolean currRun = false;
        //horizontal
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) {
                if (currRun) {
                    game.setWinner(currPlayer);
                    return true;
                }
                currPlayer = game.board[i];
                if (currPlayer == 0) continue;
                currRun = true;
            }
            if (currRun && game.board[i] != currPlayer) {
                currRun = false;
            }

        }
        if (currRun) {
            game.setWinner(currPlayer);
            return true;
        }
        //vertical
        for (int i = 0; i < 3; i++) {
            if (currRun) {
                game.setWinner(currPlayer);
                return true;
            }
            currPlayer = game.board[i];
            if (currPlayer == 0) continue;
            currRun = true;
            for (int j = i; j < 9; j += 3) {
                if (currRun && game.board[j] != currPlayer) {
                    currRun = false;
                }
            }
        }
        if (currRun) {
            game.setWinner(currPlayer);
            return true;
        }
        //diagonal
        currPlayer = game.board[0];
        if (currPlayer == 0) {
            currPlayer = 3;
        }
        if (game.board[4] == currPlayer && game.board[8] == currPlayer) {
            game.setWinner(currPlayer);
            return true;
        }
        currPlayer = game.board[2];
        if (currPlayer == 0) {
            currPlayer = 3;
        }
        if (game.board[4] == currPlayer && game.board[6] == currPlayer) {
            game.setWinner(currPlayer);
            return true;
        }
        return false;

    }
    private void setWinner(int player) {
        if (player == 1) {
            x_won = true;
        }
        else if (player == 2) {
            o_won = true;
        }
    }
    public String getWinner() {
        if (x_won) {
            return "X is the victor!";
        }
        else if (o_won) {
            return "O is the Victor";
        }
        else return "No winner yet";
    }
}
