package state.status;

import state.Order;

public interface OrderStatus {
    public void refreshOrder(Order order);
}
