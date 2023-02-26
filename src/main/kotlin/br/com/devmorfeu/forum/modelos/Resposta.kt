package br.com.devmorfeu.forum.modelos

import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.LocalDateTime.now

@Entity
data class Resposta(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        val mensagem: String,
        val dataCriacao: LocalDateTime = now(),

        @ManyToOne
        val autor: Usuario,

        @ManyToOne
        val topico: Topico,

        val solucao: Boolean? = false
)