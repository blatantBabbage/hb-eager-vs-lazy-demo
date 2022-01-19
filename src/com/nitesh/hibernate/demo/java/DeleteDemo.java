package com.nitesh.hibernate.demo.java;

import com.nitesh.hibernate.demo.entity.Instructor;
import com.nitesh.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Instructor.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try {

            //begin the transaction
            System.out.println("starting transaction");
            session.beginTransaction();

            //get instructor by primary key, id
            int theId = 1;
            Instructor tempInstructor =session.get(Instructor.class, theId);

            //delete that instructor by id
            session.delete(tempInstructor);

            //commit transaction
            System.out.println("committing to the database");
            session.getTransaction().commit();

            System.out.println("done");

        }
        finally {
            factory.close();
        }
    }
}
