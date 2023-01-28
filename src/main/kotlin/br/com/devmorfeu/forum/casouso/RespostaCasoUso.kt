package br.com.devmorfeu.forum.casouso

import br.com.devmorfeu.forum.controladores.dto.entrada.RespostaDE
import br.com.devmorfeu.forum.modelos.Curso
import br.com.devmorfeu.forum.modelos.Resposta
import br.com.devmorfeu.forum.modelos.Topico
import br.com.devmorfeu.forum.modelos.Usuario
import java.util.*
import java.util.stream.Collectors.toList

class RespostaCasoUso(
        private var respostas: List<Resposta>,
        private val topicoCasoUso: TopicoCasoUso,
        private val usuarioCasoUso: UsuarioCasoUso) {

    init {
        val curso = Curso(
                id = 1,
                nome = "Kotlin",
                categoria = "Programacao"
        )
        val autor = Usuario(
                id = 1,
                nome = "Ana da Silva",
                email = "ana@email.com"
        )
        val topico = Topico(
                id = 1,
                titulo = "Duvida Kotlin",
                mensagem = "Variaveis no Kotlin",
                curso = curso,
                autor = autor
        )

        val resposta = Resposta(
                id = 1,
                mensagem = "Resposta 1",
                autor = autor,
                topico = topico,
                solucao = false
        )

        val resposta2 = Resposta(
                id = 2,
                mensagem = "Resposta 2",
                autor = autor,
                topico = topico,
                solucao = false
        )

        respostas = Arrays.asList(resposta, resposta2)
    }

    fun listar(idTopico: Long): List<Resposta> {
        return respostas.stream().filter { resposta ->
            resposta.topico.id == idTopico
        }.collect(toList())
    }

    fun cadastrar(respostaDE: RespostaDE, idTopico: Long) {
        val resposta = Resposta(
                mensagem = respostaDE.mensagem,
                autor = usuarioCasoUso.buscarUsuarioPorId(respostaDE.idAutor),
                topico = topicoCasoUso.buscarPorIdBase(idTopico),
        )
        respostas = respostas.plus(resposta)
    }
}