package br.com.devmorfeu.forum.controladores.dto.entrada

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

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