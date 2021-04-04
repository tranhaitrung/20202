package BT_MySQL.Application;

import BT_MySQL.DAO.ConnectionMySQL;
import BT_MySQL.Model.CityModel;
import BT_MySQL.Model.CountryModel;
import BT_MySQL.Service.CityService;
import BT_MySQL.Service.CountryService;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Application {
    public static void main(String[] args) throws IOException, SQLException {
        CountryService countryService = new CountryService();
        CityService cityService = new CityService();

        List<CityModel> listCity = new ArrayList<>();
        List<CountryModel> listCountry = new ArrayList<>();

        Connection conn = ConnectionMySQL.connect();
        String sql = "SELECT * FROM COUNTRY";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet resultSet = pstmt.executeQuery();
        while (resultSet.next()){
            CountryModel countryModel = new CountryModel();
            countryModel.setCode(resultSet.getString("code"));
            countryModel.setNameCountry(resultSet.getString("name"));
            countryModel.setContinent(resultSet.getString("continent"));
            countryModel.setSurfaceArea(resultSet.getDouble("surfaceArea"));
            countryModel.setPopulation(resultSet.getInt("population"));
            countryModel.setGnp(resultSet.getDouble("gnp"));
            countryModel.setCappital(resultSet.getInt("capital"));
            listCountry.add(countryModel);
        }

        sql = "SELECT * FROM CITY";
        pstmt = conn.prepareStatement(sql);
        resultSet = pstmt.executeQuery();
        while (resultSet.next()){
            CityModel cityModel = new CityModel();
            cityModel.setId(resultSet.getInt("id"));
            cityModel.setNameCity(resultSet.getString("name"));
            cityModel.setPopulation(resultSet.getInt("population"));
            cityModel.setCountryCode(resultSet.getString("code"));
            listCity.add(cityModel);
        }


        System.out.println("SELECT OPTION:");
        System.out.println("1. Tìm thành phố đông dân nhất của mỗi quốc gia.");
        System.out.println("2. Tìm thành phố đông dân nhất của mỗi lục địa");
        System.out.println("3. Tìm thành phố là thủ đô, đông dân nhất.");
        System.out.println("4. Tìm thành phố là thủ đô, đông dân nhất của mỗi lục địa.");
        System.out.println("5. Sắp xếp các quốc gia theo số lượng thành phố giảm dần.");
        System.out.println("6. Sắp xếp các quốc gia theo mật độ dân số theo thứ tự giảm dần bỏ qua các quốc gia có dân số bằng không.");
        System.out.println("----------------------------------------------------------------------------");

        int i;
        Scanner scanner = new Scanner(System.in);
        i = scanner.nextInt();
        while (i<1 || i>6){
            System.out.println("Vui lòng nhập lại: ");
            i = scanner.nextInt();
        }
        switch (i){
            case 1:
                Set<String> listCode = new HashSet<>();
                listCountry.forEach(country -> {
                    listCode.add(country.getCode());
                });
                List<CityModel> finalListCity = listCity;
                listCode.stream().forEach(code->{
                    Stream<CityModel> cityStream = finalListCity.stream().filter(city->city.getCountryCode().equals(code));
                    String cityMax = cityStream.max(Comparator.comparing(CityModel::getPopulation)).get().getNameCity();
                    System.out.println(code + " : " +cityMax);
                });
                break;

            case 2:
                Set<String> listContinent = new HashSet<>();
                Map<String, String> countryContinent = new HashMap<>();
                listCountry.forEach(country ->{
                    if(listContinent.contains(country.getContinent()) == false)
                        listContinent.add(country.getContinent());
                    countryContinent.put(country.getCode(), country.getContinent());
                });

                List<CityModel> listCity2 = listCity;
                listContinent.stream().forEach(continent->{

                        Stream<CityModel> city2 = listCity2.stream().filter(city->countryContinent.get(city.getCountryCode()).equals(continent));
                        try {
                            CityModel cityMax = city2.max(Comparator.comparing(CityModel::getPopulation)).get();
                            System.out.println("Continent : " + continent);
                            System.out.println("the most populous city is " + cityMax.getNameCity() +" - " + cityMax.getPopulation());
                            System.out.println("-----------------------------------");
                        }
                        catch (Exception e){
                            System.out.println("No Information");
                            System.out.println("-------------------------");
                        }
                });
                break;

            case 3:
                Set<Integer> listCapital = new HashSet<>();
                Map<String, Integer> cap = new HashMap<>();
                listCountry.stream().forEach(country->{
                    cap.put(country.getCode(), country.getCappital());
                    listCapital.add(country.getCappital());
                });
                List<CityModel> listCity3 = listCity;
                Stream<CityModel> cappital = listCity3.stream().filter(city->listCapital.contains(city.getId()));
                try {
                    String cityMax = cappital.max(Comparator.comparing(CityModel::getPopulation)).get().getNameCity();
                    System.out.println("The most populous city is "+ cityMax);
                }
                catch (Exception e){
                    System.out.println("ERROR!");
                }
                break;

            case 4:
                Set<String> listContinent4 = new HashSet<>();
                Set<Integer> listCapital4 = new HashSet<>();
                Map<String, String> map4 = new HashMap<>();

                listCountry.forEach(country->{
                    listContinent4.add(country.getContinent());
                    listCapital4.add(country.getCappital());
                    map4.put(country.getCode(), country.getContinent());
                });

                List<CityModel> listCity4 = listCity;
                listContinent4.stream().forEach(countinent->{
                    Stream<CityModel> city = listCity4.stream().filter(city1->map4.get(city1.getCountryCode()).equals(countinent));
                    Stream<CityModel> capital = city.filter(city4->listCapital4.contains(city4.getId()));
                    try{
                        CityModel cityMax = capital.max(Comparator.comparing(CityModel::getPopulation)).get();
                        System.out.println("Continent "+ countinent);
                        System.out.println("The most populous capital is "+cityMax.getNameCity() + " - " +cityMax.getPopulation());
                        System.out.println("---------------------");
                    }
                    catch (Exception e){
                        System.out.println("NO INFOR");
                    }
                });
                break;

            case 5:
                Map<String, Long> mapCode = new HashMap<>();
                Set<String> listCountry5 = new HashSet<>();
                listCountry.forEach(country->{
                    listCountry5.add(country.getCode());
                });

                List<CityModel> listCity5 = listCity;
                listCountry5.stream().forEach(countr->{
                    long count;
                    Stream<CityModel> cityStream5 = listCity5.stream().filter(city->city.getCountryCode().equals(countr));
                    count = cityStream5.count();
                    mapCode.put(countr, count);
                });

                List<String> listSorted = listCountry5.stream().sorted((str1,str2)->(mapCode.get(str2).compareTo(mapCode.get(str1)))).collect(Collectors.toList());
                Set<String> tmp = mapCode.keySet();
                listSorted.stream().forEach(cty->{
                    System.out.println(cty+ " : " + mapCode.get(cty));
                });
                break;

            case 6:
                Map<String, Double> denistyCountry = new HashMap<>();

                Set<String> listCountry6 = new HashSet<>();


                listCountry.forEach(country->{
                    double denisty = country.getPopulation()/country.getSurfaceArea();
                    if(denisty > 0) {
                        denistyCountry.put(country.getCode(), denisty);
                        listCountry6.add(country.getCode());
                    }
                });

                List<String> listSort = listCountry6.stream().sorted((str1,str2)->(denistyCountry.get(str2).compareTo(denistyCountry.get(str1)))).collect(Collectors.toList());
                listSort.stream().forEach(code6->{
                    System.out.println(code6 + " : " + denistyCountry.get(code6));
                });
                break;

        }
    }
}
