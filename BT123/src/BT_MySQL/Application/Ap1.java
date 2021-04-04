package BT_MySQL.Application;

import BT_MySQL.DAO.ConnectionMySQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Scanner;

public class Ap1 {
    public static void main(String[] args) throws SQLException {
        System.out.println("SELECT OPTION:");
        System.out.println("1. Tìm thành phố đông dân nhất của mỗi quốc gia.");
        System.out.println("2. Tìm thành phố đông dân nhất của mỗi lục địa");
        System.out.println("3. Tìm thành phố là thủ đô, đông dân nhất.");
        System.out.println("4. Tìm thành phố là thủ đô, đông dân nhất của mỗi lục địa.");
        System.out.println("5. Sắp xếp các quốc gia theo số lượng thành phố giảm dần.");
        System.out.println("6. Sắp xếp các quốc gia theo mật độ dân số theo thứ tự giảm dần bỏ qua các quốc gia có dân số bằng không.");
        System.out.println("----------------------------------------------------------------------------");

        Connection conn = ConnectionMySQL.connect();
        int i;
        Scanner scanner = new Scanner(System.in);
        i = scanner.nextInt();
        while (i<1 || i>6){
            System.out.println("Vui lòng nhập lại: ");
            i = scanner.nextInt();
        }
        switch (i){
            case 1:
                String sql = "select name, max(population), code from city group by code";
                ResultSet resultSet = conn.createStatement().executeQuery(sql);
                while (resultSet.next()){
                    String nameCity = resultSet.getString("name");
                    int p = resultSet.getInt("max(population)");
                    String code = resultSet.getString("code");
                    System.out.format("%s |%15d | %10s\n",nameCity,p,code);
                }
        }
    }
}
