package jpa.service;
import org.junit.*;
import static org.junit.Assert.*;
import jpa.entitymodels.Student;

public class StudentServiceTest {
	private static StudentService studentService;

	// private static CourseService courseService;

	@BeforeClass
	public static void setUp() {
		studentService = new StudentService();
		// courseService = new CourseService();
	}

	@Test
	public void testGetStudentByEmail() {
		Student expected = new Student();
		expected.setsEmail("aiannitti7@is.gd");
		expected.setsName("Alexandra Iannitti");
		expected.setsPass("TWP4hf5j");
		Student actual = studentService.getStudentByEmail("aiannitti7@is.gd");	
		assertEquals(expected, actual);	
	}
}
