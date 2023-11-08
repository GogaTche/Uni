import java.util.List;



/* A demo reactive agent that plays randomly */

public class XAgent {
	/**
     * return a valid random move.
     * @param board         the board to play on
     */
	static int play(Ilayout board) {
        int[] moves = new int[board.getAvailableMoves().size()];
        int index = 0;
        for (Integer item : board.getAvailableMoves()) {
            moves[index++] = item;
        }
        int randomMove = moves[new java.util.Random().nextInt(moves.length)];
        return randomMove;
    }
}
