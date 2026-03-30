


import java.util.*;

public class Orderservice {
    private List<Order> orders = new ArrayList<>();
    private Paymentservice paymentService = new Paymentservice();

    public void placeOrder(String userId, CartService cartService) {
        List<CartItem> cart = cartService.getCart(userId);

        if (cart.isEmpty()) {
            System.out.println("Cart is empty");
            return;
        }

        double total = 0;
        for (CartItem item : cart) {
            total += item.getTotal();
        }

        Order order = new Order(UUID.randomUUID().toString(), cart, total);

        boolean paymentSuccess = paymentService.processPayment();

        if (!paymentSuccess) {
            for (CartItem item : cart) {
                item.getProduct().releaseStock(item.getQuantity());
            }
            order.setStatus("FAILED");
            System.out.println("Payment failed. Stock restored.");
        } else {
            order.setStatus("PAID");
            cartService.clearCart(userId);
            System.out.println("Order placed successfully");
        }

        orders.add(order);
        AuditLogin.log("ORDER CREATED: " + order.getOrderId());
    }

    public void viewOrders() {
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}