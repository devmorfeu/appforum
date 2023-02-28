package br.com.devmorfeu.forum.seguranca.configuracoes

import br.com.devmorfeu.forum.seguranca.JwtUtilidade
import br.com.devmorfeu.forum.seguranca.filtros.JwtAutenticacaoFiltro
import br.com.devmorfeu.forum.seguranca.filtros.JwtLoginFiltro
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod.POST
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy.STATELESS
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SegurancaConfiguracao(private val userDetailsService: UserDetailsService, private val jwtUtilidade: JwtUtilidade)

    : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http
            ?.csrf()
            ?.disable()
            ?.authorizeRequests()
            ?.antMatchers("/topicos")?.hasAnyAuthority("LEITURA_ESCRITA")
            ?.antMatchers(POST,"/login")?.permitAll()
            ?.anyRequest()
            ?.authenticated()
            ?.and()
            ?.addFilterBefore(JwtLoginFiltro(authmanager = authenticationManager(), jwtUtilidade = jwtUtilidade), UsernamePasswordAuthenticationFilter().javaClass)
            ?.addFilterBefore(JwtAutenticacaoFiltro(jwtUtilidade), UsernamePasswordAuthenticationFilter().javaClass)
            ?.sessionManagement()
            ?.sessionCreationPolicy(STATELESS)
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailsService)?.passwordEncoder(bCryptSenha())
    }

    @Bean
    private fun bCryptSenha(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}