package br.com.devmorfeu.forum.entidades

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class Usuario(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        val nome: String,
        val email: String,
        val senha: String,

        @JsonIgnore
        @ManyToMany(fetch = FetchType.EAGER)
        @JoinColumn(name = "usuario_role")
        val acessos: List<Acessos> = mutableListOf()
)