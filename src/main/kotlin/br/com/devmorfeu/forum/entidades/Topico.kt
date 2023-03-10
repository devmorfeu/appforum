package br.com.devmorfeu.forum.entidades

import java.time.LocalDateTime
import java.time.LocalDateTime.now
import javax.persistence.*

@Entity
data class Topico(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        var titulo: String,
        var mensagem: String,
        val dataCriacao: LocalDateTime = now(),

        @ManyToOne
        val curso: Curso,

        @ManyToOne
        val autor: Usuario,

        @Enumerated(value = EnumType.STRING)
        val status: StatusTopico = StatusTopico.NAO_RESPONDIDO,

        @OneToMany(mappedBy = "topico")
        val respostas: List<Resposta> = ArrayList()
)