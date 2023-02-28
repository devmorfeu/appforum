package br.com.devmorfeu.forum.casosusos

import br.com.devmorfeu.forum.controladores.modelos.entrada.AtualizacaoTopicoDE
import br.com.devmorfeu.forum.controladores.modelos.entrada.TopicoDE
import br.com.devmorfeu.forum.controladores.modelos.saida.TopicoCategoriaDS
import br.com.devmorfeu.forum.controladores.modelos.saida.TopicoDS
import br.com.devmorfeu.forum.excessoes.IdNaoEncontradoExcessao
import br.com.devmorfeu.forum.entidades.StatusTopico.NAO_RESPONDIDO
import br.com.devmorfeu.forum.entidades.Topico
import br.com.devmorfeu.forum.repositorios.TopicoRepositorio
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDateTime.now

@Service
class TopicoCasoUso(
    private val repositorio: TopicoRepositorio,
    private val cursoCasoUso: CursoCasoUso,
    private val usuarioCasoUso: UsuarioCasoUso
) {

    fun listar(nomeCurso: String?, paginacao: Pageable): Page<TopicoDS> {

        val topicos = if (nomeCurso == null) repositorio.findAll(paginacao) else repositorio.findByCursoNome(nomeCurso, paginacao);

        return topicos.map { topico ->
            TopicoDS(
                id = topico.id!!,
                titulo = topico.titulo,
                mensagem = topico.mensagem,
                dataCriacao = topico.dataCriacao,
                status = topico.status
            )
        }
    }

    fun buscarPorId(id: Long): TopicoDS {

        val topico = repositorio.findById(id).stream()
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
        repositorio.save(
            Topico(
                titulo = topicoDE.titulo,
                mensagem = topicoDE.mensagem,
                curso = cursoCasoUso.buscarCursoPorId(topicoDE.idCurso),
                autor = usuarioCasoUso.buscarUsuarioPorId(topicoDE.idAutor)
            )
        )

        return TopicoDS(id = 0, titulo = "", mensagem = "", status = NAO_RESPONDIDO, dataCriacao = now())
    }

    fun atualizar(atualizacaoTopicoDE: AtualizacaoTopicoDE) {

        val topico = repositorio.findById(atualizacaoTopicoDE.id).stream()
            .filter { topico ->
                topico.id == atualizacaoTopicoDE.id
            }
            .findFirst()
            .orElseThrow { IdNaoEncontradoExcessao("Topico nao encontrado") }

        topico.titulo = atualizacaoTopicoDE.titulo
        topico.mensagem = atualizacaoTopicoDE.mensagem

        repositorio.save(topico)
    }

    fun deletar(id: Long) {
        repositorio.deleteById(id)
    }

    fun buscarRelatorio(): List<TopicoCategoriaDS> {
        return repositorio.buscarRelatorio()
    }
}