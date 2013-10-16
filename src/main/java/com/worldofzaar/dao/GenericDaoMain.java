package com.worldofzaar.dao;

import com.worldofzaar.util.HibernateUtilMain;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Arrays;

public class GenericDaoMain<T> {

    T type;

    public GenericDaoMain(T type) {
        this.type = type;
    }

    public void add(T element) {
        Session session = HibernateUtilMain.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(element);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.out.println("add() Error = " + e.getCause());
            System.out.println(Arrays.deepToString(e.getStackTrace()));

        } finally {
            session.close();
        }
    }

    public void remove(T element) {
        Session session = HibernateUtilMain.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(element);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.out.println("remove() Error = " + e.getCause());
            System.out.println(Arrays.deepToString(e.getStackTrace()));

        } finally {
            session.close();
        }
    }

    public T find(Integer id) {
        try {
            Session session = HibernateUtilMain.getSessionFactory().openSession();
            T element = (T) session.get(type.getClass(), id);
            session.close();
            return element;
        } catch (Exception e) {
            System.out.println("find() Error = " + e.getCause());
            System.out.println(Arrays.deepToString(e.getStackTrace()));
        }
        return null;
    }

    public void update(T element) {
        Session session = HibernateUtilMain.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(element);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.out.println("update() Error = " + e.getCause());
            System.out.println(Arrays.deepToString(e.getStackTrace()));
        } finally {
            session.close();
        }
    }
}
