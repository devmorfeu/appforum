package br.com.devmorfeu.forum.repositorios

import br.com.devmorfeu.forum.entidades.Curso
import org.springframework.data.jpa.repository.JpaRepository

interface CursoRepositorio: JpaRepository<Curso, Long> {
}