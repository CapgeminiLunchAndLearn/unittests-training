package com.programmaniaks.training.unittest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.programmaniaks.training.unittest.entity.Greeting;

@Repository
public class GreetingDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public void create(Greeting greeting) {
		entityManager.persist(greeting);
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Greeting> findAll() {
		Query query = entityManager.createQuery("Select e from Greeting e");
		return (List<Greeting>) query.getResultList();
	}
}
