package com.programmaniaks.training.unittest.dao;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.programmaniaks.training.unittest.entity.User;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-test-context.xml")
@Transactional
public class UserDaoTest {
	
	@Autowired
	private UserDao userDao;
	
	@Test
	public void testCreate() {
		User userExpected = new User();
		userExpected.setName("Johnny Boubouc");
		userExpected.setPassword("Boubouc");
		userExpected.setUsername("Johnny");
		userExpected.setDateOfBirth(new Date());
		
		userDao.create(userExpected);
		
		User userActual = userDao.find(userExpected.getId());
		
		assertEquals(userExpected, userActual);
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void testCreate_UsernameUniqueConstraint() {
		User userExpected = new User();
		userExpected.setPassword("password");
		userExpected.setUsername("user1");
		
		userDao.create(userExpected);
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void testCreate_PasswordNonNullConstraint() {
		User userExpected = new User();
		userExpected.setUsername("UserQuiNePeutPasExisterDeManiereSurParceQuePersonneNePrendCaCommmePseudo");
		
		userDao.create(userExpected);
	}

}
