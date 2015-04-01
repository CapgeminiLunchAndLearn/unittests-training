package com.programmaniaks.training.unittest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.programmaniaks.training.unittest.entity.User;

@Repository
public class UserDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public void create(User user) {
		entityManager.persist(user);
	}
	
	@Transactional
	public void delete(User user) {
		entityManager.remove(user);
	}
	
	@Transactional
	public User update(User user) {
		return entityManager.merge(user);
	}
	
	public User find(Long id) {
		return entityManager.find(User.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		Query query = entityManager.createQuery("Select u from User u");
		return query.getResultList();
	}
	
	public User findByUsernameAndPassword(String username, String password) {
		try {
			Query query = entityManager.createQuery("Select u from User u where u.username = :username and u.password = :password");
			query.setParameter("username", username);
			query.setParameter("password", password);
			return (User) query.getSingleResult();
		} catch (NonUniqueResultException| NoResultException e) {
			// TODO : Mettre un logger
			e.printStackTrace();
			return null;
		}
		
	}

}
