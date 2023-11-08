import java.util.*;
import java.io.*;

public class MainTest{

    private final static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) throws Exception {

        int i = 1;

        System.out.print("While true: ");
        String s = sc.next();

        while(i++ == 1 || s.toLowerCase().equals("yes")){

            System.out.print("WriteFile: ");

            if(sc.next().toLowerCase().equals("yes")){

                List<List<Double>> averageData = new LinkedList<>();

                Long startTimer = System.nanoTime();
                FileWriter myWriter = new FileWriter("Tests" + i + ".txt");

                System.out.print("From: ");
                int from = Integer.parseInt(sc.next());

                System.out.print("To: ");
                int to = Integer.parseInt(sc.next());

                System.out.print("Numbers: ");
                int nbt = Integer.parseInt(sc.next());

                int sum = (to - from) / nbt;

                while(from <= to){

                    String outcome = "";

                    List<StateSearch> stateSearchs = new LinkedList<>();
                    List<StateSearch> stateWithoutUCSSearchs = new LinkedList<>();

                    stateSearchs.add(new UniformCostSearch()); stateSearchs.add(new AStar()); stateSearchs.add(new IterativeDeepeningAStar());
                    stateWithoutUCSSearchs.add(new AStar()); stateWithoutUCSSearchs.add(new IterativeDeepeningAStar());

                    if(from > -121 && from < 121){
                        StateSearchStudy study = new StateSearchStudy(stateSearchs, new Triple(from), new Triple(from*3), 30, ""); 
                        outcome =  study.toString();
                    }
                    else {
                        StateSearchStudy study = new StateSearchStudy(stateWithoutUCSSearchs, new Triple(from), new Triple(from*3), 30, ""); 
                        outcome =  study.toString();
                    }

                    myWriter.write("---------------------------------------------------------------------------------------\n" + outcome + "\n");

                    
                    from += sum;
                }

                myWriter.close();
                Long endTimer = System.nanoTime();


                double finalTime = (double) (endTimer - startTimer);

                double miliSeconds = finalTime / 1000000;
                double seconds = miliSeconds / 1000;
                double minutes = seconds/60;

                System.out.println("File creation time: " + minutes  + " Minutes / " + seconds + " Seconds / " + miliSeconds  + " MiliSeconds");
            }

            else{

                List<StateSearch> stateSearch = new LinkedList<>();
                
                System.out.print("Use UniformCostSearch: ");
                if(sc.next().toLowerCase().equals("yes")) 
                    stateSearch.add(new UniformCostSearch());

                System.out.print("Use AStar: ");
                if(sc.next().toLowerCase().equals("yes")) 
                    stateSearch.add(new AStar());

                System.out.print("Use IterativeDeepeningAStar: ");
                if(sc.next().toLowerCase().equals("yes")) 
                    stateSearch.add(new IterativeDeepeningAStar());

                System.out.print("Number of Tests: ");
                int numberOfTests = Integer.parseInt(sc.next());

                System.out.print("Input: ");
                String input = sc.next();
                String goal = String.valueOf(Integer.parseInt(input) * 3);
                System.out.println();

                StateSearchStudy study = new StateSearchStudy(stateSearch, new Triple(input), new Triple(goal), numberOfTests, "=====================");
                String outcome = study.toString();
                System.out.println(outcome);

            }                  
        }
    }
}
