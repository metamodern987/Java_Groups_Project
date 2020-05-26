/*
 Работа с драйвером jdbc для подключения к БД
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJDBC {

    public static void main(String args[]) {

        try (Connection connect = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/template1", "postgres", "password")) {

            if (connect != null) {
                System.out.println("Приконнектились к БД!");
            } else {
                System.out.println("Коннект к БД не удался");
            }
        }  catch (SQLException e) {
            System.err.format("SQL State: %s/n%s",e.getSQLState(), e.getMessage());
        }   catch (Exception e) {
            e.printStackTrace();
        }
    }


}

