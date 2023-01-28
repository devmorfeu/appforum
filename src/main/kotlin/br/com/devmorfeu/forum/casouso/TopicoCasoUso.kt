package br.com.devmorfeu.forum.casouso

import br.com.devmorfeu.forum.controladores.dto.entrada.TopicoDE
import br.com.devmorfeu.forum.controladores.dto.saida.TopicoDS
import br.com.devmorfeu.forum.modelos.Topico
import org.springframework.stereotype.Service
import java.util.stream.Collectors.*

@Service
class TopicoCasoUso(
        private var topicos: List<Topico> = ArrayList(),
        private val cursoCasoUso: CursoCasoUso,
        private val usuarioCasoUso: UsuarioCasoUso) {

    fun listar(): List<TopicoDS> {

        return topicos.stream().map {
            topico -> TopicoDS(
                    id = topico.id!!,
                    titulo = topico.titulo,
                    mensagem = topico.mensagem,
                    dataCriacao = topico.dataCriacao,
                    status = topico.status
            )
        }.collect(toList())
    }

    fun buscarPorId(id: Long): TopicoDS {

        val topico = topicos.stream()
                .filter { topico -> topico.id == id }
                .findFirst()
                .get()

        return TopicoDS(
                id = topico.id!!,
                titulo = topico.titulo,
                mensagem = topico.mensagem,
                dataCriacao = topico.dataCriacao,
                status = topico.status
        )
    }

    fun cadastrar(topicoDE: TopicoDE) {
        topicos = topicos.plus(Topico(
                    id = topicos.size.toLong() + 1,
                    titulo = topicoDE.titulo,
                    mensagem = topicoDE.mensagem,
                    curso = cursoCasoUso.buscarCursoPorId(topicoDE.idCurso),
                    autor = usuarioCasoUso.buscarUsuarioPorId(topicoDE.idAutor)
            ))
    }

    fun buscarPorIdBase(id: Long): Topico {

        return topicos.stream()
                .filter { topico -> topico.id == id }
                .findFirst()
                .get()
    }
}