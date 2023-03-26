package bridge.payment;

public interface PaymentSystem {
    boolean validatePayment(double amount);
    boolean authorizePayment(double amount);
    boolean capturePayment(double amount);
}