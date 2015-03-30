package com.programmaniaks.training.unittest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.programmaniaks.training.unittest.dao.GreetingDao;
import com.programmaniaks.training.unittest.entity.Greeting;

@Service
public class GreetingService {

	@Autowired
	private GreetingDao greetingDao;
	
	public void create(Greeting greeting) {
		greetingDao.create(greeting);
	}
	
	public List<Greeting> findAll() {
		return greetingDao.findAll();
	}
}
