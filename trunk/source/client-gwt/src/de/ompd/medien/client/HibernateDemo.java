package de.ompd.medien.client;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import de.ompd.medien.server.dto.Film;
import de.ompd.medien.server.util.HibernateUtil;
 
public class HibernateDemo
{
    public static void main(String[] args)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

 
        //Film object0 = new Film();
        //Film object1 = new Film();
        //session.save(object0);
        //session.save(object1);
        List filme = session.createQuery("from Film").list();
        
        Film object0 = (Film) filme.get(0);
        Film object1 = (Film) filme.get(1);
 
        transaction.commit();
 
        System.out.println("Object 0");
        System.out.println("Generated ID is: " + object0.getId());
        System.out.println("Generated Version is: " + object0.getName());
 
        System.out.println("Object 1");
        System.out.println("Generated ID is: " + object1.getId());
        System.out.println("Generated Version is: " + object1.getName());
    }
}