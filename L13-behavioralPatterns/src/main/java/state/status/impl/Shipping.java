package state.status.impl;

import java.time.LocalDateTime;

import state.Order;
import state.status.OrderStatus;

public class Shipping implements OrderStatus{

    @Override
    public void refreshOrder(Order order) {
        //Some business-logic
        order.setShippingDate(LocalDateTime.now());
        System.out.println("Order shipping");
    }
    
}
