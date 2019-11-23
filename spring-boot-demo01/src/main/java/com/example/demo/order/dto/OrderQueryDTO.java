package com.example.demo.order.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.order.domain.Order;

public class OrderQueryDTO {
	// 1.封装动态查询条件
	private String orderNumber;

	private Date createTimeStart;
	private Date createTimeEnd;
	
	//userid
	//userdataLever

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	// 2.根据查询条件是否有值 - 动态拼接动态查询条件对象 Specification<T> spec
	public static Specification<Order> getWhereClause(final OrderQueryDTO orderQueryDTO) {
		return new Specification<Order>() {
			@Override
			public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicate = new ArrayList<>();
				if (StringUtils.isNotBlank(orderQueryDTO.getOrderNumber())) {
					predicate.add(cb.like(root.get("orderNumber").as(String.class),
							"%" + orderQueryDTO.getOrderNumber() + "%"));
				}
				if (null != orderQueryDTO.getCreateTimeStart()) {
					predicate.add(cb.greaterThanOrEqualTo(root.get("createTime").as(Date.class),
							orderQueryDTO.getCreateTimeStart()));
				}
				if (null != orderQueryDTO.getCreateTimeEnd()) {
					predicate.add(cb.lessThanOrEqualTo(root.get("createTime").as(Date.class),
							orderQueryDTO.getCreateTimeEnd()));
				}

				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();// and
			}
		};
	}

}
