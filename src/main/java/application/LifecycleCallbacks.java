package application;

import entity.AccountBank;
import entity.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class LifecycleCallbacks {
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hib");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tran = em.getTransaction();
		
		try {
			tran.begin();
			
			AccountBank ac1 = new AccountBank(new Client(null, "Maria Arcari"), 4000.0);
			
			em.persist(ac1);
			//em.flush();
			/**
			 * esse método irá executar antes da variável 'save' ser setada
			 * pois quando o id de uma entidade é gerado com sequence
			 * o comando de insert só é feito ao realizar o commit()
			 * então o método com anotação PostPersist não será executado logo 
			 * após o método de persist ser executado, mas no de commit()
			 * podemos utilizar o flush() para contornar
			 */
			ac1.validarAmountAcc();
		
			
			tran.commit();
			
		} catch (Exception e) {
			if (tran != null) {
				tran.rollback();
			}
		} finally {
			em.close();
		}
	}
	
	public static String alteraNome(String nome) {
		String nomeAlterado = "Cliente -> " + nome;
		return nomeAlterado;
	}
}
