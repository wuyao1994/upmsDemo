package com.upms.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "t_book")
public class Book implements Serializable {
	@Id
	@GeneratedValue
	@NotNull
	private Long	id;
	@NotNull(message = "name is null")
	private String	name;
	@NotNull(message = "num is null")
	private String	num;



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getNum() {
		return num;
	}



	public void setNum(String num) {
		this.num = num;
	}
}
