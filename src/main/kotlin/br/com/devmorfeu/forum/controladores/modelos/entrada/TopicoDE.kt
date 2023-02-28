package br.com.devmorfeu.forum.controladores.modelos.entrada

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class TopicoDE(

        @field:NotEmpty
        val titulo: String,

        @field:NotEmpty
        val mensagem: String,

        @field:NotNull
        @JsonProperty("id_curso")
        val idCurso: Long,

        @field:NotNull
        @JsonProperty("id_autor")
        val idAutor: Long
)