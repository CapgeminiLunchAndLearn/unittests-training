package com.programmaniaks.training.unittest.service;

import java.math.BigDecimal;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.programmaniaks.training.unittest.dao.ArticleDao;
import com.programmaniaks.training.unittest.entity.Article;
import com.programmaniaks.training.unittest.entity.Basket;
import com.programmaniaks.training.unittest.exceptions.NotEnoughtQtyException;

public class BasketService {
	
	@Autowired
	private ArticleDao articleDao;

	public BigDecimal computeCheckOut(Basket basket){
		BigDecimal sum = BigDecimal.ZERO;
		basket.getContent().forEach(
				(article,number)->
				sum.add(BigDecimal.valueOf(article.getPrice()*number)));
		return sum;
	}
	
	@Transactional
	public void updateStock(Basket basket) throws NotEnoughtQtyException{
		for(Entry<Article, Integer> element:basket.getContent().entrySet()){
			Article articleFromDb = articleDao.find(element.getKey().getId());
			articleFromDb.setQuantity(articleFromDb.getQuantity()-element.getValue());
			articleDao.update(articleFromDb);
		}
	}
}
