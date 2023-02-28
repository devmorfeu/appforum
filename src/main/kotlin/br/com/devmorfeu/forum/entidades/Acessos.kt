package br.com.devmorfeu.forum.entidades

import org.springframework.security.core.GrantedAuthority
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id

@Entity
data class Acessos(

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private val id: Long,

    private val nome: String

) : GrantedAuthority {

    override fun getAuthority() = nome
}