package BT_MySQL.DAO;

import BT_MySQL.Model.CityModel;
import BT_MySQL.Model.CountryModel;
import BT_MySQL.Service.CityService;
import BT_MySQL.Service.CountryService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ConnectionMySQL {
    private static String dbURL= "jdbc:mysql://localhost:3306/thuctap";
    private static String username = "root";
    private static String password = "";

    public static Connection connect(){
        try {
//            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(dbURL, username,password );
            System.out.println("Connected database successfull!");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean insertCity(CityModel cityModel){
        try{
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String sql = "insert into City values (?, ?, ?, ?)";
            PreparedStatement preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, cityModel.getId());
            preStatement.setString(2, cityModel.getNameCity());
            preStatement.setInt(3, cityModel.getPopulation());
            preStatement.setString(4, cityModel.getCountryCode());

            int insert = preStatement.executeUpdate();
            if(insert > 0)
                return true;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean insertCountry(CountryModel countryModel){
        try {
            Connection connection = ConnectionMySQL.connect();
            String sql = "INSERT INTO COUNTRY VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preStatment = connection.prepareStatement(sql);
            preStatment.setString(1, countryModel.getCode());
            preStatment.setString(2, countryModel.getNameCountry());
            preStatment.setString(3, countryModel.getContinent());
            preStatment.setDouble(4, countryModel.getSurfaceArea());
            preStatment.setInt(5, countryModel.getPopulation());
            preStatment.setDouble(6, countryModel.getGnp());
            preStatment.setInt(7, countryModel.getCappital());

            int inset = preStatment.executeUpdate();
            if(inset > 0)
                return true;

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        List<CountryModel> listCountry;
        List<CityModel> listCity ;

        CountryService countryService = new CountryService();
        listCountry = countryService.getCountryModelList();

        CityService cityService = new CityService();
        listCity = cityService.getListCity();

//        for (CountryModel c: listCountry){
//            boolean b = insertCountry(c);
//            if(b){
//                System.out.println("Thêm thành công "+c.getNameCountry());
//            }
//            else{
//                System.out.println("Thất bại ");
//            }
//        }

        for (CityModel c:listCity){
//            System.out.println(c.toString());
            boolean b = insertCity(c);
            if(b){
                System.out.println("Thêm thành công "+ c.getNameCity());
            }
            else {
                System.out.println("Thêm thất bại");
            }
        }

    }
}
