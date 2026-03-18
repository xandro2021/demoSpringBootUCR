package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Curso;
import com.example.demo.service.CursoService;
import com.example.demo.service.ProfesorService;

@Controller
@RequestMapping("/cursos")
public class CursoController {

  private final CursoService cursoService;
  private final ProfesorService profesorService;

  public CursoController(CursoService cursoService, ProfesorService profesorService) {
    this.cursoService = cursoService;
    this.profesorService = profesorService;
  }

  @GetMapping
  public String listar(Model model) {
    model.addAttribute("cursos", cursoService.listar());
    model.addAttribute("profesor", null); // importante para la vista
    return "cursos";
  }

  @GetMapping("/nuevo")
  public String nuevo(Model model) {
    model.addAttribute("curso", new Curso());
    model.addAttribute("profesores", profesorService.listar());
    return "formularioCurso";
  }

  @PostMapping("/guardar")
  public String guardar(@ModelAttribute Curso curso) {
    // Si el profesor viene como null → puedes validarlo aquí o en el frontend
    cursoService.add(curso); // o save() directamente
    return "redirect:/cursos";
  }

  @GetMapping("/editar/{id}")
  public String editar(@PathVariable Long id, Model model) {
    Curso curso = cursoService.consultar(id);
    if (curso == null) {
      return "redirect:/cursos";
    }
    model.addAttribute("curso", curso);
    model.addAttribute("profesores", profesorService.listar());
    return "formularioCurso";
  }

  @GetMapping("/eliminar/{id}")
  public String eliminar(@PathVariable Long id) {
    cursoService.eliminar(id);
    return "redirect:/cursos";
  }
}
