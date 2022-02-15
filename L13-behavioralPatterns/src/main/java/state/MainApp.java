package state;

import state.status.impl.Assembling;
import state.status.impl.Delivered;
import state.status.impl.Shipping;

public class MainApp {
    public static void main(String[] args) {
        Order order = new Order();
        order.setOrderStatus(new Assembling());
        order.setOrderStatus(new Shipping());
        order.setOrderStatus(new Delivered());
        
        System.out.println(order.toString());
    }
}
