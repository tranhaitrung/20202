package Bai3;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.*;
import java.util.concurrent.*;

public class Bai3 {
    private static Map<String,Integer> map = new TreeMap<>();

    public static void main(String[] args) throws ExecutionException, InterruptedException, FileNotFoundException {
        //get list file...
        RFolder folder = new RFolder("D:/20202/TT20202/WEEEK_1/BT123/recoures/bai3/Input");
        List<String> files = folder.getListPathFile();

        //create Thread...
        Callable<Map<String, Integer>> callable;
        Future<Map<String, Integer>> future;
        ExecutorService executor = Executors.newFixedThreadPool(6);

        for(String f:files){
            callable = new CallAb(f);
            future = executor.submit(callable);
            map.putAll(future.get());
        }
        executor.shutdown();

        while (!executor.isTerminated()){
            //waiting...
        }

        PrintWriter printWriter = new PrintWriter("D:/20202/TT20202/WEEEK_1/BT123/recoures/bai3/Output/output.txt");
        Set<String> key = map.keySet();

        for(String k : key){
            printWriter.write(k + " : " + map.get(k) + "\n");
        }
        printWriter.close();
    }
}