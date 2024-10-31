package application;
import entity.Guide;
import entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class TypedQueryLanguage {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hib");
		EntityManager em = emf.createEntityManager();
		
		try {
			TypedQuery<Guide> query = em.createNamedQuery("Guide.findById", Guide.class);
			query.setParameter("id", 1);
			Guide g = query.getSingleResult();
			System.out.println(g);
			
			TypedQuery<Student> queryStu =  em.createNamedQuery("findById", Student.class);
			queryStu.setParameter("id", 1);
			Student s = queryStu.getSingleResult();
			System.out.println(s);
		
		} catch (Exception e) {
			if (em.getTransaction() != null) {
				em.getTransaction().rollback();
			}
		} finally {
			em.clear();
		}
	}

}
