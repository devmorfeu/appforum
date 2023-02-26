package br.com.devmorfeu.forum.repositorio

import br.com.devmorfeu.forum.modelos.Curso
import org.springframework.data.jpa.repository.JpaRepository

interface CursoRepositorio: JpaRepository<Curso, Long> {
}