package com.nitesh.hibernate.demo.java;

import com.nitesh.hibernate.demo.entity.Course;
import com.nitesh.hibernate.demo.entity.Instructor;
import com.nitesh.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseDemo {

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
            System.out.println("getting instructor obj from db to add course");
            int theId = 1;
            Instructor tempInstructor = session.get(Instructor.class, theId);

            // create some courses
            System.out.println("creating course");
            Course tempCourse1 = new Course("Guitar");
            Course tempCourse2 = new Course("DS Algo");

            // add courses to instructor
            System.out.println("adding course");
            tempInstructor.add(tempCourse1);
            tempInstructor.add(tempCourse2);

            // save the courses
            System.out.println("saving course");
            session.save(tempCourse1);
            session.save(tempCourse2);

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
