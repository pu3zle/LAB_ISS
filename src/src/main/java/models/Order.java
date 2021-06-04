package models;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;

@javax.persistence.Entity
@Table(name = "orders")
public class Order extends Entity<Integer>{
    private Date created_at;
    private Date delivered_at;
    private String department_name;
    private Integer requester;
    private OrderStatus status;

    public Order(Date createdAt, Date deliveredAt, String departmentName, Integer requester, OrderStatus status) {
        this.created_at = createdAt;
        this.delivered_at = deliveredAt;
        this.department_name = departmentName;
        this.requester = requester;
        this.status = status;
    }

    public Order() {

    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at")
    public Date getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(Date createdAt) {
        this.created_at = createdAt;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="delivered_at")
    public Date getDeliveredAt() {
        return delivered_at;
    }

    public void setDeliveredAt(Date deliveredAt) {
        this.delivered_at = deliveredAt;
    }

    @Column(name="department_name")
    public String getDepartmentName() {
        return department_name;
    }

    public void setDepartmentName(String departmentName) {
        this.department_name = departmentName;
    }

    public Integer getRequester() {
        return requester;
    }

    public void setRequester(Integer requesterName) {
        this.requester = requesterName;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(created_at, order.created_at) && Objects.equals(delivered_at, order.delivered_at) && Objects.equals(department_name, order.department_name) && Objects.equals(requester, order.requester) && status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(created_at, delivered_at, department_name, requester, status);
    }
}
