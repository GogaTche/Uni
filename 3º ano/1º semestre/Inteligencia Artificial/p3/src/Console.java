import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


/**
 * For playing in the console.
 */


public class Console {
    private Board board;
    private int rows;
    private int columns;
    private int k;

    private Scanner sc = new Scanner(System.in);

    private Console() throws Exception {

        System.out.print("Rows: ");
        this.rows = Integer.parseInt(sc.next());

        System.out.print("Columns: ");
        this.columns = Integer.parseInt(sc.next());

        System.out.print("Winner's length: ");
        this.k = Integer.parseInt(sc.next());

        board = new Board(rows, columns, k);
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

        if (move < 0 || move >= rows * columns) {
            System.out.println("\nInvalid move.");
            System.out.println("\nThe index of the move must be between 0 and "
                    + (rows * columns - 1) + ", inclusive.");
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

    

    public static void main(String[] args) throws Exception  {
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
