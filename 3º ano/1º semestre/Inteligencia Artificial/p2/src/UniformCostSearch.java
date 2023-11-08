import java.util.*;

/** UniformCostSearch is a {@link StateSearch} that calculates the smallest path between a layout {@code start} and a layout {@code goal}.
 * <p>It uses the method of looking for the next node that has the least weight using a {@link PriorityQueue} so it can go back to nodes that were
 * previously more expensive but now are cheaper, and a {@link HashMap} with {@link States} that we already tested so we don't test them again and use time,
 * the use of this map is crucial because the search in the hash map would be constant and it can be imptoved with a good {@link HashMap#hashCode(Object o)}.
 * @author Afonso Rio
 * @author Daniel Andrade 
 * @version 1.0 28/10/2023
 * @see StateSearch
 */
public class UniformCostSearch extends StateSearch{

    protected Queue<State> open;
    protected HashMap<Ilayout, State> closed;

    /** Initializes all variables in the this.class and super.class
     * @param start Ilayout
     * @param goal Ilayout
     */
    protected void constructor(Ilayout start, Ilayout goal){
        super.constructor(start, goal);

        this.open = new PriorityQueue<>((s1, s2) -> (int) Math.signum(s1.getWeight()-s2.getWeight()));
        this.closed = new HashMap<Ilayout, State>();
        this.current = new State(start);
        this.goal = goal;
    }

    /**Returns a linkedList<State> with the successors without the states that have already been tested
     * @param state State
     * @return a linkedList<State> with the successors without the states that have already been tested
     */
    private List<State> getNotClosedSuccessors(State state){
        List<State> result = new LinkedList<>();
        List<Ilayout> children = state.getLayout().children();
        for(Ilayout currentLayout : children)
            if(!this.closed.containsKey(currentLayout))
                result.add(new State(currentLayout, state));
        return result;
    }
    /** Returns the smallest path from {@code start} to {@code goal} using the UniformCostSearch way
     * @param start Ilayout
     * @param goal Ilayout
     * @returnt the smallest path from {@code start} to {@code goal} using the UniformCostSearch way
     */
    public Iterator<State> solve(Ilayout start, Ilayout goal) {
        this.constructor(start, goal);
        List<State> successors =  new LinkedList<>();

        open.add(this.current);
        while(!open.isEmpty()){
            this.current = open.remove();
            
            if(this.current.isGoal(this.goal))
                break;

            successors = getNotClosedSuccessors(this.current);
            open.addAll(successors);
            closed.put(current.getLayout(), current);

            this.formedNodes += successors.size();
            this.expandedNodes++;
        }
        return this.current.getPath().iterator();
    }

}
