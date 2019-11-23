package com.example.demo.order.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.demo.order.domain.Order;

@Repository
//@Component
public interface OrderRepository 
extends PagingAndSortingRepository<Order, Long>
,JpaSpecificationExecutor<Order>//动态查询
{
//基于java反射机制 生成模板代码
	
//1.Repository 接口：代表是一个持久层的接口，只可以自定义查询方法！
// Repository 接口 与 @Repository注解  重名冲突解决：
//	1、@Component替换@Repository 
//	2、者声明其中一个的真实包路径
	
	//自定义查询  //hql == oop sql
		//@Query(value="sql",nativeQuery=true)
		//@Query("hql")
		//@Query("from Order order where order.id = ?1  and order.orderNumber = ?2")
		//public Page<Order> findPage2(Long id,String orderNumber,Pageable pageable);
		//public Page<Order> findByIdAndOrderNumber(Long id,String orderNumber,Pageable pageable);
	
//2.CrudRepository 接口 extends  Repository：实现基本CRUD功能。

//	Order  save(Order entity);		//id
//	Iterable<Order> saveAll(Iterable<Order> entities);
	
//	Optional<Order> findById(Long id);
//	boolean existsById(Long id);
	
//	Iterable<Order> findAll();
//	Iterable<Order> findAllById(Iterable<Long> ids);
	
//	long count();
	
//	void deleteById(Long id);
//	void delete(Order entity);
//	void deleteAll(Iterable<? extends Order> entities);
//	void deleteAll();

	
//3.PagingAndSortingRepository接口 extends CrudRepository 接口：
//  分页和排序的功能，以及继承CRUD功能
//	Iterable<Order> findAll(Sort sort);
//	Page<Order> findAll(Pageable pageable);
	
	
//动态多条件的复杂查询
//4.JpaSpecificationExecutor接口：实现动态查询的方法	
//	Optional<T> findOne(@Nullable Specification<T> spec);
//	List<T> findAll(@Nullable Specification<T> spec);
//	Page<T> findAll(@Nullable Specification<T> spec, Pageable pageable);
//	List<T> findAll(@Nullable Specification<T> spec, Sort sort);
//	long count(@Nullable Specification<T> spec);
	
}
