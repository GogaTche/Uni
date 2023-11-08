import java.util.*;

/** AStar or A* is a {@link UniformCostSearch} that calculates the smallest path between a layout {@code start} and a layout {@code goal}.
 * <p>The differance between {@code UniformCostSearch} and {@code A*} is its Evaluation function, in A*  we have a method that estimates the
 * cost from a {@code start} to a  {@code goal}, that evaluation function is then used in the priority queue so those who are closest to the goal
 * are prioritised.
 * @version 1.0 28/10/2023
 * @author Afonso Rio
 * @author Daniel Andrade 
 * @see StateSearch
 * @see UniformCostSearch
 */
public class AStar extends UniformCostSearch{

    
    /** Initializes all variables in the this.class and super.class
     * @param start Ilayout
     * @param goal Ilayout
     */
    protected void constructor(Ilayout start, Ilayout goal){
        super.constructor(start, goal);

        this.open = new PriorityQueue<>((s1, s2) -> (int) Math.signum(s1.getEvaluation(goal) - s2.getEvaluation(goal)));
    }

}