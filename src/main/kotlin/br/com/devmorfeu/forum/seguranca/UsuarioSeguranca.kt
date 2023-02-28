package br.com.devmorfeu.forum.seguranca

import br.com.devmorfeu.forum.entidades.Usuario
import org.springframework.security.core.userdetails.UserDetails

class UsuarioSeguranca (private val usuario: Usuario): UserDetails {

    override fun getAuthorities() = usuario.acessos

    override fun getPassword() = usuario.senha

    override fun getUsername() = usuario.email

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}