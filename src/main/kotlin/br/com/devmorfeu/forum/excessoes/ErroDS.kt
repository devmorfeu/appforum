package br.com.devmorfeu.forum.excessoes

import java.time.LocalDateTime

data class ErroDS(
    val tempo: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val erro: String,
    val mensagem: String?,
    val rota: String
)