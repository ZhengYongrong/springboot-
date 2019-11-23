package com.example.demo.order.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.utils.ApplicationException;
import com.example.demo.common.web.ResponseEntity;
import com.example.demo.common.web.STablePageRequest;
import com.example.demo.order.domain.Order;
import com.example.demo.order.dto.OrderDTO;
import com.example.demo.order.dto.OrderQueryDTO;
import com.example.demo.order.server.IOrderService;

@RestController
@RequestMapping("/order")
public class OrderController 
{
	
	@Autowired
	private IOrderService orderService;
	
	@PostMapping
	public String save(OrderDTO dto) {
		Order entity = new Order();
		BeanUtils.copyProperties(dto, entity);
		//关联关系的维护
		//User user = userService.findById(dto.getUserId());
		//entity.setUser(user);
		
		orderService.save(entity);
		return "success";
	}
	
	@PutMapping(value="/{id}")
    public ResponseEntity<Order> update(
    		@PathVariable("id") Long id
    		,@RequestBody Order source) {
        try {
        	
        	Order target = orderService.findById(id);
			if(target!=null) {
				BeanUtils.copyProperties(source, target);//使用自定义的BeanUtils	
				
				//User user = userService.findById(source.getUserId());
				//target.serUser(user);
				
				orderService.save(target);//update
			}else {
				orderService.save(source);//save
			}
        } catch (ApplicationException ex) {
            //log.error("Update Order error.{}", ex.getMessage());
            return ResponseEntity.ofFailed().data("Update Order error.");
        }
        return ResponseEntity.ofSuccess().status(HttpStatus.OK).data("Update Order Success.");
    }
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Order> delete(@PathVariable("id") Long id) {
		try {
			if (id != null) {
				orderService.deleteById(id);
			}
		} catch (ApplicationException ex) {
			//log.error("Delete Order error.{}", ex.getMessage());
			return ResponseEntity.ofFailed().data("Delete Order error.");
		}
		return  ResponseEntity.ofSuccess().status(HttpStatus.OK).data("Delete Order Success.");
	}
	
	@GetMapping("/{id}")
	public Order findById(@PathVariable Long id) {
		
		return orderService.findById(id);
	}
	//Specification<Order> spec ,
	@GetMapping
	public ResponseEntity<Page<OrderDTO>> findAll(OrderQueryDTO query , STablePageRequest pageable){

		Page<OrderDTO> page = Page.empty(pageable.getPageable());
        try {
        	Page<Order> domainPage = orderService.findAll(OrderQueryDTO.getWhereClause(query), pageable.getPageable());
        	
        	List<OrderDTO> content = null;
        	List<Order> domainList = domainPage.getContent();
        	if(null!= domainList) {
        		content = new ArrayList<OrderDTO>();
        		for(Order order : domainPage.getContent()) 
        		{
        			OrderDTO dto = new OrderDTO();
            		BeanUtils.copyProperties(order, dto);
            		
            		content.add(dto);
            	}
        		page = new PageImpl<OrderDTO>(content, pageable.getPageable(), domainPage.getTotalElements());
        	}
        } catch (ApplicationException ex) {
            
            return ResponseEntity.ofFailed().data("Find Order Page error.");
        }
        return ResponseEntity.ofSuccess().status(HttpStatus.OK).data(page);
	}
}
