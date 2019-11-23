package com.example.demo.order.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.order.domain.Order;
import com.example.demo.order.repository.OrderRepository;

@Service
@Transactional(readOnly=true)
public class OrderService  implements IOrderService{

	@Autowired
	private OrderRepository orderRepository;

	@Transactional
	public void save(Order entity) {
		//复杂的业务逻辑、算法、数据库单标/多表操作、文件操作、日志、权限、校验、流程控制 etc......
		orderRepository.save(entity);
		
	}

	@Transactional
	public void deleteById(Long id) {
		orderRepository.deleteById(id);
		
	}

	@Transactional
	public void delete(Order entity) {
		orderRepository.delete(entity);
		
	}

	@Transactional
	public void deleteAll(List<Order> entities) {
		orderRepository.deleteAll(entities);
		
	}

	@Override
	public Order findById(Long id) {
		return orderRepository.findById(id).get();
	}

	@Override
	public boolean existsById(Long id) {
		
		return orderRepository.existsById(id);
	}

	@Override
	public List<Order> findAllById(List<Long> ids) {

		return (List<Order>) orderRepository.findAllById(ids);
	}

	@Override
	public Page<Order> findAll(Specification<Order> spec,Pageable pageable) {
		// 
		return orderRepository.findAll(spec, pageable);
	}

	
}
