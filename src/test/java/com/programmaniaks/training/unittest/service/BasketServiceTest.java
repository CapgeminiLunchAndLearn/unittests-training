package com.programmaniaks.training.unittest.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.programmaniaks.training.unittest.dao.ArticleDao;
import com.programmaniaks.training.unittest.entity.Article;
import com.programmaniaks.training.unittest.entity.Basket;
import com.programmaniaks.training.unittest.exceptions.NotEnoughtQtyException;

@RunWith(MockitoJUnitRunner.class)
public class BasketServiceTest {

	private Article articleA = new Article();

	private Article articleB = new Article();

	@InjectMocks
	private BasketService basketService = new BasketService();

	@Mock
	private ArticleDao articleDao;

	@Before
	public void before() {
		articleA.setPrice(20.0);
		articleA.setName("Article A");
		
		articleB.setPrice(10.0);
		articleB.setName("ArticleB");
	}

	@Test
	public void computeCheckOutTest() {
		Basket basket = new Basket();
		basket.setContent(new HashMap<Article, Integer>());
		// basket initialization
		basket.getContent().put(articleA, 1);
		// Unique article test: sum equals article price
		BigDecimal sum = basketService.computeCheckOut(basket);
		assertEquals(BigDecimal.valueOf(20), sum);
		// Multiple article test: sum equals article price * qty
		basket.getContent().put(articleA, 3);
		sum = basketService.computeCheckOut(basket);
		assertEquals(BigDecimal.valueOf(60), sum);
		// Multiple article test: sum equals article price * qty for each elem
		basket.getContent().put(articleB, 1);
		sum = basketService.computeCheckOut(basket);
		assertEquals(BigDecimal.valueOf(70), sum);
		// case basket is empty
		basket.getContent().clear();
		sum = basketService.computeCheckOut(basket);
		assertEquals(BigDecimal.ZERO, sum);
	}

	@Test(expected = NullPointerException.class)
	public void computeCheckOutTestException() {
		Basket basket = new Basket();
		// case null
		basket.setContent(null);
		basketService.computeCheckOut(basket);
	}

	@Test
	public void updateStockTest() throws NotEnoughtQtyException {
		
	}

	@Test
	public void updateStockTestException() throws NotEnoughtQtyException {
		
	}

}
