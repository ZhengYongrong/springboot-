package com.example.demo.hello.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.hello.domain.Hello;
import com.example.demo.hello.service.IHelloService;

@RestController
@RequestMapping("/hello")
public class HelloController 
{
	@Autowired
	private IHelloService helloService;
	
	@PostMapping
	public void save(Hello hello) {// id = null
		 helloService.saveOrUpdate(hello);
	}
	
	@PutMapping
	public void update(Hello hello) {// id = 1
		 helloService.saveOrUpdate(hello);
	}
	

	@DeleteMapping
	public void delete(Long id) {
		Hello hello = helloService.findOne(id);
		if(null!=hello)
		 helloService.delete(hello);
	}
	
	@GetMapping
	public Hello findOne(Long id) {
		return helloService.findOne(id);
	}
	
}
