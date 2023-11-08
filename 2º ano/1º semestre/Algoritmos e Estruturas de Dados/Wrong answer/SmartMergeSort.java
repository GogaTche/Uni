package aed.sorting;
import java.util.Random;

public class SmartMergeSort extends Sort {

    private static final int MAX_INTERVAL = 64;

    //creates a random generator with a specific seed
    //this is useful for testing methods that are supposed to generate random elements
    //because we can always repeat the same tests by using the same seed
    private static final Random pseudoRandom = new Random(3729);

    public static <T extends Comparable<T>> void insertionSortWithInitialSortedHand(T[] a, int low, int n, int high)
    {
        assert(low < high);
        assert(n > 0);
        assert(low + n <= high);
        for (int i = low + n; i <= high; i++)
            for (int j = i; j > low; j--)
                if (less(a[j], a[j - 1]))
                    exchange(a, j, j - 1);
                else
                    break;
    }

    public static <T extends Comparable<T>> Run getNaturalRun(T[] a, int low, int high)
    {
        assert(low < high);
        Run currentRun = new Run(low, 1);
        for (int i = low + 1; i <= high; i++)
            if (less(a[i - 1], a[i]) || a[i - 1].equals(a[i]))
                currentRun.length += 1;
            else
                break;
        return currentRun;
    }


    public static <T extends Comparable<T>> Run getNaturalOrMakeAscendingRun(T[] a, int low, int high)
    {
        assert(low < high);
        Run result;
        if (a.length == 1)
            result = getNaturalRun(a, low, high);
        else if (less(a[low], a[low + 1]) || a[low].equals(a[low + 1]))
            result = getNaturalRun(a, low, high);
        else
        {
            Run descendingRun = new Run(low, 1);
            for (int i = low + 1; i <= high; i++)
                if (less(a[i], a[i - 1]))
                    descendingRun.length += 1;
                else
                    break;
            insertionSortWithInitialSortedHand(a, low, 1, low + descendingRun.length - 1);
            result = descendingRun;
        }
        return result;
    }

    public static <T extends Comparable<T>> Run getNextRunWithMinimumSize(T[] a, int low, int high, int minRunSize)
    {
        assert(low < high);
        assert(minRunSize > 0);
        Run currentRun = getNaturalOrMakeAscendingRun(a, low, high);
        if (currentRun.length < minRunSize)
        {
            insertionSortWithInitialSortedHand(a, low, currentRun.length, Math.min(low + minRunSize - 1, high));
            currentRun = getNaturalOrMakeAscendingRun(a, low, high);
        }
        return currentRun;
    }

    public static <T extends Comparable<T>> void merge(T[] a, T[] aux, Run leftRun, Run rightRun)
    {
        assert(rightRun.start == leftRun.start + leftRun.length);
        int low = leftRun.start;
        int mid = rightRun.start - 1;
        int high = rightRun.start + rightRun.length - 1;
        MergeInsertionSort.merge(a, aux, low, mid, high);
    }

    public static <T extends Comparable<T>> void mergeCollapse(MergeStack stack, T[] a, T[] aux)
    {
        //TODO: implement
    }


    public static <T extends Comparable<T>> void sort(T[] a)
    {

        //TODO: implement

        //Pro Tip: this is how we can create an aux array of Comparables
        @SuppressWarnings("unchecked")
        T[] aux = (T[]) new Comparable[a.length];
    }


    public static Integer[] generateLargeNaturalRunsExample(Random randomGenerator, int n)
    {
        //todo: implement
        return null;
    }


    public static void main(String[] args)
    {
        Integer[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        Run leftRun = getNaturalOrMakeAscendingRun(a, 0, 4);
        Run rightRun = getNaturalOrMakeAscendingRun(a, 5, 9);
        Integer[] aux = new Integer[a.length];
        merge(a, aux, leftRun, rightRun);
    }
}

