package BT_MySQL.Demo;

import BT_MySQL.DAO.ConnectionMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transaction {
    public static void main(String[] args) {
        Connection cnn = null;
        try{
            cnn = ConnectionMySQL.connect();
            cnn.setAutoCommit(false);
            String sql = "INSERT INTO COUNTRY VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = cnn.prepareStatement(sql);
            String code = "NVV";
            String name = "VietNam";
            String continent = "Asea";
            double surface = 122443.3;
            int population = 100000000;
            double gnp = 223.2;
            int capital = 84848484;

            pstmt.setString(1,code);
            pstmt.setString(2,name);
            pstmt.setString(3,continent);
            pstmt.setDouble(4,surface);
            pstmt.setInt(5,population);
            pstmt.setDouble(6,gnp);
            pstmt.setInt(7,capital);

            int rowAffected = pstmt.executeUpdate();
            if(rowAffected > 0){
                String sqlCity = "INSERT INTO CITY VALUES (?,?,?,?)";
                PreparedStatement psCity = cnn.prepareStatement(sqlCity);
                int id = 84848485;
                String nameCity = "Ha Noi";
                int populationCity = 10000000;
                String codeCountry = "VN";

                psCity.setInt(1,id);
                psCity.setString(2,nameCity);
                psCity.setInt(3,populationCity);
                psCity.setString(4,codeCountry);

                int check = psCity.executeUpdate();
                if(check>0){
                    cnn.commit();
                    System.out.println("ADD SUCCESSFUL");
                }else {
                    cnn.rollback();
                    System.out.println("ADD ERROR");
                }
            }


        } catch (SQLException e) {
            try{
                if(cnn !=null){
                    cnn.rollback();
                    System.out.println("ERROR - ROLL BACK!!");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
