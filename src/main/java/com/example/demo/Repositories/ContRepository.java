package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Models.ContadorModel;

public interface ContRepository extends JpaRepository<ContadorModel, Long> {
}