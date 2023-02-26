package br.com.devmorfeu.forum.controladores

import br.com.devmorfeu.forum.casouso.TopicoCasoUso
import br.com.devmorfeu.forum.controladores.dto.entrada.AtualizacaoTopicoDE
import br.com.devmorfeu.forum.controladores.dto.entrada.TopicoDE
import br.com.devmorfeu.forum.controladores.dto.saida.TopicoDS
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/topicos")
class TopicoControladora(private val topicoCasoUso: TopicoCasoUso) {

    @GetMapping
    fun listar(): List<TopicoDS> {

        return topicoCasoUso.listar()
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): TopicoDS {
        return topicoCasoUso.buscarPorId(id)
    }

    @PostMapping
    fun cadastrar(@RequestBody @Valid topicoDE: TopicoDE, uriBuilder: UriComponentsBuilder): ResponseEntity<TopicoDS> {

        val topicoResposta = topicoCasoUso.cadastrar(topicoDE)

        val uri = uriBuilder.path("/topicos/${topicoResposta.id}").build().toUri()

        return ResponseEntity.created(uri).body(topicoResposta)
    }

    @PutMapping
    fun atualizar(@RequestBody @Valid atualizacaoTopicoDE: AtualizacaoTopicoDE) {

        topicoCasoUso.atualizar(atualizacaoTopicoDE)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    fun deletar(@PathVariable id: Long) {
        topicoCasoUso.deletar(id)
    }
}