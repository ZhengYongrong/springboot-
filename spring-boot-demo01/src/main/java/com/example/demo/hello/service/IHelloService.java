package com.example.demo.hello.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.hello.domain.Hello;

public interface IHelloService 
{
	public void saveOrUpdate(Hello hello);
	public void delete(Hello hello);
	public Hello findOne(Long id);
	public Page<Hello> findPage(Pageable pageable);
}
