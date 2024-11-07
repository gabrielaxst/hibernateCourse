package application;

import entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class FlushRefresh {
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hib");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tran = em.getTransaction();
		
		try {
			tran.begin();

			/**
			 * refresh -> atualiza o contexto de persistência 
			 * de acordo com o que está no banco
			 */
			Student student = em.find(Student.class, 1);
			student.setNome("teste.");
			
			System.out.println(student.getNome());
			
			/*
			 * refresh faz o select do banco da entidade passada como parâmetro.
			 */
			em.refresh(student);
			
			/*
			 * o nome não será alterado, devido ao refresh, que atualizou o 'student'
			 * com os dados do banco  
			 */
			System.out.println(student.getNome());
			
			student.setCurso("alterando curso.");
			
			/**
			 * flush -> faz o mesmo pepel do commit, atualizando o banco 
			 * de acordo com o contexto de persistência
			 * então irá atualizar o curso do student no banco
			 */
			em.flush();
			
			/* tirando a entidade do contexto de persistência para poder fazer um 
			 * select ao banco
			 */
			em.detach(student);
			
			student = em.find(Student.class, 1);
			
			System.out.println(student);
			
			tran.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			if (tran !=  null) {
				tran.rollback();
			}
		} finally {
			em.close();
		}
	}
}
