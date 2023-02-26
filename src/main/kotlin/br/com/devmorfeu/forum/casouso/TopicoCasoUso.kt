package br.com.devmorfeu.forum.casouso

import br.com.devmorfeu.forum.controladores.dto.entrada.AtualizacaoTopicoDE
import br.com.devmorfeu.forum.controladores.dto.entrada.TopicoDE
import br.com.devmorfeu.forum.controladores.dto.saida.TopicoDS
import br.com.devmorfeu.forum.excessoes.IdNaoEncontradoExcessao
import br.com.devmorfeu.forum.modelos.StatusTopico
import br.com.devmorfeu.forum.modelos.StatusTopico.NAO_RESPONDIDO
import br.com.devmorfeu.forum.modelos.Topico
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.LocalDateTime.*
import java.util.stream.Collectors.*

@Service
class TopicoCasoUso(
    private var topicos: List<Topico> = ArrayList(),
    private val cursoCasoUso: CursoCasoUso,
    private val usuarioCasoUso: UsuarioCasoUso
) {

    fun listar(): List<TopicoDS> {

        return topicos.stream().map { topico ->
            TopicoDS(
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
            .orElseThrow { IdNaoEncontradoExcessao("Topico nao encontrado") }

        return TopicoDS(
            id = topico.id!!,
            titulo = topico.titulo,
            mensagem = topico.mensagem,
            dataCriacao = topico.dataCriacao,
            status = topico.status
        )
    }

    fun cadastrar(topicoDE: TopicoDE): TopicoDS {
        topicos = topicos.plus(
            Topico(
                id = topicos.size.toLong() + 1,
                titulo = topicoDE.titulo,
                mensagem = topicoDE.mensagem,
                curso = cursoCasoUso.buscarCursoPorId(topicoDE.idCurso),
                autor = usuarioCasoUso.buscarUsuarioPorId(topicoDE.idAutor)
            )
        )

        return TopicoDS(id = 0, titulo = "", mensagem = "", status = NAO_RESPONDIDO, dataCriacao = now())
    }

    fun buscarPorIdBase(id: Long): Topico {

        return topicos.stream()
            .filter { topico -> topico.id == id }
            .findFirst()
            .orElseThrow { IdNaoEncontradoExcessao("Topico nao encontrado") }
    }

    fun atualizar(atualizacaoTopicoDE: AtualizacaoTopicoDE) {
        val topico = topicos.stream().filter { topico ->
            topico.id == atualizacaoTopicoDE.id
        }.findFirst().get()

        topicos.minus(topico).plus(
            Topico(
                id = atualizacaoTopicoDE.id,
                titulo = atualizacaoTopicoDE.titulo,
                mensagem = atualizacaoTopicoDE.mensagem,
                autor = topico.autor,
                curso = topico.curso,
                respostas = topico.respostas,
                status = topico.status,
                dataCriacao = topico.dataCriacao
            )
        )
    }

    fun deletar(id: Long) {
        val topico = topicos.stream().filter { topico ->
            topico.id == id
        }.findFirst()
            .orElseThrow { IdNaoEncontradoExcessao("Topico nao encontrado") }

        topicos.minus(topico)
    }
}