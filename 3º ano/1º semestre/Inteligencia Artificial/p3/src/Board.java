import java.util.*;
// import java.util.ArrayList;

/**
 * Represents a board.
 */
public class Board implements Ilayout,Cloneable{

    
    private ID[][] board;
    private ID playersTurn;
    private ID winner;
    private HashSet<Integer> movesAvailable;

    private int moveCount;
    private boolean gameOver;

    
    Board() {
        board = new ID[N][M];
        movesAvailable = new HashSet<>();
        reset();
    }

    /**
     * Set the cells to be blank and load the available moves (all the moves are
     * available at the start of the game).
     */
    private void initialize () {
        for (int row = 0; row < N; row++)
            for (int col = 0; col < M; col++) {
                board[row][col] = ID.Blank;
            }
        movesAvailable.clear();

        for (int i = 0; i < N*M; i++) {
            movesAvailable.add(i);
        }
    }

    /**
     * Restart the game with a new blank board.
     */
    public void reset() {
        moveCount = 0;
        gameOver = false;
        playersTurn = ID.X;
        winner = ID.Blank;
        initialize();
    }

    /**
     * Places an X or an O on the specified index depending on whose turn it is.
     * @param index     position starts in 0 and increases from left to right and from top to bottom
     * @return          true if the move has not already been played
     */
    public boolean move (int index) {
        return move(index% M, index/M);
    }

    /**
     * Places an X or an O on the specified location depending on who turn it is.
     * @param x         the x coordinate of the location
     * @param y         the y coordinate of the location
     * @return          true if the move has not already been played
     */
   public boolean move (int x, int y) {

        if (gameOver) {
            throw new IllegalStateException("Game over. No more moves can be played.");
        }

        if (board[y][x] == ID.Blank) {
            board[y][x] = playersTurn;
        } else {
            return false;
        }

        moveCount++;
        movesAvailable.remove(y * N + x);

        // The game is a draw.
        if (moveCount == N * M) {
            winner = ID.Blank;
            gameOver = true;
        }

        // Check for a winner.
       
        /** YOUR CODE HERE */


        playersTurn = (playersTurn == ID.X) ? ID.O : ID.X;
        return true;
    }

    private boolean checkLines(){
        for(int i = 0; i < N*M; i++){
            if(i + K - 1 < M){
                for(int j = i; j < i + K; j++){
                    if(!board[j/M][j%M].name().equals(getTurn().name()))
                        break;
                    if(j == i+K-1)
                        return true;
                }
            }
            if(i + K*(N-1) < M*N){
                for(int j = i; j <= i + K*(N-1); j+=N){
                    if(!board[j/M][j%M].name().equals(getTurn().name()))
                        break;
                    if(j == i + K*(N-1))
                        return true;
                }
            }
            if(i + K*M - 1 < M*N){
                for(int j = i; j < i + K*N; j+=N+1){
                    if(!board[j/M][j%M].name().equals(getTurn().name()))
                        break;
                    if(j == i + K*N - 1)
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * Check to see if the game is over (if there is a winner or a draw).
     * @return          true if the game is over
     */
    public boolean isGameOver () {
        gameOver = checkLines();
        if(checkLines() == true)
            winner = getTurn();
        return gameOver;
    }

    

    /**
     * Check to see who's turn it is.
     * @return          the player who's turn it is
     */
    public ID getTurn () {
        return playersTurn;
    }

    /**
     * @return          the player who won (or Blank if the game is a draw)
     */
    public ID getWinner () {
        if (!gameOver) {
            throw new IllegalStateException("Not over yet!");
        }
        return winner;
    }

    /**
     * Get the indexes of all the positions on the board that are empty.
     * @return          the empty cells
     */
    public HashSet<Integer> getAvailableMoves () {
        return movesAvailable;
    }

    

    /**
     * @return  an deep copy of the board
     */
    public Object clone () {
    	try {
	        Board b = (Board) super.clone();
	
	        b.board = new ID[N][M];
	        for (int i = 0; i < N; i++)
	        	for (int j=0; i<M; j++)
	        		b.board[i][j] = this.board[i][j];
	       
	        b.playersTurn       = this.playersTurn;
	        b.winner            = this.winner;
	        b.movesAvailable    = new HashSet<Integer>();
	        b.movesAvailable.addAll(this.movesAvailable);
	        b.moveCount         = this.moveCount;
	        b.gameOver          = this.gameOver;
	        return b;
    	}
    	catch (Exception e) {
    		throw new InternalError();
    	}
    }

    @Override
    public String toString () {
        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {

                if (board[y][x] == ID.Blank) {
                    sb.append("-");
                } else {
                    sb.append(board[y][x].name());
                }
                sb.append(" ");

            }
            if (y != N -1) {
                sb.append("\n");
            }
        }
        return new String(sb);
    }  
        
     /**
         * 
         * @return the children of the receiver.
     */   
     public List<Ilayout> children() {
 		return null;
        // YOUR CODE HERE
     }
   

	@Override
	public boolean equals(Object other) {     
		if (other == this) return true;
		if (other == null) return false;
		if (getClass() != other.getClass()) return false;
		Board that = (Board) other;
		
		for (int y = 0; y < N; y++) 
            for (int x = 0; x < M; x++) 
            	if (board[x][y] != that.board[x][y]) return false;
        return true;
	}
		
	@Override
	public int hashCode() {
		return board.hashCode();	
	}
		
	public boolean isBlank (int index) {
		int x=index/M;
		int y=index%M;
        return (board[x][y] == ID.Blank);
	}
}
