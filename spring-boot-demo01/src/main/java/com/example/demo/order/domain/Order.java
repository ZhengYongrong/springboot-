package com.example.demo.order.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="t_order")
public class Order 
{
	//order.user.userId
//	order:{id:1,orderNumber="c000001"...  
//			user:{id:1,userName:admin}
//	}
	//@JsonIgnore
	//@OneToMany(fetch=FetchType.LAZY)
	//List<OrderDetils>

	//@OneToOne(fetch=FetchType.EAGER)
	//private User user;
	//数据权限字段
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;//null
	private String orderNumber;
	private Double amount;
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	//@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	//@Lob
	@Column(length=50)
	private String description;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	

	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
