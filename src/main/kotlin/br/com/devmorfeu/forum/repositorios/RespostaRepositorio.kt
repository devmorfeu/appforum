package br.com.devmorfeu.forum.repositorios

import br.com.devmorfeu.forum.entidades.Resposta
import org.springframework.data.jpa.repository.JpaRepository

interface RespostaRepositorio: JpaRepository<Resposta, Long> {
}