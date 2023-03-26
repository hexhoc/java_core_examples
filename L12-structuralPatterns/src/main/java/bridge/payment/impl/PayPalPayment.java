package bridge.payment.impl;

import bridge.payment.PaymentSystem;

public class PayPalPayment implements PaymentSystem {
    @Override
    public boolean validatePayment(double amount) {
        // Validate PayPal details
        return true;
    }

    @Override
    public boolean authorizePayment(double amount) {
        // Authorize payment with PayPal
        return true;
    }

    @Override
    public boolean capturePayment(double amount) {
        // Capture payment from PayPal
        return true;
    }
}