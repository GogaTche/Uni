import java.util.*;

public class Triple implements Ilayout, Cloneable{

    private int value;
    private double weight;

    public Triple(int triple){
        this.value = triple;
        this.weight = 0;
    }

    public Triple(int triple, double weight){
        this.value = triple;
        this.weight = weight;
    }

    public int getValue(){
        return this.value;
    }
    
    public List<Ilayout> children(){
        List<Ilayout> result = new LinkedList<>();
        result.add(new Triple(this.value + 1, 1));
        result.add(new Triple(this.value - 1, 2));
        result.add(new Triple(this.value * 2, 3));
        return result;
    }

    public boolean isGoal(Ilayout layout){
        return this.equals(layout);
    }

    public double getWeight(){
        return this.weight;
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
}
