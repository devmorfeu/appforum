package br.com.devmorfeu.forum.controladores.dto.entrada

import jakarta.validation.constraints.NotEmpty

data class RespostaDE (
        @field:NotEmpty
        val mensagem: String,
        val idAutor: Long
)