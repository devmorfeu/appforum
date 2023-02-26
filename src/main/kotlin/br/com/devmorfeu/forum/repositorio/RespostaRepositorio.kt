package br.com.devmorfeu.forum.repositorio

import br.com.devmorfeu.forum.modelos.Resposta
import org.springframework.data.jpa.repository.JpaRepository

interface RespostaRepositorio: JpaRepository<Resposta, Long> {
}