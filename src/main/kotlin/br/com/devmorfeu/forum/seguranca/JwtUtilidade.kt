package br.com.devmorfeu.forum.seguranca

import br.com.devmorfeu.forum.casosusos.UsuarioCasoUso
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtilidade (private val usuarioCasoUso: UsuarioCasoUso) {

    private val tempoToken: Long = 60000

    @Value("\${jwt.secret}")
    private lateinit var segredo: String

    fun gerarToken(email: String, authorities: MutableCollection<out GrantedAuthority>): String? {
        return Jwts
            .builder()
            .setSubject(email)
            .claim("role", authorities)
            .setExpiration(Date(System.currentTimeMillis() + tempoToken))
            .signWith(SignatureAlgorithm.HS512, segredo.toByteArray())
            .compact()
    }

    fun validar(token: String): Boolean {

        return try {
            Jwts.parser().setSigningKey(segredo.toByteArray()).parseClaimsJwt(token)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    fun autenticacao(token: String): Authentication {

        val email = Jwts.parser().setSigningKey(segredo.toByteArray()).parseClaimsJwt(token).body.subject

        val usuario = usuarioCasoUso.loadUserByUsername(email)
        return UsernamePasswordAuthenticationToken(email, null, usuario.authorities)
    }
}