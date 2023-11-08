import java.util.*;

/**This interface represents a layout that has the methods that are required to use the class {@link StateSearch}.
 * @author Afonso Rio
 * @author Daniel Andrade 
 * @version 1.0 28/10/2023
 */
interface Ilayout {

    /** Creates a successors list of a layout.
     * @return List<ILayout> of the successors 
     */
    public List<Ilayout> children();

    /** Verify if a {@code this.layout} equals {@code that.layout}
     * @return Boolean
     */
    public boolean isGoal(Ilayout layout);

    /** Gets {@code this.layout} Weight
     * @return {@code this.layout} Weight
     */
    public double getWeight();

    /** Gets the estimated weight to "go" from {@code this.layout} to {@code goal}
     * @param goal Ilayout
     * @return the estimated weight to "go" from {@code this.layout} to {@code goal}
     */
    public double getHeuristic(Ilayout goal);

    @Override
    public boolean equals(Object layout);

    @Override
    public String toString();
}
