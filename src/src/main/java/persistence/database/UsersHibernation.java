package persistence.database;

import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import persistence.interfaces.UsersRepoInterface;

import java.util.ArrayList;
import java.util.List;

public class UsersHibernation implements UsersRepoInterface {
    private SessionFactory sessionFactory = null;

    public UsersHibernation(SessionFactory ss) {
        sessionFactory = ss;
    }

    @Override
    public User findOne(Integer aLong) {
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                User user = session.createQuery("from User as u where u.id is " + aLong, User.class).setMaxResults(1).uniqueResult();
                tx.commit();
                return user;
            }
            catch (RuntimeException ex){
                if (tx != null)
                    tx.rollback();
            }
        }
        return null;
    }

    public User save(User user)
    {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                session.save(user);
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
    public User delete(Integer aLong) {
        try(Session session = sessionFactory.openSession())
        {
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                User usr = session.createQuery("from User where id is " + aLong.toString(), User.class).uniqueResult();
                session.delete(usr);
                tx.commit();
                return usr;
            } catch (RuntimeException e) {
                if(tx != null)
                {
                    tx.rollback();
                }
            }
        }
        return null;
    }


    public User findOneByUsername(String username)
    {
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                User user = session.createQuery("from User as u where u.username is \'" + username + "\'", User.class).setMaxResults(1).uniqueResult();
                tx.commit();
                return user;
            }
            catch (RuntimeException ex){
                if (tx != null)
                    tx.rollback();
            }
        }
        return null;
    }

    @Override
    public User update(User entity)
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
    public Iterable<User> findAll()
    {
        try(Session session = sessionFactory.openSession())
        {
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                List<User> users =
                        session.createQuery("from User as u order by u.id asc", User.class)
                        .list();
                tx.commit();
                return users;
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
