package state.status.impl;

import java.time.LocalDateTime;

import state.Order;
import state.status.OrderStatus;

public class Assembling implements OrderStatus{

    @Override
    public void refreshOrder(Order order) {
        //Some business-logic
        order.setAssemblingDate(LocalDateTime.now());
        System.out.println("Order assembling");
    }
    
}
