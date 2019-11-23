package com.example.demo.hello.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_hello")
public class Hello 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String msg;
	private String myTestMsg;
	
	public Long getId() {
		return id;
	}
	public String getMsg() {
		return msg;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMyTestMsg() {
		return myTestMsg;
	}
	public void setMyTestMsg(String myTestMsg) {
		this.myTestMsg = myTestMsg;
	}
	
	
}
