package models.dtos;

import models.Entity;
import models.OrderStatus;

import javax.persistence.Column;
import java.util.Date;

public class OrderDTO extends Entity<Integer> {
    private Integer orderId;
    private Date createdAt;
    private Date deliveredAt;
    private OrderStatus status;
    private Integer totalPrice;
    private String department;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public OrderDTO(Integer orderId, Date createdAt, Date deliveredAt, OrderStatus status, Integer totalPrice, String department) {
        this.orderId = orderId;
        this.createdAt = createdAt;
        this.deliveredAt = deliveredAt;
        this.status = status;
        this.totalPrice = totalPrice;
        this.department = department;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(Date deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

}
