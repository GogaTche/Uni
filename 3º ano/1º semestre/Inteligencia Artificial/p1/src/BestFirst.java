import java.util.*;

public class BestFirst{

    protected Queue<State> open;
    private HashMap<Ilayout, State> closed;
    private State current;
    private Ilayout goal;


    static class State{
        private Ilayout layout;
        private State father;
        private double weight;

        State(Ilayout layout){
            this.layout = layout;
            this.father = null;
            this.weight = 0;
        }

        State(Ilayout layout, State father){
            this.layout = layout;
            this.father = father;
            if(father != null)
                this.weight = father.getWeight() + layout.getWeight();
            else
                this.weight = 0;
        }

        public Ilayout getLayout(){return this.layout;}

        public State getFather(){return this.father;}

        public double getWeight(){return this.weight;}

        public List<State> getStatePath(){
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
        public String toString(){
            return "" + ((Triple) this.layout).getValue();
        }

    }

    public BestFirst(String current){
        this.open = new PriorityQueue<>(10, (s1, s2) -> (int) Math.signum(s1.getWeight()-s2.getWeight()));
        this.closed = new HashMap<Ilayout, State>(1, 2);
        this.current = new State(new Triple(Integer.parseInt(current)), null);
        this.goal = new Triple(Integer.parseInt(current) * 3);
    }

    public List<State> getNotClosedSuccessors(State state){
        List<State> result = new LinkedList<>();
        List<Ilayout> children = state.getLayout().children();
        for(Ilayout currentLayout : children)
            if(!this.closed.containsKey(currentLayout))
                result.add(new State(currentLayout, state));
        return result;
    }

    public Iterator<State> solve() {
        open.add(this.current);
        while(!open.isEmpty()){
            this.current = open.remove();
            if(this.current.getLayout().isGoal(this.goal))
                break;
            open.addAll(getNotClosedSuccessors(this.current));
            closed.put(current.getLayout(), current);
        }
        return this.current.getStatePath().iterator();
    }

}
