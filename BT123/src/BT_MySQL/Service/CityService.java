package BT_MySQL.Service;

import BT_MySQL.Model.CityModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CityService {
    private  int id;
    private String name;
    private int population;
    private String codeCoutry;

    private List<CityModel> listCity;

    public CityService() throws IOException {
        listCity = new ArrayList<>();
        File file = new File("BT123/recoures/Bai8/cities.dat");
        BufferedReader fr = new BufferedReader(new FileReader(file));
        String readline;
        while ((readline = fr.readLine()) != null){
            readline = readline.substring(5);
            StringTokenizer tokenizer = new StringTokenizer(readline, ",");
            List<String> stringList = new ArrayList<>();
            while (tokenizer.hasMoreElements()){
                String tmp = tokenizer.nextToken();
                stringList.add(tmp);
            }
            for(int i=0;i<stringList.size();i++){
                tokenizer = new StringTokenizer(stringList.get(i),"=");
                String key = tokenizer.nextToken();
                String value = tokenizer.nextToken();

                switch (i){
                    case 0:
                        this.id= Integer.parseInt(value);
                        break;
                    case 1:
                        this.name = value;
                        break;
                    case 2:
                        this.population = Integer.parseInt(value);
                        break;
                    case 3:
                        value = value.substring(0,value.length()-1);
                        this.codeCoutry = value;
                }
            }

            CityModel cityModel = new CityModel(id, name, population, codeCoutry);
            listCity.add(cityModel);
        }

    }

    public List<CityModel> getListCity(){
        return listCity;
    }
}
