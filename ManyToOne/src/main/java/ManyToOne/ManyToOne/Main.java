package ManyToOne.ManyToOne;

import entities.State;
import entities.Country;
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
            // Create a country
            Country country = new Country("India");

            // Create states
            State state1 = new State("Maharashtra");
            State state2 = new State("Karnataka");

            // Associate states with the country
            country.addState(state1);
            country.addState(state2);

            // Save country (cascade will save states)
            session.persist(country);

            // Commit transaction
            session.getTransaction().commit();

            // Print confirmation message
            System.out.println("Country and States saved successfully.");
            System.out.println("Country ID: " + country.getCountryId());
            System.out.println("Country Name: " + country.getName());
            System.out.println("States:");

            // Print the states associated with the country
            for (State state : country.getStates()) {
                System.out.println("- " + state.getName());
            }
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
