package br.com.devmorfeu.forum.seguranca.filtros

import br.com.devmorfeu.forum.seguranca.JwtUtilidade
import br.com.devmorfeu.forum.seguranca.modelos.Credenciais
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtLoginFiltro(private val authmanager: AuthenticationManager,
                     private val jwtUtilidade: JwtUtilidade)

    : UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {

        val (email, senha) = ObjectMapper().readValue(request?.inputStream, Credenciais::class.java)

        val token = UsernamePasswordAuthenticationToken(email, senha)

        return authmanager.authenticate(token)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        val usuario = (authResult?.principal as UserDetails)

        val token = jwtUtilidade.gerarToken(usuario.username, usuario.authorities)

        response?.addHeader("Authorization", "Bearer $token")
    }
}