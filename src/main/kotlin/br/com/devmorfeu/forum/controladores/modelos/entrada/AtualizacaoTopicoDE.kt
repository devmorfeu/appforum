package br.com.devmorfeu.forum.controladores.modelos.entrada

import org.jetbrains.annotations.NotNull
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class AtualizacaoTopicoDE (

        @field:NotNull
        val id: Long,

        @field:NotEmpty
        @field:Size(min = 5, max = 100)
        val titulo: String,

        @field:NotEmpty
        val mensagem: String
)