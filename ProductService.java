package ecommerce;

import java.sql.*;
import java.util.Scanner;

public class ProductService {
    Scanner sc = new Scanner(System.in);

    public void addProduct() {
        System.out.print("Enter product name: ");
        String name = sc.nextLine();
        System.out.print("Enter price: ");
        double price = sc.nextDouble();
        System.out.print("Enter stock quantity: ");
        int stock = sc.nextInt();
        sc.nextLine();

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO products(name, price, stock) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, stock);
            ps.executeUpdate();
            System.out.println("Product added!");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewProducts() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM products");
            System.out.println("\n--- Products ---");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getDouble("price") + " | " +
                    rs.getInt("stock")
                );
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}