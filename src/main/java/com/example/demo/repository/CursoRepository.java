package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Curso;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByProfesorId(Long profesorId);
}