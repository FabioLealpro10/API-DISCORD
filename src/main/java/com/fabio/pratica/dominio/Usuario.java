package com.fabio.pratica.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter 
@Setter 
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeCompleto;

    @Column(unique = true, nullable = false)
    private String NomeSocial;

    @Column(unique = true, nullable = false)
    private String email;

    @Column (nullable= false)
    private LocalDate dataNascimento;

    @Column (nullable = false)
    private String senha;


    // Grupos que este usuário administra
    @OneToMany(mappedBy = "usuarioAdministrador")
    private List<Grupo> gruposAdministrados = new ArrayList<>();

    // Grupos dos quais este usuário participa
    @ManyToMany(mappedBy = "participantes")
    private List<Grupo> gruposParticipantes = new ArrayList<>();

}