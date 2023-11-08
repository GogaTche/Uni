import java.io.InvalidObjectException;
import java.util.Scanner;

public class Cliente{

    private final static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws InvalidObjectException {
        while(true){
            Puzzle p = new Puzzle(new Board(sc.next()), new Board(sc.next()));
            Puzzle.State x = p.solvePuzzle();
            System.out.println(x.toString());  
            break;
        }  
    }
}