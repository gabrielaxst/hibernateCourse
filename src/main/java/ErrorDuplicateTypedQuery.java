
import entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class ErrorDuplicateTypedQuery {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hib");
		EntityManager em = emf.createEntityManager();

		try {
			/*
			 * Named query tem escopo global, por isso não podemos ter elas com nomes repetidos
			 */
			TypedQuery<Student> queryStu = em.createNamedQuery("findById", Student.class);
			queryStu.setParameter("id", 1);
			Student s = queryStu.getSingleResult();
			System.out.println(s);

		} catch (Exception e) {
			if (em.getTransaction() != null) {
				em.getTransaction().rollback();
			}
		} finally {
			em.close();
		}
	}

}
