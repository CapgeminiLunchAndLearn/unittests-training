package com.programmaniaks.training.unittest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.programmaniaks.training.unittest.entity.Article;
import com.programmaniaks.training.unittest.entity.User;

@Repository
public class ArticleDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public void create(Article article) {
		entityManager.persist(article);
	}
	
	@Transactional
	public void delete(Article article) {
		entityManager.remove(article);
	}
	
	@Transactional
	public Article update(Article article) {
		return entityManager.merge(article);
	}
	
	public Article find(Long id) {
		return entityManager.find(Article.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Article> findAll() {
		Query query = entityManager.createQuery("Select u from User u");
		return query.getResultList();
	}

}
