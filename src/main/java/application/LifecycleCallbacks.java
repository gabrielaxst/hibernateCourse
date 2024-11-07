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
			
			AccountBank ac1 = new AccountBank(80000.0);
			
			em.persist(ac1);
			
			/*
			 * System.out.println("isso vai executar antes do método PostPersist,");
			 * System.out.println("pois a geração do id é com sequence." +
			 * "e o insert só é feito ao commit");
			 */
			
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
