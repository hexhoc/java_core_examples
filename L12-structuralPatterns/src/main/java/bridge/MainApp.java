package bridge;

import bridge.payment.PaymentSystem;
import bridge.payment.impl.BankTransferPayment;
import bridge.payment.impl.CreditCardPayment;
import bridge.payment.impl.PayPalPayment;
import bridge.store.OnlineStore;

public class MainApp {
    public static void main(String[] args) {
        OnlineStore onlineStore = new OnlineStore(new CreditCardPayment());
        onlineStore.checkout(100.0);

        onlineStore = new OnlineStore(new PayPalPayment());
        onlineStore.checkout(50.0);

        onlineStore = new OnlineStore(new BankTransferPayment());
        onlineStore.checkout(200.0);
    }
}
