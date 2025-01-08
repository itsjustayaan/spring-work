package com.working.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.working.model.Authority;

public interface AuthorityDAO extends JpaRepository<Authority, Long> {
	public Authority findByUsername(String username);
	public void deleteByUsername(String username);
}
