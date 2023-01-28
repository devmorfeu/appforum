package br.com.devmorfeu.forum.modelos

import java.time.LocalDateTime
import java.time.LocalDateTime.*

data class Resposta(
        val id: Long? = null,
        val mensagem: String,
        val dataCriacao: LocalDateTime = now(),
        val autor: Usuario,
        val topico: Topico,
        val solucao: Boolean? = false
)