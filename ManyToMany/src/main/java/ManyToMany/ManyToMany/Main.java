package ManyToMany.ManyToMany;

import entities.Student;
import entities.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import configurations.HibernateUtils;

public class Main {
    public static void main(String[] args) {
        // Create session factory
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

        // Create session
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try {
            // Create students
            Student student1 = new Student("Alice");
            Student student2 = new Student("Bob");

            // Create courses
            Course course1 = new Course("Math");
            Course course2 = new Course("Physics");

            // Enroll students in courses
            course1.addStudent(student1);
            course1.addStudent(student2);
            course2.addStudent(student1);

            // Save students and courses
            session.persist(student1);
            session.persist(student2);
            session.persist(course1);
            session.persist(course2);

            // Commit transaction
            session.getTransaction().commit();

            // Print confirmation message
            System.out.println("Students and Courses saved successfully.");

            // Print saved students and courses
            System.out.println("Saved Students:");
            System.out.println("Student ID: " + student1.getStudentId() + ", Name: " + student1.getName());
            System.out.println("Student ID: " + student2.getStudentId() + ", Name: " + student2.getName());

            System.out.println("Saved Courses:");
            System.out.println("Course ID: " + course1.getCourseId() + ", Name: " + course1.getName());
            System.out.println("Course ID: " + course2.getCourseId() + ", Name: " + course2.getName());
        } catch (Exception e) {
            // Rollback transaction in case of error
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            // Close session and session factory
            session.close();
            sessionFactory.close();
        }
    }
}
