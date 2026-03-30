

import java.util.*;

public class CartService {
    private Map<String, List<CartItem>> carts = new HashMap<>();

    public void addToCart(String userId, Product product, int qty) {
        if (!product.reserveStock(qty)) {
            System.out.println("Insufficient stock");
            return;
        }

        carts.putIfAbsent(userId, new ArrayList<>());
        carts.get(userId).add(new CartItem(product, qty));

        AuditLogin.log(userId + " added " + product.getName() + " qty=" + qty);
    }

    public List<CartItem> getCart(String userId) {
        return carts.getOrDefault(userId, new ArrayList<>());
    }

    public void clearCart(String userId) {
        carts.remove(userId);
    }

    public void viewCart(String userId) {
        List<CartItem> cart = getCart(userId);
        for (CartItem item : cart) {
            System.out.println(item.getProduct().getName() + 
                    " Qty: " + item.getQuantity());
        }
    }
}
