package br.com.devmorfeu.forum.casouso

import br.com.devmorfeu.forum.modelos.Usuario
import br.com.devmorfeu.forum.repositorio.UsuarioRepositorio
import org.springframework.stereotype.Service

@Service
class UsuarioCasoUso(private val repositorio: UsuarioRepositorio) {
    fun buscarUsuarioPorId(idAutor: Long): Usuario {
        return repositorio.getReferenceById(idAutor)
    }
}