

import java.io.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Bai1 {

    public static void main(String[] args) {

        Set<Integer> setA = new HashSet<Integer>();
        Set<Integer> setB = new HashSet<Integer>();

        int cnt = 0;
        Random generator = new Random();

        //tạo tập set thứ nhất
        while (cnt < 200000){
            int tmp = generator.nextInt( );
            if(setA.contains(tmp) == false){
                setA.add(tmp);
                cnt++;
            }

        }
        cnt = 0;
        //tạo tập set thứ 2
        int c=0; // kiểm tra xem tập B có phần tử trùng với A hay không
        while (cnt < 200000){
            int tmp = generator.nextInt( );
            if(setB.contains(tmp) == false){
                setB.add(tmp);
                cnt++;
                if(setA.contains(tmp)==true){
                    c++;
                }
            }
        }

        //nếu chưa có phần tử trùng thì tạo lại tập B
        while (c==0){
            setB.clear();
            cnt = 0;
            while (cnt < 200000){
                int tmp = generator.nextInt( );
                if(setB.contains(tmp) == false){
                    setB.add(tmp);
                    cnt++;
                    if(setA.contains(tmp)==true){
                        c++;
                    }
                }
            }
        }

        System.out.println("Set A có size: "+ setA.size());
        System.out.println("Set B có size: "+ setB.size());

        Set<Integer> setC = new HashSet<Integer>();
        Set<Integer> setD = new HashSet<Integer>();

        for (Integer tmp:setA){
            if(setB.contains(tmp) == true){
                setC.add(tmp);
                setB.remove(tmp);
            }
        }

        setD.addAll(setA);
        setD.addAll(setB);

        System.out.println("Set C có size: "+ setC.size());
        System.out.println("Set D có size: "+ setD.size());


        for(Integer tmp:setC){
            System.out.println(tmp);
        }

        try {
            File f = new File("D:/20202/TT20202/WEEEK_1/BT123/recoures/Bai1.txt");
            FileWriter fw = new FileWriter(f);
            fw.write("Tập A: \n");
            for(Integer tmp:setA){
                String str = tmp.toString();
                fw.write(str +" ");
            }

            fw.write("\n\n Tập B\n");
            for(Integer tmp:setB){
                String str = tmp.toString();
                fw.write(str +" ");
            }
            fw.write("\n\n Tập giao \n");
            for(Integer tmp:setC){
                String str = tmp.toString();
                fw.write(str +" ");
            }
            fw.write("\n\n Tập hợp \n");
            for(Integer tmp:setD){
                String str = tmp.toString();
                fw.write(str +" ");
            }
            fw.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
