import java.util.*;

/** StateSearchStudy provides us with a method that allow us to study the time complexity and memory usage and other things of a {@code StateSearch}
 * @version 1.0 28/10/2023
 * @author Afonso Rio
 * @author Daniel Andrade 
 * @see StateSearch
 * @see State
 * @see Ilayout
 */
public class StateSearchStudy extends StateSearch{

    private String SEPARATOR;

    protected LinkedList<LinkedList<Double>> outcomesData;
    private LinkedList<String> outcomes;
    private Ilayout start;
    private Ilayout goal;

    /**See {@link StateSearchStudy#study(List, Ilayout, Ilayout, int, String)}
     * @param stateSearch StateSearch
     * @param start Ilayout
     * @param goal Ilayout
     * @param numberOfTests int
     * @param separator String
     */
    public StateSearchStudy(List<StateSearch> stateSearchs, Ilayout start, Ilayout goal, int numberOfTests, String separator){
        this.study(stateSearchs, start, goal, numberOfTests, separator);
    }


    
    /** Returns {@code getOutcomesData}
     * @return getOutcomesData
     */
    public LinkedList<LinkedList<Double>> getOutcomesData(){
        return this.outcomesData;
    }

    /**Returns an double[] with the time complexity and the memory usage of a {@code StateSearch}
     * @param stateSearch StateSearch
     * @return an double[] with the time complexity and the memory usage of a {@code StateSearch}
     */
    private double[] getTimeAndMemory(StateSearch stateSearch){
        Runtime rt = Runtime.getRuntime();

        long startTimer = 0;
        long endTimer = 0;
        long startMemory = 0;
        long endMemory = 0;

        double time = 0;
        double memoryUsed = 0;

        System.gc();
  
        startMemory = rt.totalMemory() - rt.freeMemory();
        startTimer = System.nanoTime();

        stateSearch.solve(this.start, this.goal);

        endTimer = System.nanoTime();
        endMemory = rt.totalMemory() - rt.freeMemory();

        time = (double) (endTimer - startTimer) / 1000000;
        memoryUsed = (double)(endMemory - startMemory) / 1000000;

        return new double[]{time, memoryUsed};
    }

    /**Returns an double[] with the time complexity and the memory usage of a {@code StateSearch} that is tested {@code N} times.
     * @param stateSearch StateSearch
     * @param numberOfTests int
     * @return an double[] with the time complexity and the memory usage of a {@code StateSearch}
     */
    private double[] getTimeAndMemory(StateSearch stateSearch, int numberOfTests){
        double time = 0;
        double memoryUsed = 0;
        double[] aux = new double[2];

        for(int i = 0; i < numberOfTests; i++){
            aux = getTimeAndMemory(stateSearch);
            time += aux[0];
            memoryUsed += aux[1];
        }  

        return new double[]{time / numberOfTests, memoryUsed / numberOfTests};
    }

    /**Returns how many nodes the smallest path has from a {@code StateSearch}.
     * @param stateSearch StateSearch
     * @return how many nodes the smallest path has from a {@code StateSearch}.
     */
    private int getPathLength(StateSearch stateSearch){
        Iterator<State> iterator = stateSearch.solve(this.start, this.goal);
        int result = -1;
        while(iterator.hasNext()){
            iterator.next();
            result++;
        }
        return result;
    }

    /**Returns best weight we can reach from {@start} to {@goal}.
     * @param stateSearch StateSearch
     * @return best weight we can reach from {@start} to {@goal}.
     */
    private int getGoalWeight(StateSearch stateSearch){
        Iterator<State> iterator = stateSearch.solve(this.start, this.goal);
        State result = null;
        while(iterator.hasNext()){
            result = iterator.next();
        }
        return (int) result.getWeight();
    }


    
    /**Returns a string With the representation from specific data given
     * @param stateSearch StateSearch
     * @param className String
     * @param time double
     * @param memoryUsed double
     * @param formedNodes int
     * @param expandedNodes int
     * @param pathLength int 
     * @param weight int 
     * @Return a string With the representation from specific data given
     */
    private String buildOutcome(String className, double time, double memoryUsed, int formedNodes, int expandedNodes, int pathLength, int weight){
        return SEPARATOR + "\n[" + 
        className + "]\n" + 
        "Weight: " + weight + "\n" +
        "Time: " + time + " ms\n" +
        "Memory Used: " + memoryUsed + " MegaBytes\n" +
        "Formed Nodes: " + formedNodes + " Nodes\n" +
        "Expanded Nodes: " + expandedNodes + " Nodes\n" +
        "Penetrance: " + (double) pathLength / (double) (formedNodes > 0 ? formedNodes : 1) + "\n" +
        "Path Length: " + pathLength + " Nodes\n" + 
        // "Layout: " + this.goal.getClass().getName() + "\n" + 
        SEPARATOR; 
    }

    /**Prints all the outcomes
     */
    public void printOutcomes(){
        for(String outcome : this.outcomes)
            System.out.println(outcome);
    }

    @Override
    public String toString(){
        String result = "Start: " + this.start.toString() + "\n" +
        "Goal: " + this.goal.toString() + "\n";
        for(String outcome : this.outcomes)
            result += outcome;
        return result;
    }

    /** Returns a string with all the following tests:
     * <p>Time,
     * Memory,
     * Formed Nodes,
     * Expanded Nodes,
     * Final Weight from {@code start} to {@code goal},
     * Path Length.<p>
     * @param stateSearchs List<StateSearch>
     * @param start Ilayout
     * @param goal Ilayout
     * @param numberOfTests int
     * @param separator String
     * @return String
     */
    public void study(List<StateSearch> stateSearchs, Ilayout start, Ilayout goal, int numberOfTests, String separator){
        this.SEPARATOR = separator;
        this.outcomes = new LinkedList<>();
        this.outcomesData = new LinkedList<>();
        this.start = start;
        this.goal = goal;

        for(StateSearch stateSearch : stateSearchs){

            LinkedList<Double> data = new LinkedList<>();

            String className = stateSearch.getClass().getName();
            double[] getTimeAndMemory = getTimeAndMemory(stateSearch, numberOfTests);
            int formedNodes = stateSearch.formedNodes;
            int expandedNodes = stateSearch.expandedNodes;
            int weight = getGoalWeight(stateSearch);
            int pathLength = getPathLength(stateSearch);

            data.add(getTimeAndMemory[0]);
            data.add(getTimeAndMemory[1]);
            data.add((double)formedNodes);
            data.add((double)expandedNodes);
            data.add((double)pathLength);

            this.outcomes.add(buildOutcome(className, getTimeAndMemory[0], getTimeAndMemory[1], formedNodes, expandedNodes, pathLength, weight));
            this.outcomesData.add(data);
        }
    }
}     