package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import com.example.demo.model.Curso;
import com.example.demo.model.Profesor;
import com.example.demo.service.CursoService;
import com.example.demo.service.ProfesorService;

@Controller
@RequestMapping("/profesores")
public class ProfesorController {
  private final ProfesorService profesorService;
  private final CursoService cursoService;

  public ProfesorController(ProfesorService profesorService, CursoService cursoService) {
    this.profesorService = profesorService;
    this.cursoService = cursoService;
  }

  @GetMapping
  public String listar(Model model) {
    model.addAttribute("profesores", profesorService.listar());
    return "profesores";
  }

  @GetMapping("/nuevo")
  public String mostrarFormulario(Model model) {
    model.addAttribute("profesor", new Profesor());
    return "formulario";
  }

  @PostMapping("/guardar")
  public String guardar(@ModelAttribute Profesor profesor) {
    profesorService.add(profesor);
    return "redirect:/profesores";
  }

  @GetMapping("/editar/{id}")
  public String editarProfesor(@PathVariable Long id, Model model) {

    Profesor profesor = profesorService.consultar(id);

    model.addAttribute("profesor", profesor);

    return "formulario";
  }

  @GetMapping("/eliminar/{id}")
  public String eliminar(@PathVariable Long id) {
    profesorService.eliminar(id);
    return "redirect:/profesores";
  }

  @GetMapping("/cursos/{id}")
  public String verCursosPorProfesor(@PathVariable Long id, Model model) {

    List<Curso> cursos = cursoService.listarPorProfesor(id);
    Profesor profesor = profesorService.consultar(id);

    model.addAttribute("cursos", cursos);
    model.addAttribute("profesor", profesor);

    return "cursos"; // o cursos_profesor.html
  }

  @GetMapping("/{id}/asignar-curso")
  public String asignarCurso(@PathVariable Long id, Model model) {
    Profesor profesor = profesorService.consultar(id);
    if (profesor == null)
      return "redirect:/profesores";

    Curso curso = new Curso();
    curso.setProfesor(profesor); // pre-seleccionado

    model.addAttribute("curso", curso);
    model.addAttribute("profesores", List.of(profesor)); // solo este profesor
    model.addAttribute("profesor", profesor);

    return "formularioCurso";
  }
}
