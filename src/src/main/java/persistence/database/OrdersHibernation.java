package persistence.database;

import models.Drug;
import models.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import persistence.interfaces.OrdersRepoInterface;

import java.util.ArrayList;
import java.util.List;

public class OrdersHibernation implements OrdersRepoInterface {
    private SessionFactory sessionFactory = null;

    public OrdersHibernation(SessionFactory ss) {
        sessionFactory = ss;
    }

    @Override
    public Order findOne(Integer aLong) {
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                Order e = session.createQuery("from Order as d where d.id is " + aLong, Order.class).setMaxResults(1).uniqueResult();
                tx.commit();
                return e;
            }
            catch (RuntimeException ex){
                if (tx != null)
                    tx.rollback();
            }
        }
        return null;
    }

    public Order save(Order e)
    {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                session.save(e);
                tx.commit();
            }
            catch (RuntimeException ex)
            {
                if (tx != null)
                    tx.rollback();
                throw new RuntimeException("Couldn't add entity");
            }
        }
        return null;
    }

    @Override
    public Order delete(Integer aLong) {
        try(Session session = sessionFactory.openSession())
        {
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                Order e = session.createQuery("from Order where id is " + aLong.toString(), Order.class).uniqueResult();
                session.delete(e);
                tx.commit();
                return e;
            } catch (RuntimeException e) {
                if(tx != null)
                {
                    tx.rollback();
                }
            }
        }
        return null;
    }

    @Override
    public Order update(Order entity)
    {
        try(Session session = sessionFactory.openSession())
        {
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                session.update(entity);
                tx.commit();
                return entity;
            } catch (RuntimeException e) {
                if(tx != null)
                {
                    tx.rollback();
                }
            }
        }
        return null;
    }

    @Override
    public Iterable<Order> findAll()
    {
        try(Session session = sessionFactory.openSession())
        {
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                List<Order> e =
                        session.createQuery("from Order as o order by o.id asc", Order.class)
                                .list();
                tx.commit();
                return e;
            } catch (RuntimeException e) {
                if(tx != null)
                {
                    tx.rollback();
                }
            }
        }
        return new ArrayList<>();
    }

    public Iterable<Order> findAllByUser(Integer id)
    {
        try(Session session = sessionFactory.openSession())
        {
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                List<Order> e =
                        session.createQuery("from Order as u where u.requester is " + id.toString(), Order.class)
                                .list();
                tx.commit();
                return e;
            } catch (RuntimeException e) {
                if(tx != null)
                {
                    tx.rollback();
                }
            }
        }
        return new ArrayList<>();
    }
}
