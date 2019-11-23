package com.example.demo.order;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.order.domain.Order;
import com.example.demo.order.domain.OrderStatus;
import com.example.demo.order.server.IOrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTests {
	@Autowired
	private IOrderService orderService;
	
	@Test
	public void testData() {
		for (int i = 0; i < 100; i++) {
			Order entity = new Order();
			int max=9999999;  int min = 100;
			entity.setAmount( Math.random() * ((min - 1) / 1) + (max + 1 - min));
			entity.setCreateTime(new Date());
			entity.setDescription("我的订单测试数据-序号："+i);
			entity.setOrderNumber("No"+i);
			if(i%2==0)
				entity.setOrderStatus(OrderStatus.OPEN);
			else
				entity.setOrderStatus(OrderStatus.FINISH);
			orderService.save(entity);
		}
		
	}
}
