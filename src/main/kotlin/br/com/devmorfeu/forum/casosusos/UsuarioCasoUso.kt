package br.com.devmorfeu.forum.casosusos

import br.com.devmorfeu.forum.entidades.Usuario
import br.com.devmorfeu.forum.repositorios.UsuarioRepositorio
import br.com.devmorfeu.forum.seguranca.UsuarioSeguranca
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UsuarioCasoUso(private val repositorio: UsuarioRepositorio): UserDetailsService {
    fun buscarUsuarioPorId(idAutor: Long): Usuario {
        return repositorio.getOne(idAutor)
    }

    override fun loadUserByUsername(email: String?): UserDetails {

        val usuario = repositorio.findByEmail(email) ?: throw RuntimeException()

        return UsuarioSeguranca(usuario)
    }
}