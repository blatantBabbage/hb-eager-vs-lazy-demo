package com.nitesh.hibernate.demo.java;

import com.nitesh.hibernate.demo.entity.Course;
import com.nitesh.hibernate.demo.entity.Instructor;
import com.nitesh.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorCourseDemo {

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

            // get the instructor from the db
            System.out.println("getting instructor obj");
            int theId = 1;
            Instructor tempInstructor = session.get(Instructor.class, theId);
            System.out.println("instructor retrieved : " + tempInstructor);

            // get the courses corresponding to the instructor
            System.out.println("Courses retrieved : " + tempInstructor.getCourse());

            //commit transaction
            System.out.println("committing to the database");
            session.getTransaction().commit();

            System.out.println("done");

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
