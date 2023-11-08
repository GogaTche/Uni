import java.util.*;

/** State representes a "node" that instead of going forward it goes back.
 * @author Afonso Rio, Daniel Andrade 
 * @version 1.0 28/10/2023
 * @see Ilayout
 */
class State{
    
    private Ilayout layout;
    private State father;
    private double weight;
    

    /** Constructs a {@code State} given a layout. {@code Father} is set to {@code null}
     * @param layout Ilayout
     */
    public State(Ilayout layout){
        this.layout = layout;
        this.father = null;
        this.weight = 0;
    }
    
    /** Constructs a {@code State} given a layout and the previous State
     * @param layout Ilayout
     * @param father State
     */
    public State(Ilayout layout, State father){
        this.layout = layout;
        this.father = father;
        if(father != null)
            this.weight = father.getWeight() + layout.getWeight();
        else
            this.weight = 0;
    }
    
    
    /** Returns this.State layout
     * @return this.State layout
     */
    public Ilayout getLayout(){
        return this.layout;
    }
    
    /** Returns this.State father
     * @return this.State father
     */
    public State getFather(){
        return this.father;
    }
    
    /** Returns this.State weight
     * @return this.State weight
     */
    public double getWeight(){
        return this.weight;
    }
    
    /** Returns this.State layout's H
     * @param goal Ilayout
     * @return this.State layout's H
     */
    public double getHeuristic(Ilayout goal){
        return this.layout.getHeuristic(goal);
    }
    
    /** Returns the sum between this.State weight and it's estimated weight to achive {@code Goal}
     * @param goal Ilayout
     * @return the sum between this.State weight and it's estimated weight to achive {@code Goal}
     */
    public double getEvaluation(Ilayout goal){
        return this.getHeuristic(goal) + this.weight;
    }
    
    /** Verifys if this.State's layout the goal
     * @param goal Ilayout
     * @return Bolean if this.State's layout is the goal
     */
    public boolean isGoal(Ilayout goal){
        return this.layout.isGoal(goal);
    }

    /** Returns the path from a this.State until it's father is null
     * @return the path from a this.State until it's father is null
     */
    public List<State> getPath(){
        LinkedList<State> result = new LinkedList<>();
        State currentState = this.father;
        result.addFirst(new State(this.layout, this.father));
    
        while(currentState != null){
            result.addFirst(currentState);
            currentState = currentState.getFather();
        }
        return result;
    }

    @Override
    public boolean equals(Object s){
        return this.layout.equals(((State)s).layout);
    }

    @Override
    public int hashCode() {
        return this.layout.hashCode();
    }
            
    @Override
    public String toString(){
        return this.layout.toString();
    }
}
