import java.util.*;
import java.util.Scanner;

public class Client {

    private final static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) throws Exception {
        BestFirst bf = new BestFirst(sc.next());
        Iterator<BestFirst.State> x =  bf.solve();
        BestFirst.State s = null;
        while(x.hasNext()){
            s = x.next();
            System.out.println(s);
        }
        System.out.println();
        System.out.println((int)s.getWeight());
    }
}
