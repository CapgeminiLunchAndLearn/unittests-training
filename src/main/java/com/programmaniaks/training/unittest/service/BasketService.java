package com.programmaniaks.training.unittest.service;

import java.math.BigDecimal;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programmaniaks.training.unittest.dao.ArticleDao;
import com.programmaniaks.training.unittest.entity.Article;
import com.programmaniaks.training.unittest.entity.Basket;
import com.programmaniaks.training.unittest.exceptions.NotEnoughtQtyException;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class BasketService {
	
	@Autowired
	private ArticleDao articleDao;

	/**
	 * compute total price of the basket
	 * @param basket
	 * @return BigDecimal
	 */
	public BigDecimal computeCheckOut(Basket basket){
		checkNotNull(basket.getContent(), "Basket content should not be null.");
		return basket.getContent().entrySet().stream()
				.map(e -> new BigDecimal(e.getKey().getPrice() * e.getValue()))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
	/**
	 * automatic update of item stocks
	 * @param basket
	 * @throws NotEnoughtQtyException
	 */
	@Transactional
	public void updateStock(Basket basket) throws NotEnoughtQtyException{
		for(Entry<Article, Integer> element:basket.getContent().entrySet()){
			Article articleFromDb = articleDao.find(element.getKey().getId());
			int qty =articleFromDb.getQuantity()-element.getValue();
			if(qty>=0){
				articleFromDb.setQuantity(qty);
				articleDao.update(articleFromDb);
			}else{
				throw new NotEnoughtQtyException("Not enough quantity for item: "+articleFromDb.getName());
			}			
		}
	}
}
