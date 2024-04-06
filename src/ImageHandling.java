import java.awt.image.PackedColorModel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class ImageHandling {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/myDatabase";
        String username = "root";
        String password = "bishal0000@";

        String image_path = "/Users/bishalkhatiwada/Documents/shiv/shiva.jpeg"; \\ to insert the image
        String folder_path = "/Users/bishalkhatiwada/Documents/shiv/";


//        String query = "INSERT INTO image_table(image_data) VALUES (?)";
        String query = "SELECT image_data FROM image_table WHERE image_id = (? )"


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully");

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection established successfully");
            FileInputStream fileInputStream = new FileInputStream(image_path);
            byte[] imageData = new byte[fileInputStream.available()];
            fileInputStream.read(imageData);

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setBytes(1, imageData);
            int affectedRows = preparedStatement.executeUpdate();

            if(affectedRows > 0){
                System.out.println("Image Insertion Successfully");
            }
            else {
                System.out.println("Failed!!!! Image is not inserted");
            }



        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
}