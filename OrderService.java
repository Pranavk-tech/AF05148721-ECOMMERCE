package ecommerce;

import java.sql.*;
import java.util.Scanner;

public class OrderService {
    Scanner sc = new Scanner(System.in);

    public void placeOrder() {
        System.out.print("Enter user ID: ");
        int userId = sc.nextInt();
        System.out.print("Enter product ID: ");
        int productId = sc.nextInt();
        System.out.print("Enter quantity: ");
        int quantity = sc.nextInt();

        try {
            Connection conn = DBConnection.getConnection();

            String checkStock = "SELECT stock, price FROM products WHERE id=?";
            PreparedStatement ps1 = conn.prepareStatement(checkStock);
            ps1.setInt(1, productId);
            ResultSet rs = ps1.executeQuery();

            if (rs.next()) {
                int stock = rs.getInt("stock");
                double price = rs.getDouble("price");
                if (quantity > stock) {
                    System.out.println("Not enough stock!");
                    return;
                }

                double total = price * quantity;

                String insertOrder = "INSERT INTO orders(user_id, product_id, quantity, total_price) VALUES (?, ?, ?, ?)";
                PreparedStatement ps2 = conn.prepareStatement(insertOrder);
                ps2.setInt(1, userId);
                ps2.setInt(2, productId);
                ps2.setInt(3, quantity);
                ps2.setDouble(4, total);
                ps2.executeUpdate();

                String updateStock = "UPDATE products SET stock=? WHERE id=?";
                PreparedStatement ps3 = conn.prepareStatement(updateStock);
                ps3.setInt(1, stock - quantity);
                ps3.setInt(2, productId);
                ps3.executeUpdate();

                System.out.println("Order placed! Total: $" + total);
            } else {
                System.out.println("Product not found!");
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewOrders() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM orders");
            System.out.println("\n--- Orders ---");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " | User ID: " +
                    rs.getInt("user_id") + " | Product ID: " +
                    rs.getInt("product_id") + " | Quantity: " +
                    rs.getInt("quantity") + " | Total: " +
                    rs.getDouble("total_price")
                );
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}