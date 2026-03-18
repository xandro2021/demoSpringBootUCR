package com.example.demo.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "profesores")
public class Profesor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "address", nullable = false)
  private String address;

  @Column(name = "phone", nullable = false)
  private String phone;

  @OneToMany(mappedBy = "profesor")
  private List<Curso> cursos;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public List<Curso> getCursos() {
    return cursos;
  }

  public void setCursos(List<Curso> cursos) {
    this.cursos = cursos;
  }

}
