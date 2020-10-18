package br.com.dao;

import java.util.List;
import javax.persistence.EntityManager;

import br.com.db.DB;
import br.com.db.DbException;
import br.com.model.Personagem;

public class PersonagemDAO implements InterfaceDAO<Personagem> {

	private EntityManager em = null;

	public PersonagemDAO() {
		this.em = DB.getEntityManager();
	}

	@Override
	public boolean insert(Personagem t) {
		System.out.println("Insert");
		try {
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DbException(e.getMessage());
		}
		return true;
	}

	@Override
	public Personagem findById(int id) {
		System.out.println("findById");

		try {
			Personagem p = em.find(Personagem.class, id);
			if (p != null) {
				return p;
			}
		} catch (Exception e) {
			System.out.println("vish..." + e.getMessage());
			throw new DbException(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Personagem> findAll() {
		System.out.println("findAll");

		try {
			return em.createQuery("FROM " + Personagem.class.getName()).getResultList();
		} catch (Exception e) {
			System.out.println("vix... " + e.getMessage());
			throw new DbException(e.getMessage());
		}
	}

	@SuppressWarnings("finally")
	@Override
	public boolean update(Personagem t) {
		System.out.println("update");
		try {
			em.getTransaction().begin();
			em.merge(t);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DbException(e.getMessage());
		} finally {
			return true;
		}
	}

	@Override
	public boolean delete(Personagem t) {
		System.out.println("delete");
		try {
			this.em.getTransaction().begin();
			this.em.remove(t);
			this.em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
