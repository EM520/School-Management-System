package jpa.mainrunner;
import java.util.ArrayList;
import java.util.List;
//import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import static java.lang.System.out;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
//import jpa.entitymodels.Student;
import jpa.service.CourseService;
import jpa.service.StudentService;
public class SMSRunner {
	private Scanner input;
	private CourseService courseService;
	private StudentService studentService;
	public SMSRunner() {
		input = new Scanner(System.in);
		courseService = new CourseService();
		studentService = new StudentService();
	}

	public static void main(String[] args) {
		SMSRunner sms = new SMSRunner();
		sms.createTable();
		sms.run();
	}

	public void createTable() {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		// Inserting the sample course data, where the course id auto increments
		Course c1 = new Course("English", "Anderea Scamaden");
		session.persist(c1);
		Course c2 = new Course("Mathematics", "Eustace Niemetz");
		session.persist(c2);
        session.persist(new Course("Anatomy", "Reynolds Pastor"));
        session.persist(new Course("Organic Chemistry", "Odessa Belcher"));
        session.persist(new Course("Physics", "Dani Swallow"));
        session.persist(new Course("Digital Logic", "Glenden Reilingen"));
        session.persist(new Course("Object Oriented Programming", "Giselle Ardy"));
        session.persist(new Course("Data Structures", "Carolan Stoller"));
        session.persist(new Course("Politics", "Carmita De Maine"));
        session.persist(new Course("Art", "Kingsly Doxsey"));
		
     // Inserting the Student data
        Student s1= new Student("aiannitti7@is.gd", "Alexandra Iannitti", "TWP4hf5j");
        session.persist(s1);
		session.persist(new Student("hluckham0@google.ru", "Hazel Luckham", "X1uZcoIh0dj"));
		session.persist(new Student("sbowden1@yellowbook.com", "Sonnnie Bowden", "SJc4aWSU"));
		session.persist(new Student("qllorens2@howstuffworks.com", "Quillan Llorens", "W6rJuxd"));
		session.persist(new Student("cstartin3@flickr.com", "Clem Startin", "XYHzJ1S"));
		session.persist(new Student("tattwool4@biglobe.ne.jp", "Thornie Attwool", "Hjt0SoVmuBz"));
		session.persist(new Student("hguerre5@deviantart.com", "Harcourt Guerre", "OzcxzD1PGs"));
		session.persist(new Student("htaffley6@columbia.edu", "Holmes Taffley", "xowtOQ"));
		session.persist(new Student("ljiroudek8@sitemeter.com", "Laryssa Jiroudek", "bXRoLUP"));
		session.persist(new Student("cjaulme9@bing.com", "Cahra Jaulme", "FnVklVgC6r6"));
		
		// Created a course list 
		List<Course> cl1 = new ArrayList<Course>();
		cl1.add(c1);
		cl1.add(c2);
				
		// Add course lists to student Alexandra
		s1.setsCourses(cl1);
		t.commit();
		factory.close();
		session.close();
	}

	private void run() {
		// Login or quit
		switch (menu1()) {
		case 1:
			if (studentLogin()) {
				registerMenu();
			} else {
				while (!studentLogin()) {
					System.out.println("Please try again!");
					System.out.println();
				}
			}
			break;
		case 2:
			System.out.println("You have been signed out.");
			System.exit(0);
			break;
		}
	}

	private int menu1() {
		System.out.println("\nWelcome to School Management System!\n");
		System.out.println("*********************************");
		System.out.println("Are you a(n)\n1.Student \n2. LogOut \n Please Enter 1 or 2: ");
		return input.nextInt();
	}
	private String email;
	private String password;

	/**
	 * This login() allow the user to enter his/her email and password and check whether or not those credentials are valid, 
	 * in order to log in. If the credentials are invalid the program should end with an appropriate message to the student.
	 * Register to Class: This displays all the courses in the database and 
	 * allows the student to select a course in which the student wished to be registered.
	 */
	private boolean studentLogin() {
		boolean retValue = false;
		out.print("Enter your email: ");
		email = input.next();
		out.print("Enter your password: ");
		password = input.next();
		if (studentService.validateStudent(email, password)) {
			out.println("MyClasses:");
			out.printf("%-15s%-18s%-15s\n", "# COURSE", "NAME", "INSTRUCTOR");
			studentService.getStudentCourses(email);
			retValue = true;
			registerMenu();
		}
		return retValue;
	}

	/**
	 * Displays and prompt the student to select one of the following two additional numeric (1 or 2) 
	 * options that are available:Register to Class and Logout:
	 * @return
	 */
	private void registerMenu() {
		System.out.println("\n1.Register to Class\n2.Logout\n ");
		switch (input.nextInt()) {
		case 1:
			List<Course> allCourses = courseService.getAllCourses();
			out.println("All Classes:\n");
			out.printf("%-15s%-18s%-15s\n", "# COURSE", "NAME", "INSTRUCTOR");
			out.println();
			for (Course c : allCourses) {
				System.out.println(c);
			}
			out.println();
			out.print("Which Course? ");
			int number = input.nextInt();
			String newCourse = courseService.getCourseById(number).getcName();
			System.out.println(newCourse);
			out.println();
			studentService.registerStudentToCourse(email, number);
			out.println("MyClasses:\n");
			out.printf("%-15s%-18s%-15s\n", "# COURSE", "NAME", "INSTRUCTOR");
			out.println();
			studentService.getStudentCourses(email);
			registerMenu();
			break;
		case 2:
			System.out.println("You have been signed out.");
			System.exit(0);
		}
	}
}
