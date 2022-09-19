package jpa.service;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.elena.project.sba.AbstractConnDAO;

import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;

public class CourseService extends AbstractConnDAO implements CourseDAO {

	public CourseService() {
		
	}

	/**
	 * This method takes no parameter and returns every Course in the table.
	 */
	public List<Course> getAllCourses() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String hql = "SELECT c FROM Course c";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Course> list = query.getResultList();

		t.commit();
		session.close();
		factory.close();
		return list;
	}

	/**
	 * This method takes a Student’s id and returns a Student Registered Courses.
	 * 
	 * @param id
	 * @return
	 */
	public Course getCourseById(int id) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String hql = "SELECT c FROM Course c Where c.id =?1";
		@SuppressWarnings("unchecked")
		TypedQuery<Course> courseQ = session.createQuery(hql);
		courseQ.setParameter(1, id);
		Course rs = courseQ.getSingleResult();
		t.commit();
		session.close();
		factory.close();
		return rs;
	}

}
