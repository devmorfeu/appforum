package br.com.devmorfeu.forum.repositorios

import br.com.devmorfeu.forum.controladores.modelos.saida.TopicoCategoriaDS
import br.com.devmorfeu.forum.entidades.Topico
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicoRepositorio: JpaRepository<Topico, Long> {

    fun findByCursoNome(nomeCurso: String, paginacao: Pageable): Page<Topico>

    @Query("SELECT new br.com.devmorfeu.forum.controladores.dto.saida.TopicoCategoriaDS(curso.categoria, count(t)) FROM Topico t JOIN t.curso curso GROUP BY curso.categoria")
    fun buscarRelatorio(): List<TopicoCategoriaDS>

}