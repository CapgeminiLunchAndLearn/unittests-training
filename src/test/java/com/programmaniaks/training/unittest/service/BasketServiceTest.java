package com.programmaniaks.training.unittest.service;

import java.math.BigDecimal;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.programmaniaks.training.unittest.entity.Article;
import com.programmaniaks.training.unittest.entity.Basket;


@RunWith(MockitoJUnitRunner.class)
public class BasketServiceTest {
	
	private Article articleA = new Article();
	
	private BasketService basketService = new BasketService();
	

	@Test
	public void computeCheckOutTest(){
		Basket basket= new Basket();
		basket.setContent(new HashMap<Article, Integer>());
		//basket initialization
		articleA.setPrice(20.0);
		articleA.setName("Article A");
		basket.getContent().put(articleA, 1);
		//Unique article test: sum equals article price
		BigDecimal sum = basketService.computeCheckOut(basket);
		Assert.assertEquals(BigDecimal.valueOf(20.0), sum);
	}
}
