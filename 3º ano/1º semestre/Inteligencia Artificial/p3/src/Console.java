import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


/**
 * For playing in the console.
 */


public class Console {
    private Board board;
    private Scanner sc = new Scanner(System.in);

    private Console() {
        board = new Board();
    }

    /**
     * Game on
     */
    private void play () {
        while (true) {
            playMove();
            if (board.isGameOver()) {
                printWinner();
                break;
            }
        }
    }

    
    /**
     * Handle the move to be played,(
     */
   
    private void playMove () {
    	int position;
    	
        if (board.getTurn() == Ilayout.ID.X) {
        	position=getHumanMove();
        	//position = XAgent.play(board);
        	board.move(position);
 
        } else {
        	position = XAgent.play(board);
        	board.move(position);
        }
    }
 
    private void printGameStatus () {
        System.out.println("\n" + board + "\n");
        System.out.println(board.getTurn().name() + "'s turn.");
    }

    /**
     * For reading in and interpreting the move that the user types into the console.
     */
    private int getHumanMove() {
        printGameStatus ();
        System.out.print("Index of move: ");

        int move = sc.nextInt();

        if (move < 0 || move >= Ilayout.N* Ilayout.M) {
            System.out.println("\nInvalid move.");
            System.out.println("\nThe index of the move must be between 0 and "
                    + (Ilayout.N * Ilayout.M - 1) + ", inclusive.");
        } else if (!board.isBlank(move)) {
            System.out.println("\nInvalid move.");
            System.out.println("\nThe selected index must be blank.");
        }
        return move;
    }

    
    private void printWinner () {
        Ilayout.ID winner = board.getWinner();

        System.out.println("\n" + board + "\n");

        if (winner == Ilayout.ID.Blank) {
            System.out.println("It's a draw.");
        } else {
            System.out.println("Player " + winner.toString() + " wins!");
        }
    }

    

    public static void main(String[] args)  {
    	    final int repetitions=3;
    	    long times = 0;
    	    for(int i=0; i<repetitions; i++) {
    	    	Console game = new Console();
	    	    long startTime = System.currentTimeMillis();
	    	    game.play();      	
	    	    long totalTime = System.currentTimeMillis() - startTime;
	    	    times += totalTime;
    	    }
    	    System.out.println("Av Time: " + times*1.0f/repetitions+ " milisecs"); 
    }

}
