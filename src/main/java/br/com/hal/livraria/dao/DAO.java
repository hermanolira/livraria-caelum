package br.com.hal.livraria.dao;

import javax.persistence.EntityManager;

public class DAO<T> {

	private final Class<T> classe;

	public DAO(Class<T> classe) {
		this.classe = classe;
	}

	public void adiciona(T t) {
		
		// consegue a entity manager
		EntityManager em = new JPAUtil().getEntityManager();
		
		// abre transação
		em.getTransaction().begin();
		
		// persiste o objeto
		em.persist(t);
		
		// commita a transação
		em.getTransaction().commit();
		
		// fecha a entity manager
		em.close();
	}

}
