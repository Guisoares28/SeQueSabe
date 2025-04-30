package com.example.demo.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Models.ComidaEnum;
import com.example.demo.Models.ComidaModel;

public interface ComidaRepository extends JpaRepository<ComidaModel, Long> {
    List<ComidaModel> findByTipo(ComidaEnum tipo);
}
