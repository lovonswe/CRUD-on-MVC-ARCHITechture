package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Programmer;

//import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProgrammerRepo extends JpaRepository<Programmer, Integer>{


	List<Programmer> findBypLang(String pLang);

	List<Programmer> findBypName(String pName);
	
}
