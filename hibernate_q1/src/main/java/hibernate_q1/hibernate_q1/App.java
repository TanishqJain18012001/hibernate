package hibernate_q1.hibernate_q1;

import configurations.HibernateUtils;
import entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        Employee employee = new Employee();
        employee.setEmpName("Tanishq Jain");
        employee.setEmpEmail("Tanishj75@gmail.com");

        Employee employee2 = new Employee();
        employee2.setEmpName("Rahul Sharma");
        employee2.setEmpEmail("rahul.accolite@gmail.com");

        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.persist(employee);
        session.persist(employee2);

        session.getTransaction().commit();

        System.out.println("Saved: ");
        session.createQuery("from Employee", Employee.class).list().forEach(System.out::println);
        session.close();

        System.out.println("Second session -: ");
        Session session2 = sessionFactory.openSession();
        session2.beginTransaction();

        //read operation
        Employee savedEmployee = session2.get(Employee.class, 1L);
        System.out.println(savedEmployee);

        //update operation
        savedEmployee.setEmpEmail("shalu.accolite@gmail.com");
        session2.merge(savedEmployee);

        session2.createQuery("from Employee", Employee.class).list().forEach(System.out::println);

        //delete operation
        Employee emp = session2.get(Employee.class, 2L);
        System.out.println(" Entity to be deleted : " + emp.getEmpName());
        session2.remove(emp);

        session2.getTransaction().commit();

        session2.createQuery("from Employee", Employee.class).list().forEach(System.out::println);

        session2.close();
    }
}