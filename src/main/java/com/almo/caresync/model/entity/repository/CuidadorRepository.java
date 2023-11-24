package com.almo.caresync.model.entity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almo.caresync.model.entity.Cuidador;

public interface CuidadorRepository  extends JpaRepository<Cuidador, Integer> {

	Optional<Cuidador> findByEmail(String email);
	boolean existsByEmail(String email);

}
