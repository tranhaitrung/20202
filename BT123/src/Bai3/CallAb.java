package Bai3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.concurrent.Callable;

public class CallAb implements Callable {
    private String pathFile;

    public CallAb(String path){
        this.pathFile = path;
    }

    @Override
    public Map<String,Integer> call() throws Exception {
        InputStream in;
        BufferedReader fileReader = new BufferedReader(new FileReader(pathFile));

        Map<String, Integer> map = new TreeMap<>();

        String str;
        while ((str = fileReader.readLine())!=null){
            String line = str.toLowerCase();
            StringTokenizer tokenizer = new StringTokenizer(line, ".!=,:;[]{}+-—?''\\\"()/*][$&#_ ");
            while (tokenizer.hasMoreElements()){
                String tmp = tokenizer.nextToken();
                if(map.containsKey(tmp) == false){
                    map.put(tmp, 1);
                }
                else {
                    map.put(tmp, map.get(tmp)+1);
                }
            }
        }

        System.out.println("Done File : " + pathFile);
        return map;
    }
}
