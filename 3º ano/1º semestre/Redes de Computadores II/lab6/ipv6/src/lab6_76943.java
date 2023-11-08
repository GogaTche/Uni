//a76943 Afonso Silva

import java.util.*;

public class lab6_76943 {

    private final static Scanner sc = new Scanner(System.in);


    public static String compressedToPreffered(String ip) throws Exception{
        StringBuilder stringbuilder = new StringBuilder();
        String[] ipChunk = ip.split(":", 8);
        String result = "";

        for(int i = 0; i < ipChunk.length; i++){
            
            if(ipChunk[i].length() > 4)
                throw new Exception("Invalid IPV6");

            if(ipChunk[i].equals("")){
                int x = 9 - ipChunk.length;
                while(x-- > 0){
                    result += "0000:";
                }
            }

            else {
                while(ipChunk[i].length() != 4){
                    ipChunk[i] = "0" + ipChunk[i];
                }
                result += ipChunk[i] + ":";
            }
        }

        stringbuilder.append(result);
        stringbuilder.delete(39, result.length());
        return stringbuilder.toString();
    }

    public static void main(String[] args) throws Exception {
        String ip = sc.next();
        while(!ip.equals("bye")){
            System.out.println(compressedToPreffered(ip));
            ip = sc.next();
        }
    }
}
