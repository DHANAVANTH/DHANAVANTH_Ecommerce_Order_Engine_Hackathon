


import java.util.Random;

public class Paymentservice {
    private Random random = new Random();

    public boolean processPayment() {
        return random.nextBoolean();
    }
}