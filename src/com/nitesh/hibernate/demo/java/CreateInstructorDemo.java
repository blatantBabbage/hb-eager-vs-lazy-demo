package com.nitesh.hibernate.demo.java;

import com.nitesh.hibernate.demo.entity.Course;
import com.nitesh.hibernate.demo.entity.Instructor;
import com.nitesh.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try {
            //create the obj
            Instructor tempInstructor = new Instructor("Nitesh", "Singh", "nitesh@codes.com");
            InstructorDetail tempInstructorDetail = new InstructorDetail("codesnull", "timepass");

            //associate the obj
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            //begin the transaction
            System.out.println("starting transaction");
            session.beginTransaction();

            //save obj
            //Note: this operation will also save tempInstructorDetail obj
            //because of CascadeType
            System.out.println("saving instructor and instructorDetail");
            session.save(tempInstructor);

            //commit transaction
            System.out.println("committing to the database");
            session.getTransaction().commit();

            System.out.println("done");

        }
        finally {

            // clean up code

            session.close();
            factory.close();
        }
    }
}
