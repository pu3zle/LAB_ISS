package client.controllers;

import models.User;
import models.UserType;
import persistence.database.DrugsHibernation;
import persistence.database.OrderItemsHibernation;
import persistence.database.OrdersHibernation;
import persistence.database.UsersHibernation;

public class LoginController {
    private UsersHibernation usersHibernation;
    private DrugsHibernation drugsHibernation;
    private OrderItemsHibernation orderItemsHibernation;
    private OrdersHibernation ordersHibernation;
    private User currentUser;

    public LoginController(UsersHibernation usersHibernation, DrugsHibernation drugsHibernation,
                            OrderItemsHibernation oih, OrdersHibernation ordersHibernation) {
        this.usersHibernation = usersHibernation;
        this.drugsHibernation = drugsHibernation;
        this.orderItemsHibernation = oih;
        this.ordersHibernation = ordersHibernation;
    }

    public UsersHibernation getUsersHibernation() {
        return usersHibernation;
    }

    public User Login(String username, String pass){
        Iterable<User> users =  usersHibernation.findAll();
        for(User u : users)
        {
            if(u.getUsername().equals(username))
            {
                if(u.getPassword().equals(pass))
                {
                    currentUser = u;
                    return u;
                }
            }
        }
        return null;
    }

    public AdminController createAdminController()
    {
        AdminController adminController = new AdminController(drugsHibernation);
        return adminController;
    }

    public PharmacistController createPharmacistController()
    {
        PharmacistController pharmacistController = new PharmacistController(drugsHibernation, ordersHibernation, orderItemsHibernation, currentUser);
        return pharmacistController;
    }

    public MedPersController createMedPersController()
    {
        MedPersController medPersController = new MedPersController(drugsHibernation, orderItemsHibernation, ordersHibernation,currentUser);
        return medPersController;
    }
}
