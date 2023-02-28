package br.com.devmorfeu.forum.repositorios

import br.com.devmorfeu.forum.entidades.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepositorio: JpaRepository<Usuario, Long> {

    fun findByEmail(email: String?): Usuario?

}