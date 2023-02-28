package br.com.devmorfeu.forum.controladores.modelos.saida

import br.com.devmorfeu.forum.entidades.StatusTopico
import java.time.LocalDateTime

data class TopicoDS (
        val id: Long,
        val titulo: String,
        val mensagem: String,
        val status: StatusTopico,
        val dataCriacao: LocalDateTime
)