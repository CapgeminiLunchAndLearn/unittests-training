package com.programmaniaks.training.unittest.entity;



import javax.persistence.Entity;
import javax.persistence.Id;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

@Entity
public class Greeting {

	@Id
    private long id;
    private String content;
    
    public Greeting() {
    	super();
    }

    public Greeting(long id, String content) {
    	this();
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

	@Override
	public int hashCode() {
		return Objects.hashCode(id, content);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Greeting other = (Greeting) obj;
		return Objects.equal(this.id, other.id)
				&& Objects.equal(this.content, other.content);
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).addValue(id).addValue(content).toString();
	}
}