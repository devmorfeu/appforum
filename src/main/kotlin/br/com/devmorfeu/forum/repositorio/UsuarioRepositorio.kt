package br.com.devmorfeu.forum.repositorio

import br.com.devmorfeu.forum.modelos.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepositorio: JpaRepository<Usuario, Long> {
}