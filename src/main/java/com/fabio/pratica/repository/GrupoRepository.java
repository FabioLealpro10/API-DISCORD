package com.fabio.pratica.repository;

import org.springframework.data.jpa.repository.JpaRepository; 


import com.fabio.pratica.dominio.Grupo;


public interface GrupoRepository extends JpaRepository<Grupo, Long>  {

}
