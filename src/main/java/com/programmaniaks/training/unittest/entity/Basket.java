package com.programmaniaks.training.unittest.entity;

import java.util.Map;

import org.springframework.context.annotation.Scope;

@Scope("Session")
public class Basket {

	private Map<Article, Integer> content;
	
	private User user;
	
	private double sum;

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public Map<Article, Integer> getContent() {
		return content;
	}

	public void setContent(Map<Article, Integer> content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
