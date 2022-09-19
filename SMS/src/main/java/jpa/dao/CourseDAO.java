package jpa.dao;
import java.util.List;
import jpa.entitymodels.Course;


public interface CourseDAO {
	
	enum CourseSQL{
		get_All_Courses("FROM course c "),
		get_Course_By_Id("from course c  WHERE c.cId =:cId");
		private final String query;
		
		//Enumeration constructor
		private CourseSQL(String query_string) {
			this.query = query_string;
		}
		
		public String getQuery() {
			return query;
		}
	}
	//Queries the course tables and returns all courses records as Course objects
		//@return
		List<Course> getAllCourses();
}
