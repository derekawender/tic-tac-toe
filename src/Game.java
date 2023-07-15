import java.util.Arrays;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Game implements ActionListener{
    private int[] board;
    private boolean x_won;
    private boolean o_won;
    private boolean player1_turn;
    private ArrayList<Integer> winning;


    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];

    /**
     * board will be represented as a an array of ints with 1 as X and 2 as O
     */
     Game() throws InterruptedException {
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setSize(800, 800);
         frame.getContentPane().setBackground(new Color(50, 50, 100));
         frame.setVisible(true);

         textfield.setBackground(new Color(36, 36, 36));
         textfield.setForeground(new Color(36, 255, 0));
         textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
         textfield.setHorizontalAlignment(JLabel.CENTER);
         textfield.setText("Tic-Tac-Toe");
         textfield.setOpaque(true);

         title_panel.setLayout(new BorderLayout());
         title_panel.setBounds(0,0,800, 100);

         button_panel.setLayout(new GridLayout(3, 3));
         button_panel.setBackground(new Color(150, 150, 150));

         title_panel.add(textfield);
         frame.add(title_panel, BorderLayout.NORTH);
         frame.add(button_panel);

         for (int i = 0; i < 9; i++) {
             buttons[i] = new JButton();
             button_panel.add(buttons[i]);
             buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
             buttons[i].setFocusable(false);
             buttons[i].addActionListener(this);

         }
         firstTurn();


         board = new int[9];
         Arrays.fill(board, 0);
         x_won = false;
         o_won = false;
         winning = new ArrayList<>();

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

        String currPlayer = "";
        boolean currRun = false;
        //horizontal
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) {
                if (currRun) {
                    game.setWinner(currPlayer);
                    return true;
                }
                game.winning.clear();
                currPlayer = game.buttons[i].getText();
                if (currPlayer.equals("")) continue;
                currRun = true;
            }
            game.winning.add(i);
            if (currRun && !currPlayer.equals(game.buttons[i].getText())) {

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
            game.winning.clear();
            currPlayer = (game.buttons[i].getText());
            if (currPlayer.equals("")) continue;
            currRun = true;
            for (int j = i; j < 9; j += 3) {
                game.winning.add(j);
                if (currRun && !currPlayer.equals(game.buttons[j].getText())) {
                    currRun = false;
                }
            }
        }
        if (currRun) {
            game.setWinner(currPlayer);
            return true;
        }
        //diagonal
        currPlayer = (game.buttons[0].getText());
        if (currPlayer.equals("")) {
            currPlayer = "R";
        }
        game.winning.clear();
        if (game.buttons[4].getText().equals(currPlayer) && game.buttons[8].getText().equals(currPlayer)) {
            game.winning.add(0);
            game.winning.add(4);
            game.winning.add(8);
            game.setWinner(currPlayer);
            return true;
        }
        currPlayer = game.buttons[2].getText();
        if (currPlayer.equals("")) {
            currPlayer = "R";
        }
        game.winning.clear();
        if (game.buttons[4].getText().equals(currPlayer) && game.buttons[6].getText().equals(currPlayer)) {
            game.winning.add(2);
            game.winning.add(4);
            game.winning.add(6);
            game.setWinner(currPlayer);
            return true;
        }
        return false;

    }
    private void setWinner(String player) {
        if (player.equals("X")) {
            x_won = true;
        }
        else if (player.equals("O")) {
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

    @Override
    public void actionPerformed(ActionEvent e) {

         for (int i = 0; i < 9; i++) {
             if(e.getSource()==buttons[i]) {
                 if(player1_turn) {
                     if (buttons[i].getText()=="") {
                         buttons[i].setForeground(Color.red);
                         buttons[i].setText("X");
                         player1_turn = false;
                         textfield.setText("O turn");
                     }
                 }
                 else {
                     if (buttons[i].getText()=="") {
                         buttons[i].setForeground(Color.blue);
                         buttons[i].setText("O");
                         player1_turn = true;
                         textfield.setText("X turn");
                     }

                 }
                 if(gameOver(this)) {
                     if (x_won) {
                         winGUI("X");
                     }
                     else {
                         winGUI("O");
                     }
                 }
             }

         }

    }

    public void firstTurn() throws InterruptedException {
         try {
             Thread.sleep(2000);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
         if (random.nextInt(2) == 0) {
             player1_turn = true;
             textfield.setText("X turn");
         }
         else {
             player1_turn = false;
             textfield.setText("O turn");
         }

    }
    public void winGUI(String player) {
         for (Integer elem: winning) {
             System.out.println(elem);
         }
         buttons[this.winning.get(0)].setBackground(Color.GREEN);
         buttons[this.winning.get(1)].setBackground(Color.GREEN);
         buttons[this.winning.get(2)].setBackground(Color.GREEN);

         for (int i = 0; i < 9; i++) {
             buttons[i].setEnabled(false);
             textfield.setText(player + " wins!");
         }
    }

}
