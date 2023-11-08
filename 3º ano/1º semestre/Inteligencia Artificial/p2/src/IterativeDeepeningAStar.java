import java.util.*;

/** IterativeDeepeningAStar or IterativeDeepeningA* is a {@link StateSearch} that calculates the smallest path between a layout {@code start} and a layout {@code goal}.
 * <p>It uses the method of looking for the next node that has the least weight using a {@link Stack} and a Heuristic function so it can choose
 * the best nodes to reach the {@code goal} faster. The concept is to expand a node and to choose the node that does not exceed the {@link State#getEvaluation(Ilayout)}
 * from the first node, but if we dont find the goal and the stack goes empty we go to the beginningbut now the limit is the smallest evaluation from the nodes that 
 * exeeded the past limit and we repeat this process until we find the {@code goal}.
 * @author Afonso Rio
 * @author Daniel Andrade 
 * @version 1.0 28/10/2023
 * @see StateSearch
 */
public class IterativeDeepeningAStar extends StateSearch{

    protected Stack<State> open;
    protected double limit;

    /** Initializes all variables in the this.class and super.class
     * @param start Ilayout
     * @param goal Ilayout
     */
    protected void constructor(Ilayout start, Ilayout goal){
        super.constructor(start, goal);

        this.open = new Stack<State>();
        this.limit = this.current.getEvaluation(this.goal);
    }

    /**Returns a linkedList<State> with the successors of a {@code State}
     * @param state State
     * @return a linkedList<State> with the successors of a {@code State}
     */
    private List<State> getSuccessors(State state){
        List<State> result = new LinkedList<>();
        List<Ilayout> children = state.getLayout().children();
        for(Ilayout currentLayout : children)
                result.add(new State(currentLayout, state));
        return result;
    }

    /** Returns the smallest path from {@code start} to {@code goal} using the IterativeDeepeningAStar way
     * @return Returns the smallest path from {@code start} to {@code goal} using the IterativeDeepeningAStar way
     */
    private Iterator<State> depthFirstSearchWithCutoff(){

        List<State> successors = new LinkedList<>();

        double cutoff = this.limit;
        this.limit = Double.POSITIVE_INFINITY;

        open.push(this.current);
        while(!open.isEmpty()){

            this.current = open.pop();
            
            if(this.current.isGoal(this.goal))
                return this.current.getPath().iterator();

            if(this.current.getEvaluation(this.goal) <= cutoff){

                successors = getSuccessors(this.current);
                open.addAll(successors);

                this.formedNodes += successors.size();
                this.expandedNodes++;
            }
            else
                this.limit = Math.min(limit, this.current.getEvaluation(this.goal));
        }

        return null;
    }


    /** Returns the smallest path from {@code start} to {@code goal} using the IterativeDeepeningAStar way
     * @return Returns the smallest path from {@code start} to {@code goal} using the IterativeDeepeningAStar way
     */
    @Override
    public Iterator<State> solve(Ilayout start, Ilayout goal) {
        //Constructor
        this.constructor(start, goal);

        Iterator<State> result = null;
        while(result == null)
            result = depthFirstSearchWithCutoff();
        return result;
    }

}