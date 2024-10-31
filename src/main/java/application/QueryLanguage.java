package application;
import java.util.List;

import entity.Guide;
import entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class QueryLanguage {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hib");
		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			
			//JPQL			
			List<Guide> guides = null;

			StringBuilder jpql = new StringBuilder();
			jpql.append(" select guide from Guide guide ");
			
			Query query = em.createQuery(jpql.toString(), Guide.class);
			
			guides = query.getResultList();
			
			System.out.println();
			System.out.println(guides);

			
			query = em.createQuery(" select guide.name from Guide as guide ");
			List<String> guidesName = null;

			guidesName = query.getResultList();
			
			System.out.println();
		System.out.println(guidesName);
			
			/*
			 * Quando o select é de mais de uma coluna, é retornado um array de objects
			 * para cada registro.
			*/
			
			query = em.createQuery(" select s.nome, s.curso, g.name from Student s, Guide g where g.id = s.guide.id ");
			
			List<Object> rows = query.getResultList();
			
			for (Object object : rows) {
				Object[] record = (Object[]) object;
				System.out.println((String)record[0] + " - " + (String)record[1] + " - " + (String)record[2]);
			}
			
			//Dynamic parameter
			String inputNome = "Carlos Souza";
			 
			query = em.createQuery("select s.nome from Student s where s.nome =" + "'" + inputNome + "'");

			String r = (String) query.getSingleResult();
			
			System.out.println(r);
			
			//Named Parameter
			Student s = em.createQuery("select s from Student s where s.nome = :nome", Student.class)
					.setParameter("nome", inputNome).getSingleResult();

			System.out.println(s);
			
			String inputLetra = "J";
			List<Student> students = em.createQuery("select s from Student s where s.nome like :letra", Student.class)
					.setParameter("letra", "%" + inputLetra + "%").getResultList();
			
			System.out.println();
			System.out.println(students);
			
			//Aggregate functions com jpql
			Long qtdStudents = (Long) em.createQuery(" select count(s) from Student s where s.nome like :letra ")
					.setParameter("letra", "%J%").getSingleResult();
			
			System.out.println();
			System.out.println(qtdStudents);
			
			//Native query
			students = em.createNativeQuery(" select * from student s where s.nome like ?1 ", Student.class)
					.setParameter(1, "%J%").getResultList();
			System.out.println();

			System.out.println(students);
			
			//JOINS com jpql
			Query q = em.createQuery(" select s from Student s left join s.guide guide ");
			System.out.println(q.getResultList());
			
			q = em.createQuery(" select s from Student s join s.guide guide ");
			System.out.println(q.getResultList());
			
			q = em.createQuery(" select s from Student s right join s.guide guide ");
			System.out.println(q.getResultList());
		
			q = em.createQuery(" select g from Guide g join g.students student ");
			q.getResultList();
			
			//Mudando o fetch lazy para eager apenas para um select específico
			q = em.createQuery(" select g from Guide g join fetch g.students student ");
			q.getResultList();
			
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction() != null) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
}
