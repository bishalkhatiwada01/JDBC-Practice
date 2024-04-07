import javax.xml.transform.Result;
import java.awt.image.PackedColorModel;
import java.io.*;
import java.sql.*;

class ImageHandling {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/myDatabase";
        String username = "root";
        String password = "bishal0000@";


        //to insert the image to database
//        String image_path = "/Users/bishalkhatiwada/Documents/shiv/shivaa.png";

        // to retrive the image from the database to the local folders
        String folder_path = "/Users/bishalkhatiwada/Documents/shiv/";


//        String query = "INSERT INTO image_table(image_data) VALUES (?)";
        String query = "SELECT image_data from image_table where image_id = (?)";


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully");

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection established successfully");

//            <<<<<<< For Inseeting the Image
//            FileInputStream fileInputStream = new FileInputStream(image_path);
//            byte[] imageData = new byte[fileInputStream.available()];
//            fileInputStream.read(imageData);
//
//            PreparedStatement preparedStatement = con.prepareStatement(query);
//            preparedStatement.setBytes(1, imageData);
//            int affectedRows = preparedStatement.executeUpdate();
//
//            if(affectedRows > 0){
//                System.out.println("Image Insertion Successfully");
//            }
//            else {
//                System.out.println("Failed!!!! Image is not inserted");
//            }
//            >>>>>>>>>>>>>>>>>>>>>>

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, 1);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                byte[] image_data = resultSet.getBytes("image_data");
                String image_path = folder_path + "ExtractedImages.jpg";
                OutputStream outputStream = new FileOutputStream(image_path);

                outputStream.write(image_data);

            } else {
                System.out.println("Image not found");
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