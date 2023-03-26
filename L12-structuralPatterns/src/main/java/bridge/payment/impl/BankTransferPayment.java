package bridge.payment.impl;

import bridge.payment.PaymentSystem;

public class BankTransferPayment implements PaymentSystem {
    @Override
    public boolean validatePayment(double amount) {
        // Validate bank transfer details
        return true;
    }

    @Override
    public boolean authorizePayment(double amount) {
        // Authorize payment with bank
        return true;
    }

    @Override
    public boolean capturePayment(double amount) {
        // Capture payment from bank
        return true;
    }
}