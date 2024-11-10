package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entity.Guide;
import entity.Student;
import entity.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import listenner.TeacherListenner;

public class EntityListenner {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hib");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		try {
			transaction.begin();
			
//			List<String> subjects = new ArrayList<String>();
//			subjects.add("Matemática");
//			subjects.add("Física");
//			
//			Teacher teacher = new Teacher(null, "Marcos Allan", subjects);
//			
//			em.persist(teacher);
//			
//			teacher.setName("Marco A.");
			
//			Teacher teacher = em.find(Teacher.class, 1);
//			em.remove(teacher);
//			
//			if (TeacherListenner.allowRemove == false) {
//				transaction.rollback();
//				System.out.println("entidade não removida.");
//			}else {
//				transaction.commit();
//			}
			
			Student student = new Student(null, "Carine Antunes", "ADAS", 
					LocalDate.of(1999, 1, 28), 1);
			
			em.persist(student);
			
			Guide guide = new Guide("Caio Antunes");
			
			em.persist(guide);
			
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			em.close();
		}
	}
}
