import entity.Guide;
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
		
		} catch (Exception e) {
			if (em.getTransaction() != null) {
				em.getTransaction().rollback();
			}
		} finally {
			em.clear();
		}
	}

}
