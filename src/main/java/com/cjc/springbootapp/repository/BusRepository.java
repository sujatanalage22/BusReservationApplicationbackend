package com.cjc.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cjc.springbootapp.model.Bus;

public  interface BusRepository extends JpaRepository<Bus, Long> {
	
}

