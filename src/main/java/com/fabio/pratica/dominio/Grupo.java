package com.fabio.pratica.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Getter 
@Setter 
public class Grupo {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String nomeGrupo;

    @Column (nullable = false)
    private String descricaoGrupo;

    @Column (nullable = false)
    private LocalDate DataCriacao;

    @Column (nullable = false, unique = true)
    private Long codigoGrupo;


    @ManyToOne
    @JoinColumn(name = "usuario_aministrador_id", nullable = false)
    private Usuario usuarioAdministrador;

    
    // Vários participantes por grupo
    @ManyToMany
    @JoinTable(
        name = "grupo_participantes",
        joinColumns = @JoinColumn(name = "grupo_id"),
        inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> participantes = new ArrayList<>();




}
