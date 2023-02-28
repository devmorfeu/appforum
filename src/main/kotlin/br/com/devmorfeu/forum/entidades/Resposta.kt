package br.com.devmorfeu.forum.entidades

import java.time.LocalDateTime
import java.time.LocalDateTime.now
import javax.persistence.*

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