package aed.sorting;
import aed.utils.TemporalAnalysisUtils;

import java.util.Random;

public class SmartMergeSort extends Sort
{

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
            if (!less(a[i], a[i - 1]))
                currentRun.length++;
            else
                break;
        return currentRun;
    }

    private static <T extends Comparable<T>> void invertArray (T[] a, int low, int high)
    {
        while (low < high)
            exchange(a, low++, high--);
    }

    public static <T extends Comparable<T>> Run getNaturalOrMakeAscendingRun(T[] a, int low, int high)
    {
        assert(low < high);
        Run currentRun;
        if (low >= a.length - 1)
            currentRun = new Run(low, 1);
        else if (!less(a[low + 1], a[low]))
            currentRun = getNaturalRun(a, low, high);
        else
        {
            currentRun = new Run(low, 1);
            for (int i = low + 1; i <= high; i++)
                if (less(a[i], a[i - 1]))
                    currentRun.length++;
                else
                    break;
            invertArray(a, low, low + currentRun.length - 1);
        }
        return currentRun;
    }

    public static <T extends Comparable<T>> Run getNextRunWithMinimumSize(T[] a, int low, int high, int minRunSize)
    {
        assert(low < high);
        assert(minRunSize > 0);
        Run currentRun = getNaturalOrMakeAscendingRun(a, low, high);
        if (currentRun.length < minRunSize)
        {
            insertionSortWithInitialSortedHand(a, low, currentRun.length, Math.min(low + minRunSize - 1, high));
            currentRun.length = Math.min(minRunSize, high - low + 1);
        }
        return currentRun;
    }

    public static <T extends Comparable<T>> void merge(T[] a, T[] aux, Run leftRun, Run rightRun)
    {
        assert(rightRun.start == leftRun.start + leftRun.length);
        MergeInsertionSort.merge(a, aux, leftRun.start, rightRun.start - 1, rightRun.start + rightRun.length - 1);
    }

    public static <T extends Comparable<T>> void mergeCollapse(MergeStack stack, T[] a, T[] aux)
    {
        int height = stack.height();
        while (height > 1)
        {
            if (height >= 3 && stack.get(1).length > stack.get(3).length)
            {
                merge(a, aux, stack.get(3), stack.get(2));
                stack.collapse(2);
            }
            else if (height >= 2 && stack.get(1).length >= stack.get(2).length)
            {
                merge(a, aux, stack.get(2), stack.get(1));
                stack.collapse(1);
            }
            else if (height >= 3 && stack.get(1).length + stack.get(2).length >= stack.get(3).length)
            {
                merge(a, aux, stack.get(2), stack.get(1));
                stack.collapse(1);
            }
            else if (height >= 4 && stack.get(2).length + stack.get(3).length >= stack.get(4).length)
            {
                merge(a, aux, stack.get(2), stack.get(1));
                stack.collapse(1);
            }
            else
                break;
            height--;
        }
    }

    public static <T extends Comparable<T>> void sort(T[] a)
    {
        int size = a.length;
        if (size < 64)
            MergeInsertionSort.insertionSort(a, 0, size - 1);
        else
        {
            @SuppressWarnings("unchecked")
            T[] aux = (T[]) new Comparable[size];
            MergeStack stack = new MergeStack();
            int runSize = MergeInsertionSort.determineRunSize(size);
            int currentRunLength;
            for (int i = 0; i < size; i += currentRunLength)
            {
                Run currentRun = getNextRunWithMinimumSize(a, i, size - 1, runSize);
                currentRunLength = currentRun.length;
                stack.push(currentRun);
                mergeCollapse(stack, a, aux);
            }
            int height = stack.height();
            while (height > 1)
            {
                merge(a, aux, stack.get(2), stack.get(1));
                stack.collapse(1);
                height--;
            }
        }
    }

    public static Integer[] generateLargeNaturalRunsExample(Random randomGenerator, int n)
    {
        Integer[] a = new Integer[n];
        for (int i = 1; i <= n; i++)
            if (i % 1000 == 0)
                a[i - 1] = 0;
            else
                a[i - 1] = i;
        return a;
    }

    private static double getExecutionTimeSmartMergeSort(Integer[] example)
    {
        double initialTime;
        double executionTime;
        initialTime = (double) System.nanoTime();
        sort(example);
        executionTime = (double) System.nanoTime() - initialTime;
        return executionTime;
    }

    private static final int RANDOM_ARRAY = 1;
    private static final int MOSTLY_SORTED_ARRAY = 2;
    private static final int ALMOST_SORTED_ARRAY = 3;
    private static final int BASICALLY_ALL_SORTED_ARRAY = 4;
    private static final int ASCENDING_ARRAY = 5;
    private static final int DESCENDING_ARRAY = 6;

    public static double getAverageExecutionTimeOfSmartMergeSort(int trials, int complexity, int arrayRandomness)
    {
        Random r = new Random();
        Integer[] example;
        double totalTime = 0;
        double AET = 0;
        if (arrayRandomness == RANDOM_ARRAY)
        {
            for (int n = 1; n <= trials; n++)
            {
                example = MergeInsertionSort.generateRandomExample(r, complexity);
                totalTime += getExecutionTimeSmartMergeSort(example);
                example = null;
                System.gc();
            }
        }
        else if (arrayRandomness == MOSTLY_SORTED_ARRAY)
        {
            for (int n = 1; n <= trials; n++)
            {
                example = MergeInsertionSort.generateMostlySortedExample(r, complexity); //doesn't actually use randomGenerator r
                totalTime += getExecutionTimeSmartMergeSort(example);
                example = null;
                System.gc();
            }
        }
        else if (arrayRandomness == ALMOST_SORTED_ARRAY)
        {
            for (int n = 1; n <= trials; n++)
            {
                example = MergeInsertionSort.generateAlmostSortedExample(r, complexity); //doesn't actually use randomGenerator r
                totalTime += getExecutionTimeSmartMergeSort(example);
                example = null;
                System.gc();
            }
        }
        else if (arrayRandomness == BASICALLY_ALL_SORTED_ARRAY)
        {
            for (int n = 1; n <= trials; n++)
            {
                example = generateLargeNaturalRunsExample(r, complexity); //doesn't actually use randomGenerator r
                totalTime += getExecutionTimeSmartMergeSort(example);
                example = null;
                System.gc();
            }
        }
        else if (arrayRandomness == ASCENDING_ARRAY)
        {
            for (int n = 1; n <= trials; n++)
            {
                example = MergeInsertionSort.generateAscendingExample(r, complexity); //doesn't actually use randomGenerator r
                totalTime += getExecutionTimeSmartMergeSort(example);
                example = null;
                System.gc();
            }
        }
        else if (arrayRandomness == DESCENDING_ARRAY)
        {
            for (int n = 1; n <= trials; n++)
            {
                example = MergeInsertionSort.generateDescendingExample(r, complexity); //doesn't actually use randomGenerator r
                totalTime += getExecutionTimeSmartMergeSort(example);
                example = null;
                System.gc();
            }
        }
        AET = totalTime / 1000000 / trials;
        return AET;
    }

    public static void main(String[] args)
    {
        //Testing efficiency of MergeInsertionSort and SmartMergeSort
        //For that we start by running each method 1000 times, with arrays of size = 100000



        //Firstly we'll try with random arrays
        //SmartMergeSort
        double AET_SmartMergeSort_Random = getAverageExecutionTimeOfSmartMergeSort(1000, 100000, RANDOM_ARRAY);
        System.out.print("AET = ");
        System.out.printf("%.6f", AET_SmartMergeSort_Random);
        System.out.println("ms");
        //AET = 17,212300ms

        //MergeInsertionSort
        double AET_MergeInsertionSort_Random = MergeInsertionSort.getAverageExecutionTimeOfMergeInsertionSort(1000, 100000, RANDOM_ARRAY);
        System.out.print("AET = ");
        System.out.printf("%.6f", AET_MergeInsertionSort_Random);
        System.out.println("ms");
        //AET = 17,302699ms

        //As we can see, both methods are equally fast with random arrays, with SmartMergeSort being ever so slightly faster by 0.1ms



        //We will now test the same methods the same way but with "less random" arrays
        //90% Sorted arrays
        //SmartMergeSort
        double AET_SmartMergeSort_90 = getAverageExecutionTimeOfSmartMergeSort(1000, 100000, MOSTLY_SORTED_ARRAY);
        System.out.print("AET = ");
        System.out.printf("%.6f", AET_SmartMergeSort_90);
        System.out.println("ms");
        //AET = 6,387759ms

        //MergeInsertionSort
        double AET_MergeInsertionSort_90 = MergeInsertionSort.getAverageExecutionTimeOfMergeInsertionSort(1000, 100000, MOSTLY_SORTED_ARRAY);
        System.out.print("AET = ");
        System.out.printf("%.6f", AET_MergeInsertionSort_90);
        System.out.println("ms");
        //AET = 6,500438ms

        //Similar results, nothing can be said



        //99% Sorted
        //SmartMergeSort
        double AET_SmartMergeSort_99 = getAverageExecutionTimeOfSmartMergeSort(1000, 100000, ALMOST_SORTED_ARRAY);
        System.out.print("AET = ");
        System.out.printf("%.6f", AET_SmartMergeSort_99);
        System.out.println("ms");
        //AET = 4,881013ms

        //MergeInsertionSort
        double AET_MergeInsertionSort_99 = MergeInsertionSort.getAverageExecutionTimeOfMergeInsertionSort(1000, 100000, ALMOST_SORTED_ARRAY);
        System.out.print("AET = ");
        System.out.printf("%.6f", AET_MergeInsertionSort_99);
        System.out.println("ms");
        //AET = 5,378032ms

        //We see now a more noticeable change, but for now we won't tak any conclusions


        //99.9%
        //SmartMergeSort
        double AET_SmartMergeSort_99point9 = getAverageExecutionTimeOfSmartMergeSort(1000, 100000, BASICALLY_ALL_SORTED_ARRAY);
        System.out.print("AET = ");
        System.out.printf("%.6f", AET_SmartMergeSort_99point9);
        System.out.println("ms");
        //AET = 3,612097ms

        //MergeInsertionSort
        double AET_MergeInsertionSort_99point9 = MergeInsertionSort.getAverageExecutionTimeOfMergeInsertionSort(1000, 100000, BASICALLY_ALL_SORTED_ARRAY);
        System.out.print("AET = ");
        System.out.printf("%.6f", AET_MergeInsertionSort_99point9);
        System.out.println("ms");
        //AET = 5,109216ms

        //Despite still faster, we start seeing MergeInsertionSort fall behind SmartMergeSort
        //This is due to the nature of MergeInsertionSort method, that not only always divides array into equally sized sub-arrays to later on merge, it also performs an insertionSort every sub-array
        //SmartMergeSort on the other hand, detects that runs are bigger than the minimum size given by determineRunSize method, so it's going to be divided in less groups of sizes bigger than determined.
        //This will result in less merge operation as a whole, even ignoring mergeCollapse efficient way of performing such operations
        //Not only that but SmartMergeSort doesn't realize any sorting operations if the run found is bigger than the determined value since the same is already sorted by nature, while MergeInsertionSort always does such operations no matter how sorted the sub-array is
        //Overall, SmartMergeSort does less insertionsSorts and less merges operations, resulting in much faster results

        //With these conclusions we can assume for the two next tests (ascending and descending arrays) the gap between both times will be as large if not even more



        //Testing with ascending then descending arrays
        //SmartMergeSort
        double AET_SmartMergeSort_Ascending = getAverageExecutionTimeOfSmartMergeSort(1000, 100000, ASCENDING_ARRAY);
        System.out.print("AET = ");
        System.out.printf("%.6f", AET_SmartMergeSort_Ascending);
        System.out.println("ms");
        //AET = 0,240671ms

        //As expected it's done really fast

        //MergeInsertionSort
        double AET_MergeInsertionSort_Ascending = MergeInsertionSort.getAverageExecutionTimeOfMergeInsertionSort(1000, 100000, ASCENDING_ARRAY);
        System.out.print("AET = ");
        System.out.printf("%.6f", AET_MergeInsertionSort_Ascending);
        System.out.println("ms");
        //AET = 5,034841ms

        //And again, as predicted, SmartMergeSort is much faster
        //This due to the fact that SmartMergeSort will not realize any merge operations as it will only "divide" the array in one run of n size
        //Also since it's already sorted it won't do any sorting operation as well
        //On the other hand, MergeInsertionSort still does the same amount of merges and insertionSorts
        //Realize that the insertionSorts won't do anything, but they will still take time

        //Descending now
        //SmartMergeSort
        double AET_SmartMergeSort_Descending = getAverageExecutionTimeOfSmartMergeSort(1000, 100000, DESCENDING_ARRAY);
        System.out.print("AET = ");
        System.out.printf("%.6f", AET_SmartMergeSort_Descending);
        System.out.println("ms");
        //AET = 0,445974ms

        //The reason why it's still this fast is because of the nature of method getNaturalOrMakeAscendingRun
        //That not only can give a run of n size if the array is ascending, but can also de the opposite, when the array is 100% unsorted
        //However it's still slightly longer as it does need to invert the array, which is still way faster than sorting with insertionSort

        //MergeInsertionSort
        double AET_MergeInsertionSort_Descending = MergeInsertionSort.getAverageExecutionTimeOfMergeInsertionSort(1000, 100000, DESCENDING_ARRAY);
        System.out.print("AET = ");
        System.out.printf("%.6f", AET_MergeInsertionSort_Descending);
        System.out.println("ms");
        //AET = 13,767066ms

        //Surprisingly, it still beats the AET when the array is completely random for both algorithms
        //Nonetheless, it's arguably much worse than SmartMergeSort in this specific case


    }
}