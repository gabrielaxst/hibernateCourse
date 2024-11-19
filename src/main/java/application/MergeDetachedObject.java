package application;

import java.util.List;

import entity.Guide;
import entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class MergeDetachedObject {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hib");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			
			Guide guide = em.find(Guide.class, 13);
			
			Student student = guide.getStudents().get(0);
			
			transaction.commit();
			em.close();
			
			guide.setName("alteração na entidade guide");
			student.setCurso("alteração na entidade student");
			
			EntityManager em2 = emf.createEntityManager();
			em2.getTransaction().begin();
			
			em2.merge(guide);
			
			em2.getTransaction().commit();
			em2.close();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
}
