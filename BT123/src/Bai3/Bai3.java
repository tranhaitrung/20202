package Bai3;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.*;

public class Bai3 {

    private static final Map<String,Integer> map = new TreeMap<>();

    public static void main(String[] args) throws ExecutionException, InterruptedException, FileNotFoundException {
        //get list file...
        RFolder folder = new RFolder("BT123/recoures/bai3/Input");
        List<String> files = folder.getListPathFile();

        //create Thread...
        Callable<Map<String, Integer>> callable;
        Future<Map<String, Integer>> future;
        ExecutorService executor = Executors.newFixedThreadPool(6);//Creating Executor Service with a thread pool of Size 6

        List<Future<Map<String, Integer>>> futureList = new ArrayList<>();
        for(String f:files){
            callable = new CallAb(f);
            future = executor.submit(callable);
            futureList.add(future);
        }
        executor.shutdown();//end task

        for (Future<Map<String,Integer>> i:futureList){
            try {
                map.putAll(i.get(150,TimeUnit.MILLISECONDS));
//                if(i.isDone()){
//                    System.out.println("Done "+ i);
//                }
//                else{
//                    System.out.println("Not done!!!");
//                }
            }
            catch (InterruptedException | ExecutionException e){
                e.printStackTrace();
            }
            catch (TimeoutException exception){
                System.out.println("TIME OUT!! " + i );
                if(!i.isDone()) {
                    i.cancel(true);
                }
                System.out.println("Cancle " + i.isCancelled());
            }


        }


        PrintWriter printWriter = new PrintWriter("BT123/recoures/bai3/Output/output.txt");
        Set<String> key = map.keySet();

        for(String k : key){
            printWriter.write(k + " : " + map.get(k) + "\n");
        }
        printWriter.close();
    }
}

