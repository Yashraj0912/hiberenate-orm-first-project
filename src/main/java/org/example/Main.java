package org.example;

import com.example.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        Session session = factory.openSession();

        try {
            Student student = new Student(firstName, lastName, email);

            session.beginTransaction();
            session.persist(student);
            session.getTransaction().commit();

            System.out.println("Student saved: " + student);

        } finally {
            session.close();
            factory.close();
            scanner.close();
        }
    }
}