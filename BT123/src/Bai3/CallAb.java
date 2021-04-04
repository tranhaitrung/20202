package Bai3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.concurrent.Callable;

public class CallAb implements Callable {

    long start = System.currentTimeMillis();
    private String pathFile;
    public CallAb(String path){
        this.pathFile = path;
    }

    @Override
    public Map<String,Integer> call() throws Exception {
        InputStream in;
        BufferedReader fileReader = new BufferedReader(new FileReader(pathFile));

        System.out.println("Start read FILE: " + Thread.currentThread().getName());
        Map<String, Integer> map = new TreeMap<>();

        String str;

        while ((str = fileReader.readLine())!=null){
            String line = str.toLowerCase();
            StringTokenizer tokenizer = new StringTokenizer(line, ".!=,:;[]{}+-—?\t'\\\"()/*$&#_ ");
            while (tokenizer.hasMoreElements()){
                if(Thread.currentThread().isInterrupted()) {
                    System.out.println("Interrupted "+Thread.currentThread().getName());
                    return null;
                }
                String tmp = tokenizer.nextToken();
                if(!map.containsKey(tmp)){
                    map.put(tmp, 1);
                }
                else {
                    map.put(tmp, map.get(tmp)+1);
                }
            }
        }
        //TimeUnit.SECONDS.sleep(1);

        long finish = System.currentTimeMillis();

        System.out.println("Done File : " + Thread.currentThread().getName() + "  - Time : "+ (finish-start));
        return map;
    }
}
