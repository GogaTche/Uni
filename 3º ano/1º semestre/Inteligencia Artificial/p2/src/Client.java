import java.util.*;

public class Client {

    private final static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) throws Exception {
        int start = Integer.parseInt(sc.next());
        int goal = start * 3;

        Iterator<State> iterator = new IterativeDeepeningAStar().solve(new Triple(start), new Triple(goal));

        State current = null;
        while(iterator.hasNext()){
            current = iterator.next();
            System.out.println(current);
        }

        System.out.println("\n" + (int) current.getWeight());

        // System.out.println(new Triple(sc.next()).getHeuristic(new Triple(sc.next())));
    }
}

