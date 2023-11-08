import java.util.*;

interface Ilayout {

    public List<Ilayout> children();

    public boolean isGoal(Ilayout l);

    public double getWeight();
}
