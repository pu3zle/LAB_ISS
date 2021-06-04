package client.controllers;

import models.Drug;
import models.Order;
import models.OrderItem;
import models.User;
import models.dtos.OrderDTO;
import persistence.database.DrugsHibernation;
import persistence.database.OrderItemsHibernation;
import persistence.database.OrdersHibernation;

import java.util.ArrayList;
import java.util.List;

public class PharmacistController {
    private DrugsHibernation drugsHibernation;
    private OrdersHibernation ordersHibernation;
    private OrderItemsHibernation orderItemsHibernation;
    private User currentUser;

    public PharmacistController(DrugsHibernation drugsHibernation, OrdersHibernation ordersHibernation, OrderItemsHibernation orderItemsHibernation, User currentUser) {
        this.drugsHibernation = drugsHibernation;
        this.ordersHibernation = ordersHibernation;
        this.orderItemsHibernation = orderItemsHibernation;
        this.currentUser = currentUser;
    }
    private OrderDTO CreateOrderDTO(Iterable<OrderItem> orderItems, Order o)
    {
        Integer totalPrice = 0;
        for(OrderItem oi : orderItems)
        {
            Drug drug = drugsHibernation.findOne(oi.getDid());
            totalPrice += drug.getPrice() * oi.getQuantity();
        }
        if(totalPrice != 0)
            return new OrderDTO(o.getId(),o.getCreatedAt(), o.getDeliveredAt(), o.getStatus(), totalPrice, o.getDepartmentName());
        return null;
    }
    public List<OrderDTO> GetAllOrders()
    {
        List<OrderDTO> orderDTOS = new ArrayList<>();
        Iterable<Order> orders = ordersHibernation.findAll();
        for(Order o : orders)
        {
            Iterable<OrderItem> orderItems = orderItemsHibernation.findAllByOrder(o.getId());
            OrderDTO orderDTO = CreateOrderDTO(orderItems, o);
            if(orderDTO != null)
                orderDTOS.add(orderDTO);
        }
        return orderDTOS;
    }
}
