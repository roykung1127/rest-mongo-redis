package com.kvinod.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class User implements Serializable, Comparable<User> {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String name;
	private String city;
	private String email;
	private String phone;

	@Override
	public int compareTo(User other) {
		return this.phone.compareTo(other.phone);
	}
}
