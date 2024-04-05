import com.mysql.jdbc.Driver;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException{

        String url = "jdbc:mysql://localhost:3306/myDatabase";
        String username = "root";
        String password = "bishal0000@";
        String query = "Select * from employees;";


        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Loaded Successfully!!!!");
        }
        catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Conection Established Successfully!!!");
            System.out.println("********************************************");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String job_title = rs.getString("job_title");
                double salary = rs.getDouble("salary");
                System.out.println();
                System.out.println("-------------------------------------");
                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Job Title: " + job_title);
                System.out.println("Salary: $" + salary);

            }
            rs.close();
            stmt.close();
            con.close();

            System.out.println();
            System.out.println("********************************************");
            System.out.println("Connection Closed Successfully!!!");

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }



    }
}