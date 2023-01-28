package br.com.devmorfeu.forum.controladores

import br.com.devmorfeu.forum.casouso.TopicoCasoUso
import br.com.devmorfeu.forum.controladores.dto.entrada.TopicoDE
import br.com.devmorfeu.forum.controladores.dto.saida.TopicoDS
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

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
    fun cadastrar(@RequestBody @Valid topicoDE: TopicoDE) {
        topicoCasoUso.cadastrar(topicoDE)
    }
}