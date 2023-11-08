import java.io.InvalidObjectException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Board {
    
    private final int BOARD_SIZE = 9;

    private String[] board = new String[BOARD_SIZE];


    public Board(String board) throws InvalidObjectException {
        this(board.split(""));
    }

    public Board(String[] board) throws InvalidObjectException{
        this.board = board;
        if(board.length != BOARD_SIZE)
            throw new InvalidObjectException("Board has not the correct size, correcet size: " + BOARD_SIZE);
        if(getBlankIndex() < 0)
            throw new InvalidObjectException("Board has not a blank position, there must be a blank position to solve the puzzle");
    }

    public String[] getArray(){return this.board;}

    public List<Board> getPossibleBoardsSuccessors() throws InvalidObjectException{

        List<Board> result = new LinkedList<>();
        int blankPosition = getBlankIndex();

        if(blankPosition - 3 >= 0)
            result.add(swap(blankPosition, blankPosition - 3));

        if(blankPosition + 3 <= BOARD_SIZE-1) 
            result.add(swap(blankPosition, blankPosition + 3));

        if((blankPosition+1) % 3 == 2 || (blankPosition) % 3 == 2)
             result.add(swap(blankPosition, blankPosition - 1));

        if((((blankPosition + 1) % 3 == 2) || (blankPosition + 2) % 3 == 2))
            result.add(swap(blankPosition, blankPosition + 1));

        return result;
    }

    private int getBlankIndex(){
        int result = -1;
        for(int i = 0; i < BOARD_SIZE; i++)
            if(this.board[i].equals("0"))
               result = i;
        return result;
    }

    private Board swap(int x, int y) throws InvalidObjectException{
        String[] result = Arrays.copyOf(this.board, BOARD_SIZE);
        result[x] = this.board[y];
        result[y] = this.board[x];
        return new Board(result);
    }

    public boolean equals(Board board){
        boolean result = true;
        for(int i = 0; i < BOARD_SIZE; i++)
            if(!this.board[i].equals(board.getArray()[i]))
                result = false;
        return result;
    }

    @Override
    public String toString(){
        String result = "";
        for(int i = 0; i < this.board.length; i++){
            if(this.board[i].equals("0"))
                result += " ";
            else
                result += this.board[i];
                
            if((i+1) % 3 == 0)
                result += "\n";
        }
        return result;
    }

}
