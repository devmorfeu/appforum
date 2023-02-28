package br.com.devmorfeu.forum.seguranca.filtros

import br.com.devmorfeu.forum.seguranca.JwtUtilidade
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAutenticacaoFiltro(private val jwtUtilidade: JwtUtilidade): OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filter: FilterChain) {
        val token = request.getHeader("Authorization").let { token ->
            token.startsWith("Bearer ")
            token.substring(7, token.length)
        }

        if (jwtUtilidade.validar(token)) {
            val autenticacao = jwtUtilidade.autenticacao(token)
            SecurityContextHolder.getContext().authentication = autenticacao
        }

        filter.doFilter(request, response)
    }
}