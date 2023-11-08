import java.io.InvalidObjectException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Puzzle {

    public class State{
        private Board board;
        private State father;
        private int weight = 0;

        State(Board board){
            this.board = board;
            this.father = null;
        }

        State(Board board, State father){
            this.board = board;
            this.father = father;
            if(father != null)
                this.weight = father.getWeight() + 1;
        }

        public int getWeight(){
           return this.weight;
        }

        public Board getBoard(){return this.board;}

        public State getFather(){return this.father;}

        public LinkedList<Board> getBoardList(){
            LinkedList<Board> result = new LinkedList<>();
            result.addFirst(this.board);
            while(father != null){
                result.addFirst(father.getBoard());
                this.father = this.father.getFather();
            }
            return result;
        }

        public boolean equals(State state){
            return this.board.equals((state.getBoard()));
        }

        @Override
        public String toString(){
            String result = "";
            LinkedList<Board> boards = getBoardList();
            for(Board board : boards)
                result += board.toString() + "\n";
            result += getWeight();
            return result;
        }

    }

    private State input;
    private State solution;
    private List<State> closed = new LinkedList<>();
    private Queue<State> open = new LinkedList<>();

    public Puzzle(Board input, Board solution){
        this.input = new State(input);      
        this.solution = new State(solution);
    }

    private List<State> getGetNextSuccessors(State state) throws InvalidObjectException{
        List<State> result = new LinkedList<>();
        List<Board> possibleSuccessors = state.getBoard().getPossibleBoardsSuccessors();
        for(Board possibleSuccsessor : possibleSuccessors)
            if(!isBoardClosed(possibleSuccsessor))
                result.add(new State(possibleSuccsessor, state));
        return result;
    }

    private boolean isBoardClosed(Board board){
        boolean result = false;
        for(State closedState : this.closed)
            if(board.equals(closedState.getBoard())){
                result = true;
                break;
            }
        return result;
    }

    public State solvePuzzle() throws InvalidObjectException{
        State result = null;
        this.open.add(this.input);
        while(!open.isEmpty()){
            State possibleSolution = open.remove();
            if(possibleSolution.equals(this.solution)){
                result = possibleSolution;
                break;
            }
            this.closed.add(possibleSolution);
            this.open.addAll(getGetNextSuccessors(possibleSolution));
        }

        return result;
    }

    @Override
    public String toString(){
        return "input: " + Arrays.toString(input.getBoard().getArray()) + " " + "solution: " + Arrays.toString(solution.getBoard().getArray());
    }
    
}
