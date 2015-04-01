package com.programmaniaks.training.unittest.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.programmaniaks.training.unittest.dao.UserDao;
import com.programmaniaks.training.unittest.entity.User;
import com.programmaniaks.training.unittest.exceptions.AuthenticationException;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public String encryptPassword(String password) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

        byte raw[] = md.digest(password.getBytes()); // Message summary reception
 
        try{
            return new String(Base64.encodeBase64(raw),"UTF-8");
        } catch (UnsupportedEncodingException use){
            return new String(raw);
        }
	}
	
	public User login(String username, String password) {
		User user = userDao.findByUsernameAndPassword(username, encryptPassword(password));
		if (user == null) {
			throw new AuthenticationException();
		}
		return user;
	}
	
	public void create(User user) {
		user.setPassword(encryptPassword(user.getPassword()));
		userDao.create(user);
	}
	

}
