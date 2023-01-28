package br.com.devmorfeu.forum.casouso

import br.com.devmorfeu.forum.modelos.Usuario
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsuarioCasoUso(var usuarios: List<Usuario>) {

    init {
        val usuario = Usuario(
                id = 1,
                nome = "Rainha elizabeth",
                "morreu@morreu"
        )

        usuarios = Arrays.asList(usuario)
    }

    fun buscarUsuarioPorId(idAutor: Long): Usuario {
        return usuarios.stream()
                .filter { usuario -> usuario.id == idAutor }
                .findFirst()
                .get()
    }
}