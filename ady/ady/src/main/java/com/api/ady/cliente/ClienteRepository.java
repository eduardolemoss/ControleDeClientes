package com.api.ady.cliente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	List<Cliente> findBynomeContaining(String search);
	List<Cliente> findBystatusContaining(String status);
	

}
