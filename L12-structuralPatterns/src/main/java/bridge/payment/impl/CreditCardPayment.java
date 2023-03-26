package bridge.payment.impl;

import bridge.payment.PaymentSystem;

public class CreditCardPayment implements PaymentSystem {
    @Override
    public boolean validatePayment(double amount) {
        // Validate credit card details
        return true;
    }

    @Override
    public boolean authorizePayment(double amount) {
        // Authorize payment with credit card processor
        return true;
    }

    @Override
    public boolean capturePayment(double amount) {
        // Capture payment from credit card processor
        return true;
    }
}