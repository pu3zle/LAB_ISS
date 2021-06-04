package models;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Objects;

@javax.persistence.Entity
@Table(name = "drugs_orders")
public class OrderItem extends Entity<Integer>{
    private Integer did;
    private Integer oid;
    private Integer quantity;

    public OrderItem(Integer did, Integer oid, Integer quantity) {
        this.did = did;
        this.oid = oid;
        this.quantity = quantity;
    }

    @Column(name="did")
    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    @Column(name="oid")
    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public OrderItem() {

    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(quantity, orderItem.quantity);
    }
}
