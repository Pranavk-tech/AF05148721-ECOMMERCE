package ecommerce;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        UserService userService = new UserService();
        OrderService orderService = new OrderService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- E-commerce Backend ---");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Register User");
            System.out.println("4. View Users");
            System.out.println("5. Place Order");
            System.out.println("6. View Orders");
            System.out.println("7. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume the newline after number input

            switch (choice) {
                case 1:
                    productService.addProduct();
                    break;
                case 2:
                    productService.viewProducts();
                    break;
                case 3:
                    userService.registerUser();
                    break;
                case 4:
                    userService.viewUsers();
                    break;
                case 5:
                    orderService.placeOrder();
                    break;
                case 6:
                    orderService.viewOrders();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}