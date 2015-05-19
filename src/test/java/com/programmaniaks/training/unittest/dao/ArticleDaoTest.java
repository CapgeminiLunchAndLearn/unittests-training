package com.programmaniaks.training.unittest.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.programmaniaks.training.unittest.entity.Article;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-test-context.xml")
@Transactional
public class ArticleDaoTest {

	@Autowired
	private ArticleDao articleDao;
	
	@Test
	public void testFindArticleWithMinQuantity() {
		Article article = new Article();
		article.setName("Produit test quantity");
		article.setPrice(5.0);
		article.setQuantity(0);
		article.setDescription("Produit pour test ArticleDaoTest.testFindArticleWithMinQuantity");
		articleDao.create(article);
		
		List<Article> articles = articleDao.findArticleWithMinQuantity();
		assertEquals(1, articles.size());
		assertEquals(article, articles.get(0));
	}
	
	@Test
	public void testFindArticleWithMinQuantity_TwoArticlesWithMinQuantity() {
		Article article1 = new Article();
		article1.setName("Produit test quantity");
		article1.setPrice(5.0);
		article1.setQuantity(0);
		article1.setDescription("Produit pour test ArticleDaoTest.testFindArticleWithMinQuantity");
		articleDao.create(article1);
		
		Article article2 = new Article();
		article2.setName("Produit test quantity 2");
		article2.setPrice(5.0);
		article2.setQuantity(0);
		article2.setDescription("Produit pour test ArticleDaoTest.testFindArticleWithMinQuantity");
		articleDao.create(article2);
		
		List<Article> articles = articleDao.findArticleWithMinQuantity();
		assertEquals(2, articles.size());
		assertEquals(article1, articles.get(0));
		assertEquals(article2, articles.get(1));
	}
	
	@Test
	public void testFindArticleWithMinQuantity_AllArticlesSameQuantity() {
		List<Article> articles = articleDao.findArticleWithMinQuantity();
		assertEquals(articleDao.findAll().size(), articles.size());
	}
	
	@Test
	public void testFindName() {
		
	}
	
}
