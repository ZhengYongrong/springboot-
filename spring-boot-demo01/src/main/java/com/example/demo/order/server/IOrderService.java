package com.example.demo.order.server;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.example.demo.order.domain.Order;

//核心业务的抽象：提供什么服务给用户
public interface IOrderService 
{
	public void save(Order entity);
	public void deleteById(Long id);
	public void delete(Order entity);
	public void deleteAll(List<Order> entities);

	public Order findById(Long id);
	public boolean existsById(Long id);
	public List<Order> findAllById(List<Long> ids);
	//分页+排序+动态查询条件 Specification<Order> spec ,
	public Page<Order> findAll(Specification<Order> spec,Pageable pageable);
}
