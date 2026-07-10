package org.example;

import com.example.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        boolean isRunning = true;

        while (isRunning) {

            System.out.println("\n========== STUDENT CRUD MENU ==========");
            System.out.println("1. Add Student Data");
            System.out.println("2. View Student Data");
            System.out.println("3. Update Student Data ");
            System.out.println("4. Delete Student Data");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addStudent(factory);
                    break;

                case 2:
                    viewStudent(factory);
                    break;

                case 3:
                    updateStudent(factory);
                    break;

                case 4:
                    deleteStudent(factory);
                    break;

                case 5:
                    isRunning = false;
                    System.out.println("\nApplication Closed.");
                    break;

                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }

        factory.close();
        scanner.close();
    }

    // CREATE
    private static void addStudent(SessionFactory factory) {

        System.out.print("\nEnter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        Student student = new Student(firstName, lastName, email);

        Session session = factory.openSession();

        try {

            session.beginTransaction();

            session.persist(student);

            session.getTransaction().commit();

            System.out.println("\nStudent Added Successfully!");
            System.out.println(student);

        } finally {
            session.close();
        }
    }

    // READ
    private static void viewStudent(SessionFactory factory) {

        System.out.print("\nEnter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Session session = factory.openSession();

        try {

            session.beginTransaction();

            Student student = session.get(Student.class, id);

            if (student != null) {

                System.out.println("\nStudent Details:");
                System.out.println(student);

            } else {

                System.out.println("\nStudent not found.");
            }

            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }

    // UPDATE
    private static void updateStudent(SessionFactory factory) {

        System.out.print("\nEnter student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Session session = factory.openSession();

        try {

            session.beginTransaction();

            Student student = session.get(Student.class, id);

            if (student != null) {

                System.out.println("\nCurrent Details:");
                System.out.println(student);

                System.out.print("\nEnter new first name: ");
                String firstName = scanner.nextLine();

                System.out.print("Enter new last name: ");
                String lastName = scanner.nextLine();

                System.out.print("Enter new email: ");
                String email = scanner.nextLine();

                student.setFirstName(firstName);
                student.setLastName(lastName);
                student.setEmail(email);

                session.merge(student);

                System.out.println("\nStudent Updated Successfully!");

            } else {

                System.out.println("\nStudent not found.");
            }

            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }

    // DELETE
    private static void deleteStudent(SessionFactory factory) {

        System.out.print("\nEnter student ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Session session = factory.openSession();

        try {

            session.beginTransaction();

            Student student = session.get(Student.class, id);

            if (student != null) {

                System.out.println("\nStudent Found:");
                System.out.println(student);

                System.out.print("\nDo you really want to delete this student? (yes/no): ");
                String confirmation = scanner.nextLine();

                if (confirmation.equalsIgnoreCase("yes")) {

                    session.remove(student);

                    System.out.println("\nStudent Deleted Successfully!");

                } else {

                    System.out.println("\nDelete operation cancelled.");
                }

            } else {

                System.out.println("\nStudent not found.");
            }

            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }
}