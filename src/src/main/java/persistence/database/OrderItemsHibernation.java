package persistence.database;

import models.OrderItem;
import models.Tuple;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import persistence.interfaces.OrderItemsRepoInterface;
import persistence.interfaces.UsersRepoInterface;

import java.util.ArrayList;
import java.util.List;

public class OrderItemsHibernation implements OrderItemsRepoInterface {
    private SessionFactory sessionFactory = null;

    public OrderItemsHibernation(SessionFactory ss) {
        sessionFactory = ss;
    }

    @Override
    public OrderItem findOne(Integer aLong) {
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                OrderItem oi = session.createQuery("from OrderItem as u where u.id is " + aLong.toString(), OrderItem.class).setMaxResults(1).uniqueResult();
                tx.commit();
                return oi;
            }
            catch (RuntimeException ex){
                if (tx != null)
                    tx.rollback();
            }
        }
        return null;
    }

    public OrderItem save(OrderItem oi)
    {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                session.save(oi);
                tx.commit();
            }
            catch (RuntimeException ex)
            {
                if (tx != null)
                    tx.rollback();
            }
        }
        return null;
    }

    @Override
    public OrderItem delete(Integer aLong) {
        try(Session session = sessionFactory.openSession())
        {
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                OrderItem e = session.createQuery("from OrderItem where u.id is " + aLong.toString(), OrderItem.class).uniqueResult();
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
    public OrderItem update(OrderItem entity)
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
    public Iterable<OrderItem> findAll()
    {
        try(Session session = sessionFactory.openSession())
        {
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                List<OrderItem> o =
                        session.createQuery("from OrderItem as oi order by oi.id asc", OrderItem.class)
                                .list();
                tx.commit();
                return o;
            } catch (RuntimeException e) {
                if(tx != null)
                {
                    tx.rollback();
                }
            }
        }
        return new ArrayList<>();
    }

    public Iterable<OrderItem> findAllByOrder(Integer id)
    {
        try(Session session = sessionFactory.openSession())
        {
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                List<OrderItem> o =
                        session.createQuery("from OrderItem as u where u.oid is " + id.toString(), OrderItem.class)
                                .list();
                tx.commit();
                return o;
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
