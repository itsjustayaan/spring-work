package com.working.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.working.model.Basket;

@Repository
public interface BasketDAO extends JpaRepository<Basket,Integer>{
	public List<Basket> findByBasketName(String basketName);
//	public List<Basket> findByFk_iaId(int iaId);
	
}
