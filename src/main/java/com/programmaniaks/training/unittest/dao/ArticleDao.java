package com.programmaniaks.training.unittest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Repository;

import com.programmaniaks.training.unittest.entity.Article;

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
		Query query = entityManager.createQuery("Select a from Article a");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Article> findArticleWithMinQuantity() {
		Query query = entityManager
				.createQuery("Select a from Article a where a.quantity = (select min(b.quantity) from Article b)");
		return query.getResultList();
	}

	public List<Article> findName(String string) {
		throw new NotYetImplementedException("ArticleDao.findName");
	}

}
