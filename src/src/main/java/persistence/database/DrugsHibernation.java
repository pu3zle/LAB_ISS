package persistence.database;

import models.Drug;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import persistence.interfaces.DrugsRepoInterface;
import persistence.interfaces.UsersRepoInterface;

import java.util.ArrayList;
import java.util.List;

public class DrugsHibernation implements DrugsRepoInterface {
    private SessionFactory sessionFactory = null;

    public DrugsHibernation(SessionFactory ss) {
        sessionFactory = ss;
    }

    @Override
    public Drug findOne(Integer aLong) {
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                Drug drug = session.createQuery("from Drug as d where d.id is " + aLong, Drug.class).setMaxResults(1).uniqueResult();
                tx.commit();
                return drug;
            }
            catch (RuntimeException ex){
                if (tx != null)
                    tx.rollback();
            }
        }
        return null;
    }

    public Drug save(Drug drug)
    {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                session.save(drug);
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
    public Drug delete(Integer aLong) {
        try(Session session = sessionFactory.openSession())
        {
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                Drug drg = session.createQuery("from Drug where id is " + aLong.toString(), Drug.class).uniqueResult();
                session.delete(drg);
                tx.commit();
                return drg;
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
    public Drug update(Drug entity)
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
    public Iterable<Drug> findAll()
    {
        try(Session session = sessionFactory.openSession())
        {
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                List<Drug> drugs =
                        session.createQuery("from Drug as u order by u.id asc", Drug.class)
                                .list();
                tx.commit();
                return drugs;
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
