import java.util.*;

/** An abstract class that represents every StateSpaceSearch with it's requeired methods
 * @version 1.0 28/10/2023
 * @author Afonso Rio
 * @author Daniel Andrade 
 * @see Ilayout
 * @see State
 */
public abstract class StateSearch {

    protected int formedNodes;
    protected int expandedNodes;

    protected State current;
    protected Ilayout goal;
    
    /** Initializes all variables in the this.class
     * @param start Ilayout
     * @param goal Ilayout
     */
    protected void constructor(Ilayout start, Ilayout goal){
        this.formedNodes = 0;
        this.expandedNodes = 0;

        this.current = new State(start, null);
        this.goal = goal;
    }

    /** Returns the smallest path from {@code start} to {@code goal}
     * @param start Ilayout
     * @param goal Ilayout
     * @returnt the smallest path from {@code start} to {@code goal}
     */
    public Iterator<State> solve(Ilayout start, Ilayout goal){
        return null;
    }
}
