import java.sql.*;
import java.util.Scanner;

public class BatchProcessing {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/myDatabase";
        String username = "root";
        String password = "bishal0000@";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully");

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connction Established Successfully");
            con.setAutoCommit(false);
//            Statement statement = con.createStatement();
//            statement.addBatch("INSERT INTO employees(name, job_title, salary) VALUES('Rojnit', 'AI Developer', 84000.0)");
//            statement.addBatch("INSERT INTO employees(name, job_title, salary) VALUES('Himsi', 'ML Developer', 77000.0)");
//            statement.addBatch("INSERT INTO employees(name, job_title, salary) VALUES('Asmit', 'DOT NET Developer', 73000.0)");
//            int[] batchResult = statement.executeBatch();
//            con.commit();
//            System.out.println("Batch Executed Successfully");

            String query = "INSERT INTO employees(name, job_title, salary) VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("Name: ");
                String name = sc.nextLine();
                System.out.println("Job Title: ");
                String job_title = sc.nextLine();
                System.out.println("Salary: ");
                double salary = sc.nextDouble();
                sc.nextLine();

                preparedStatement.setString(1, name);
                preparedStatement.setString(2, job_title);
                preparedStatement.setDouble(3, salary);
                preparedStatement.addBatch();

                System.out.println("Add more values Y/N ");
                String decision = sc.nextLine();
                if (decision.toUpperCase().equals("N")) {
                    break;
                }
            }
            int[] batchResult = preparedStatement.executeBatch();
            con.commit();
            System.out.println("Batch executed successfully");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}