package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClientDemo {

    public static void main(String[] args) {
        // Load Hibernate configuration
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = cfg.buildSessionFactory();

        // Update operation using HQL with positional parameters
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            // HQL query for update
            String hql = "UPDATE Department SET name = ?1, location = ?2 WHERE id = ?3";
            int updatedEntities = session.createQuery(hql)
                    .setParameter(1, "Updated Department Name")
                    .setParameter(2, "Updated Location")
                    .setParameter(3, 1) // Assuming ID 1 exists
                    .executeUpdate();

            System.out.println("Number of entities updated: " + updatedEntities);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}
