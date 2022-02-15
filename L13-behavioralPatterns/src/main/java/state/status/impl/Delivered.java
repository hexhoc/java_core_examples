package state.status.impl;

import java.time.LocalDateTime;

import state.Order;
import state.status.OrderStatus;

public class Delivered implements OrderStatus{

    @Override
    public void refreshOrder(Order order) {
        //Some business-logic
        order.setDeliveredDate(LocalDateTime.now());
        System.out.println("Order delivered");        
    }
    
}
