import java.sql.*;

public class TransactionHandling {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/myDatabase";
        String username = "root";
        String password = "bishal0000@";
        String withdrawQuery = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
        String depositQuery = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully");

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connction Established Successfully");
            con.setAutoCommit(false);// commit is true by default so, it needs to be false at first

            try {
                PreparedStatement withdrawStatemant = con.prepareStatement(withdrawQuery);
                PreparedStatement depositStatement = con.prepareStatement(depositQuery);
                withdrawStatemant.setDouble(1, 1.00);
                withdrawStatemant.setString(2, "account123");
                depositStatement.setDouble(1, 1.00);
                depositStatement.setString(2, "account456");


                int rowsAffectedWithdraw = withdrawStatemant.executeUpdate();
                int rowsAffectedDeposit = depositStatement.executeUpdate();

                if (rowsAffectedDeposit > 0 && rowsAffectedWithdraw > 0) {
                    con.commit();
                    System.out.println("Transaction successful!!");
                } else {
                    con.rollback();
                    System.out.println("Transaction Failed!!");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }


    }
}