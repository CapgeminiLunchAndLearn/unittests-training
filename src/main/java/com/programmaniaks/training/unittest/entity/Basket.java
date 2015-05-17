package com.programmaniaks.training.unittest.entity;

import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hashCode(this.content, this.user, this.sum);
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (getClass() != obj.getClass())
			return false;
		final Basket other = (Basket) obj;
		return Objects.equal(this.content, other.content)
				&& Objects.equal(this.user, other.user)
				&& Objects.equal(this.sum, other.sum);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(getClass()).addValue(this.content)
				.addValue(user).addValue(sum).toString();
	}
}
