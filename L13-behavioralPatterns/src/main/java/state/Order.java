package state;

import java.time.LocalDateTime;

import state.status.OrderStatus;
import state.status.impl.Assembling;

public class Order {
    private OrderStatus status;
    private LocalDateTime assemblingDate;
    private LocalDateTime shippingDate;
    private LocalDateTime deliveredDate;

    public Order() {
        this(new Assembling());
    }

    public Order(OrderStatus status) {
        this.status = status;
    }

    public void setAssemblingDate(LocalDateTime assemblingDate) {
        this.assemblingDate = assemblingDate;
    }

    public void setShippingDate(LocalDateTime shippingDate) {
        this.shippingDate = shippingDate;
    }

    public void setDeliveredDate(LocalDateTime deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.status = orderStatus;
        this.status.refreshOrder(this);
    }

    @Override
    public String toString() {
        return "Order [assemblingDate=" + assemblingDate + ", deliveredDate=" + deliveredDate + ", shippingDate="
                + shippingDate + ", status=" + status + "]";
    }

    
}
