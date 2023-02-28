package br.com.devmorfeu.forum.controladores.modelos.entrada

import javax.validation.constraints.NotEmpty

data class RespostaDE (
        @field:NotEmpty
        val mensagem: String,
        val idAutor: Long
)