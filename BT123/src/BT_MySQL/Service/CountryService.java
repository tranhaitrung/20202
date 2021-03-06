package BT_MySQL.Service;


import BT_MySQL.Model.CountryModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CountryService {
    String code;
    String name;
    String continent;
    double surfaceArea;
    int population;
    double gnp;
    int capital;

    private List<CountryModel> listCountry;
    public CountryService() throws IOException {
        File file = new File("D:/20202/TT20202/WEEEK_1/BT123/recoures/Bai8/countries.dat");
        BufferedReader fr = new BufferedReader(new FileReader(file));
        listCountry = new ArrayList<>();
        String line;
        while ((line = fr.readLine())!= null){
            line = line.substring(7);

            StringTokenizer tokenizer = new StringTokenizer(line,",");

            List<String> stringList = new ArrayList<>();

            while (tokenizer.hasMoreElements()){
                String tmp = tokenizer.nextToken();
                stringList.add(tmp);
            }

            for(int i=0; i<stringList.size();i++){
                tokenizer = new StringTokenizer(stringList.get(i),"=");
                String key = tokenizer.nextToken();
                String value = tokenizer.nextToken();

                switch (i){
                    case 0:
                        code = value;
                        break;
                    case 1:
                        name = value;
                        break;
                    case 2:
                        continent = value;
                        break;
                    case 3:
                        surfaceArea = Double.parseDouble(value);
                        break;
                    case 4:
                        population = Integer.parseInt(value);
                        break;
                    case 5:
                        gnp = Double.parseDouble(value);
                        break;
                    case 6:
                        value = value.substring(0,value.length()-1);
                        capital = Integer.parseInt(value);
                        break;
                }
            }

            CountryModel countryModel = new CountryModel(code, name, continent, surfaceArea, population, gnp, capital);
            listCountry.add(countryModel);
        }
    }

    public List<CountryModel> getCountryModelList(){
        return listCountry;
    }

}
