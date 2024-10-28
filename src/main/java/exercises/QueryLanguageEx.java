package exercises;

import java.util.ArrayList;
import java.util.List;

import entity.Guide;
import entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class QueryLanguageEx {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hib");
		EntityManager em = emf.createEntityManager();
		
		/*
		 * select s.nome, s.curso from student s where s.guide_id is null;
		 */
		StringBuilder jpql = new StringBuilder();	
		
		jpql.append(" SELECT student.nome, student.curso FROM Student AS student ");
		jpql.append(" WHERE student.guide.id IS NULL ");
		
		Query query = em.createQuery(jpql.toString());
		
		List<Object[]> records = query.getResultList();
		List<Student> students = new ArrayList<Student>();
		
		for (Object[] object : records) {
			students.add(new Student((String)object[0], (String)object[1], null, null));
		}
		
		System.out.println(students);
		
		/*
		 * select g.name from guide g 
		 * where g.id not in (select s.guide_id from student s where s.guide_id is not null); 
		 */
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select g.name from guide g ");
		sql.append(" where g.id not in (select s.guide_id from student s where s.guide_id is not null) ");

		query = em.createNativeQuery(sql.toString());
		
		String guideName = (String) query.getSingleResult();
		
		System.out.println(guideName);
		
		/*
		 * select * from guide g 
		 * inner join student s on s.guide_id = g.id 
		 * and s.nome like 'C%';
		 */
		jpql = new StringBuilder();
		
		jpql.append(" SELECT g FROM Guide g ");
		jpql.append(" JOIN g.students student WHERE student.nome LIKE :letra ");
		
		query = em.createQuery(jpql.toString(), Guide.class);
		
		query.setParameter("letra", "C%");
		
		List<Guide> guides = query.getResultList();
		
		System.out.println(guides);
		
	}
}
