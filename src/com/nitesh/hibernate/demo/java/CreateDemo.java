package com.nitesh.hibernate.demo.java;

import com.nitesh.hibernate.demo.entity.Instructor;
import com.nitesh.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {

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
            //create the obj
            Instructor tempInstructor = new Instructor("Prince", "Singh", "vikas@codes.com");
            InstructorDetail tempInstructorDetail = new InstructorDetail("bikesandcars", "driving!!");

            //associate the obj
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            //begin the transaction
            System.out.println("starting transaction");
            session.beginTransaction();

            //save obj
            //Note: this operation will also save tempInstructorDetail obj
            //because of CascadeType.ALL
            System.out.println("saving instructor and instructorDetail");
            session.save(tempInstructor);

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
