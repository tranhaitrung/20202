
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class Bai2 {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        Set<String> set = new HashSet<String>();
        try {
            File fl = new File("D:/20202/TT20202/WEEEK_1/BT123/recoures/Bai2/Input.txt");
            FileReader fr = new FileReader(fl);
            BufferedReader br = new BufferedReader(fr);
            String line1;

            File f2 = new File("D:/20202/TT20202/WEEEK_1/BT123/recoures/Bai2/Output.txt");
            FileWriter fw = new FileWriter(f2);

            while ((line1 = br.readLine()) != null){
                String line = line1.toLowerCase();
                StringTokenizer tokenizer = new StringTokenizer(line, ".,!--?'\"()/ ");

                while (tokenizer.hasMoreElements()){
                    String str = tokenizer.nextToken();
                    set.add(str);
                    if(map.containsKey(str)){
                        int k = map.get(str);
                        k++;
                        map.replace(str, k);
                    }
                    else{
                        map.put(str,1);
                    }
                }
            }

            fw.write("Số lần xuất hiện của các từ: \n");
            for (String str:set){
                int tmp = map.get(str);
                fw.write(str + " : " + tmp + "\n");
            }
            fw.close();
            fr.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }


        System.out.println("DONE!!!");
    }
}
