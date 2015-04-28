package com.programmaniaks.training.unittest.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.programmaniaks.training.unittest.entity.Basket;
import com.programmaniaks.training.unittest.exceptions.NotEnoughtQtyException;
import com.programmaniaks.training.unittest.service.BasketService;

@RestController
@RequestMapping("/basket")
public class BasketController {
	
	public BasketService getBasketService() {
		return basketService;
	}

	public void setBasketService(BasketService basketService) {
		this.basketService = basketService;
	}

	@Autowired
	private BasketService basketService;
	
	@RequestMapping(value="/sum", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public BigDecimal computeSum(@RequestBody(required=true)Basket basket) throws NotEnoughtQtyException{
		basketService.updateStock(basket);
		return basketService.computeCheckOut(basket);
	}
	

}
