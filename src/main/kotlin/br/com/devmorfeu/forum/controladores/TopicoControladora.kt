package br.com.devmorfeu.forum.controladores

import br.com.devmorfeu.forum.casosusos.TopicoCasoUso
import br.com.devmorfeu.forum.controladores.modelos.entrada.AtualizacaoTopicoDE
import br.com.devmorfeu.forum.controladores.modelos.entrada.TopicoDE
import br.com.devmorfeu.forum.controladores.modelos.saida.TopicoCategoriaDS
import br.com.devmorfeu.forum.controladores.modelos.saida.TopicoDS
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort.Direction.DESC
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/topicos")
class TopicoControladora(private val topicoCasoUso: TopicoCasoUso) {

    @GetMapping
    @Cacheable("topicos")
    fun listar(@RequestParam(required = false) nomeCurso: String?,
               @PageableDefault(size = 5, sort = ["dataCriacao"], direction = DESC) paginacao: Pageable): Page<TopicoDS> {

        return topicoCasoUso.listar(nomeCurso, paginacao)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): TopicoDS {
        return topicoCasoUso.buscarPorId(id)
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun cadastrar(@RequestBody @Valid topicoDE: TopicoDE, uriBuilder: UriComponentsBuilder): ResponseEntity<TopicoDS> {

        val topicoResposta = topicoCasoUso.cadastrar(topicoDE)

        val uri = uriBuilder.path("/topicos/${topicoResposta.id}").build().toUri()

        return ResponseEntity.created(uri).body(topicoResposta)
    }

    @PutMapping
    @Transactional
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun atualizar(@RequestBody @Valid atualizacaoTopicoDE: AtualizacaoTopicoDE) {

        topicoCasoUso.atualizar(atualizacaoTopicoDE)
    }

    @Transactional
    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun deletar(@PathVariable id: Long) {
        topicoCasoUso.deletar(id)
    }

    @GetMapping("/relatorios")
    fun buscarPorId(): List<TopicoCategoriaDS> {
        return topicoCasoUso.buscarRelatorio()
    }
}