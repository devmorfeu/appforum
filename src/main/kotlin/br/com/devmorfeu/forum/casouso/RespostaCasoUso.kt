//package br.com.devmorfeu.forum.casouso
//
//import br.com.devmorfeu.forum.controladores.dto.entrada.RespostaDE
//import br.com.devmorfeu.forum.modelos.Curso
//import br.com.devmorfeu.forum.modelos.Resposta
//import br.com.devmorfeu.forum.modelos.Topico
//import br.com.devmorfeu.forum.modelos.Usuario
//import br.com.devmorfeu.forum.repositorio.RespostaRepositorio
//import java.util.*
//import java.util.stream.Collectors.toList
//
//class RespostaCasoUso(
//        private val repositorio: RespostaRepositorio,
//        private val topicoCasoUso: TopicoCasoUso,
//        private val usuarioCasoUso: UsuarioCasoUso){
//
//    fun listar(idTopico: Long): List<Resposta> {
//        return repositorio.fin(idTopico).filter { resposta ->
//            resposta.topico.id == idTopico
//        }.collect(toList())
//    }
//
//    fun cadastrar(respostaDE: RespostaDE, idTopico: Long) {
//        val resposta = Resposta(
//                mensagem = respostaDE.mensagem,
//                autor = usuarioCasoUso.buscarUsuarioPorId(respostaDE.idAutor),
//                topico = topicoCasoUso.buscarPorIdBase(idTopico),
//        )
//        respostas = respostas.plus(resposta)
//    }
//}