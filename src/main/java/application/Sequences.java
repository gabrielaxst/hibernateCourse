package application;

import java.util.ArrayList;
import java.util.List;

import entity.Guide;
import entity.Student;
import entity.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Sequences {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hib");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tran = em.getTransaction();
		try {
			tran.begin();
			List<String> subjects = new ArrayList<String>();
			
			subjects.add("Matemática");
			subjects.add("Física");

			Teacher teacher = new Teacher(null, "Maicon Selen", subjects);
			
			em.persist(teacher);
			
			System.out.println("persistido!");
			
			tran.commit();
			
		} catch (Exception e) {
			if (tran != null) {
				tran.rollback();
			}
		} finally {
			em.close();
		}
	}
}
