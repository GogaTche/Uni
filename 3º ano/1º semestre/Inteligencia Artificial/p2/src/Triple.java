import java.util.*;

/** Triple is a layout that holds a number.
 * @author Afonso Rio
 * @author Daniel Andrade 
 * @version 1.0 28/10/2023
 * @see Ilayout
 */
public class Triple implements Ilayout, Cloneable{

    private final int ADDITION = 1;
    private final int SUBTRACTION = 2;
    private final int MULTIPLICATION = 3;

    private int value;
    private double weight;

    /** Constructs a {@code Triple} given a string that later is converted
     * into a Integer. That String is now {@code value}, and {@code weight} is set to 0
     * @param value String any Integer
     */
    public Triple(String value){
        this(Integer.parseInt(value));
    }

    /** Constructs a {@code Triple} given an Integer. That Integer is now {@code value}, and {@code weight} is set to {@code 0}.
     * @param value int any Integer
     */
    public Triple(int value){
        this(value, 0);
    }

    /** Constructs a {@code Triple} given an Integer and a Double. That Integer is now {@code value}, and that Double is now{@code weight}
     * @param value int any Integer
     * @param weight double any Double
     */
    private Triple(int triple, double weight){
        this.value = triple;
        this.weight = weight;
    }

    
    /** Returns the value that {@code Triple} holds
     * @return  the value that {@code Triple} holds*
     */
    public int getValue(){
        return this.value;
    }
    
    /** Creates a successors list of this.Triple, there is always 3 successors and each one of them has a specific weight.
     * <p> 1º - {@code Value} + 1  Weight: 1 </p>
     * <p> 2º - {@code Value} - 1  Weight: 2 </p>
     * <p> 3º - {@code Value} * 2  Weight: 3 </p>
     * @return List<ILayout> of the successors 
     */
    @Override
    public List<Ilayout> children(){
        List<Ilayout> result = new LinkedList<>();
        result.add(new Triple(this.value + 1, ADDITION));
        result.add(new Triple(this.value - 1, SUBTRACTION));
        result.add(new Triple(this.value * 2, MULTIPLICATION));
        return result;
    }

    @Override
    public boolean isGoal(Ilayout layout){
        return this.equals(layout);
    }

    @Override
    public double getWeight(){
        return this.weight;
    }


    
    /** Return the distance between an two numbers({@code goal - start}), but if the {@code start} is higher than the {@code goal}
     * then we multiply the distance by the value that {@code SUBTRACTION} holds.
     * @param goal int
     * @return the distance between two numbers
     */
    private int distTo(int goal){
        return goal > this.value ? Math.abs(goal - this.value) * ADDITION : Math.abs(this.value - goal) * SUBTRACTION;
    }

    /** Returns the operation of log2(goal) without using the log method from Math.
     * {@code Goal} is divided by 2 until it reaches 0.
     * @param goal
     * @return log2(goal)
     */
    private int getK(int goal){
        int result = 0;
        while(Math.abs(goal) > Math.abs(this.value)){
            goal /= 2;
            result++;
        }
        return result;
    }


    /** Gets the remainder of dividing {@code goal} by 2 {@code k} times
     * @param goal int
     * @param k int number of times that goal is divided
     * @return remainder of dividing {@code goal} by 2 {@code k} times
     */
    private int getRemainder(int goal, int k, double negativeOrPositive){
        int result = 0;
        double auxGoal = Math.abs(goal);
    
        while(k-- > 0){
            result += auxGoal % 2; 
            auxGoal = auxGoal/2 + (int) (-negativeOrPositive + auxGoal % 2 / 2);
        }

        return result;
    }

    @Override
    public double getHeuristic(Ilayout goal){

        int goalValue = ((Triple) goal).value;
        int k = getK(goalValue);

        double negativeOrPositive = goalValue < this.value ? -0.99 : 0;

        int valueAbove = (int) (((double)goalValue/Math.pow(2, k-1)) + negativeOrPositive);
        int valueBelow = (int) ((goalValue/Math.pow(2, k)) + negativeOrPositive);

        int sumWeight = distTo(goalValue);
        int aboveWeight = distTo(valueAbove) + MULTIPLICATION * (k-1);
        int belowWeight = distTo(valueBelow) + MULTIPLICATION * k;

        return Math.abs(goalValue) <= Math.abs(this.value) ? sumWeight : 
                Math.min(sumWeight, Math.min(aboveWeight, belowWeight) + getRemainder(goalValue, aboveWeight > belowWeight ? k : k-1, negativeOrPositive));
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public boolean equals(Object triple){
        boolean result = false;
        if (triple != null && this.getClass() == triple.getClass()) 
            result = this.value == ((Triple)triple).getValue();
        return result;
    }

    @Override
    public String toString(){
        return "" + this.value;
    }
}