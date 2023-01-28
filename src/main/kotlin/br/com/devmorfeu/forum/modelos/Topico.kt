package br.com.devmorfeu.forum.modelos

import java.time.LocalDateTime
import java.time.LocalDateTime.now

data class Topico(
        val id: Long? = null,
        val titulo: String,
        val mensagem: String,
        val dataCriacao: LocalDateTime = now(),
        val curso: Curso,
        val autor: Usuario,
        val status: StatusTopico = StatusTopico.NAO_RESPONDIDO,
        val respostas: List<Resposta> = ArrayList()
)