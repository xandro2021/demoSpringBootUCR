package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Profesor;
import com.example.demo.repository.ProfesorRepository;

@Service
public class ProfesorService {
  private final ProfesorRepository profesorRepository;

  public ProfesorService(ProfesorRepository profesorRepository) {
    this.profesorRepository = profesorRepository;
  }

  public Profesor add(Profesor profesor) {
    return profesorRepository.save(profesor);
  }

  public List<Profesor> listar() {
    return profesorRepository.findAll();
  }

  public Profesor consultar(Long id) {
    return profesorRepository.findById(id).orElse(null);
  }

  public void eliminar(Long id) {
    profesorRepository.deleteById(id);
  }

  public Profesor actualizar(Profesor profesor) {
    return profesorRepository.save(profesor);
  }
}
