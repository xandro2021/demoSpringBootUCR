package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Curso;
import com.example.demo.repository.CursoRepository;

@Service
public class CursoService {
  private final CursoRepository cursoRepository;

  public CursoService(CursoRepository cursoRepository) {
    this.cursoRepository = cursoRepository;
  }

  public Curso add(Curso curso) {
    return cursoRepository.save(curso);
  }

  public List<Curso> listar() {
    return cursoRepository.findAll();
  }

  public List<Curso> listarPorProfesor(Long profesorId) {
    return cursoRepository.findByProfesorId(profesorId);
  }

  public Curso consultar(Long id) {
    return cursoRepository.findById(id).orElse(null);
  }

  public void eliminar(Long id) {
    cursoRepository.deleteById(id);
  }

  public Curso actualizar(Curso curso) {
    return cursoRepository.save(curso);
  }
}
