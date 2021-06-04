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
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MedPersController {
    private DrugsHibernation drugsHibernation;
    private OrdersHibernation ordersHibernation;
    private OrderItemsHibernation orderItemsHibernation;
    private User currentUser;

    public MedPersController(DrugsHibernation drugsHibernation,OrderItemsHibernation orderItemsHibernation,
                             OrdersHibernation ordersHibernation, User user) {
        this.drugsHibernation = drugsHibernation;
        this.orderItemsHibernation = orderItemsHibernation;
        this.ordersHibernation = ordersHibernation;
        currentUser = user;
    }

    public DrugsHibernation getDrugsHibernation() {
        return drugsHibernation;
    }

    public void setDrugsHibernation(DrugsHibernation drugsHibernation) {
        this.drugsHibernation = drugsHibernation;
    }
    public List<Drug> GetAllDrugs()
    {
        return StreamSupport.stream(drugsHibernation.findAll().spliterator(), false).collect(Collectors.toList());
    }
    public List<OrderDTO> GetOrdersForUser()
    {
        List<OrderDTO> orderDTOS = new ArrayList<>();
        Iterable<Order> orders = ordersHibernation.findAllByUser(currentUser.getId());
        for(Order o : orders)
        {
            Iterable<OrderItem> orderItems = orderItemsHibernation.findAllByOrder(o.getId());
            OrderDTO orderDTO = CreateOrderDTO(orderItems, o);
            if(orderDTO != null)
                orderDTOS.add(orderDTO);
        }
        return orderDTOS;
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
}
