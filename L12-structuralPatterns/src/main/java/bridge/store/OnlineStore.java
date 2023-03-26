package bridge.store;

import bridge.payment.PaymentSystem;

public class OnlineStore {
    private final PaymentSystem paymentSystem;

    public OnlineStore(PaymentSystem paymentSystem) {
        this.paymentSystem = paymentSystem;
    }

    public void checkout(double amount) {
        boolean isValid = paymentSystem.validatePayment(amount);
        if (isValid) {
            boolean isAuthorized = paymentSystem.authorizePayment(amount);
            if (isAuthorized) {
                boolean isCaptured = paymentSystem.capturePayment(amount);
                if (isCaptured) {
                    System.out.println("Payment successful.");
                } else {
                    System.out.println("Payment capture failed.");
                }
            } else {
                System.out.println("Payment authorization failed.");
            }
        } else {
            System.out.println("Payment validation failed.");
        }
    }
}
