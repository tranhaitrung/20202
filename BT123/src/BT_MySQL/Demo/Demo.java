package BT_MySQL.Demo;

import BT_MySQL.DAO.ConnectionMySQL;
import BT_MySQL.Model.CityModel;

import java.sql.*;
import java.util.Scanner;

public class Demo {

    public static void main(String[] args) throws SQLException {
        Connection conn = ConnectionMySQL.connect();

        Statement statement = conn.createStatement();
        //query mysql to city
        String sql = "SELECT * FROM CITY WHERE code = ?";
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập mã Country:");
        String code = scanner.nextLine();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,code);
        System.out.println(pstmt);
        ResultSet resultSet1 = pstmt.executeQuery();

        if(!resultSet1.next()){
            System.out.println("DON'T FOUND");
        }
        while (resultSet1.next()){
            CityModel cityModel = new CityModel();
            cityModel.setId(resultSet1.getInt("id"));
            cityModel.setNameCity(resultSet1.getString("name"));
            cityModel.setPopulation(resultSet1.getInt("population"));
            cityModel.setCountryCode(resultSet1.getString("code"));

            System.out.format("%10d | %-25s |%10d |%10s \n" ,cityModel.getId(), cityModel.getNameCity(),cityModel.getPopulation(),cityModel.getCountryCode());
        }
    }
}
