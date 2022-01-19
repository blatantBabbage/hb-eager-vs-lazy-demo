package com.nitesh.hibernate.demo.java;

import com.nitesh.hibernate.demo.entity.Course;
import com.nitesh.hibernate.demo.entity.Instructor;
import com.nitesh.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FetchJoinDemo {

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

            //begin the transaction
            System.out.println("starting transaction");
            session.beginTransaction();

            // fetching data with Hibernate query, HQL
            // get the instructor from the db
            System.out.println("getting instructor obj");
            int theId = 1;
            Query<Instructor> query = session.createQuery("select i from Instructor i "
            + "JOIN FETCH i.course "
            + "where i.id=:theInstructorId",
                    Instructor.class);
            // set the parameter on query
            query.setParameter("theInstructorId", theId);

            // execute query and get instructor result
            Instructor tempInstructor = query.getSingleResult();
            System.out.println("niteshBp: instructor retrieved : " + tempInstructor);

            //commit transaction
            System.out.println("committing to the database");
            session.getTransaction().commit();

            session.close();
            System.out.println("session is now closed!");

            // get the courses corresponding to the instructor
            System.out.println("niteshBp: Courses retrieved : " + tempInstructor.getCourse());

            System.out.println("niteshBp: done");

        }
        // handle exception
         catch (Exception exc) {
            exc.printStackTrace();
         }
        finally {

            // clean up code
            session.close();
            factory.close();
        }
    }
}
