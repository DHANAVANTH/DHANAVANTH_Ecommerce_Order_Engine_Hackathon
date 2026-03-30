


import java.util.Scanner;

public class Home {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ProductService productService = new ProductService();
        CartService cartService = new CartService();
        Orderservice orderService = new Orderservice();

        String userId = "USER_1";

        while (true) {
            System.out.println("\n1 Add Product");
            System.out.println("2 View Products");
            System.out.println("3 Add to Cart");
            System.out.println("4 View Cart");
            System.out.println("5 Place Order");
            System.out.println("6 View Orders");
            System.out.println("7 Low Stock Alert");
            System.out.println("8 View Logs");
            System.out.println("0 Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("ID: ");
                    int id = sc.nextInt();

                    System.out.print("Name: ");
                    String name = sc.next();

                    System.out.print("Price: ");
                    double price = sc.nextDouble();

                    System.out.print("Stock: ");
                    int stock = sc.nextInt();

                    productService.addProduct(
                        new Product(id, name, price, stock));
                    break;

                case 2:
                    productService.viewProducts();
                    break;

                case 3:
                    System.out.print("Product ID: ");
                    int pid = sc.nextInt();

                    System.out.print("Qty: ");
                    int qty = sc.nextInt();

                    Product product = productService.getProduct(pid);

                    if (product != null) {
                        cartService.addToCart(userId, product, qty);
                    }
                    break;

                case 4:
                    cartService.viewCart(userId);
                    break;

                case 5:
                    orderService.placeOrder(userId, cartService);
                    break;

                case 6:
                    orderService.viewOrders();
                    break;

                case 7:
                    productService.lowStockAlert();
                    break;

                case 8:
                    AuditLogin.showLogs();
                    break;

                case 0:
                    System.exit(0);
            }
        }
        
    }
}
