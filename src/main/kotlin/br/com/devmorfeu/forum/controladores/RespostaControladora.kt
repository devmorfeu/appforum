//package br.com.devmorfeu.forum.controladores
//
//import br.com.devmorfeu.forum.casouso.RespostaCasoUso
//import br.com.devmorfeu.forum.controladores.dto.entrada.RespostaDE
//import br.com.devmorfeu.forum.modelos.Resposta
//import jakarta.validation.Valid
//import org.springframework.web.bind.annotation.*
//
//@RestController
//@RequestMapping("/topicos/{id}/respostas")
//class RespostaControladora(private val respostaCasoUso: RespostaCasoUso) {
//
//    @GetMapping
//    fun listar(@PathVariable id: Long): List<Resposta> {
//        return respostaCasoUso.listar(id)
//    }
//
//    @PostMapping
//    fun cadastrar(@PathVariable id: Long, @RequestBody @Valid respostaDE: RespostaDE) {
//        respostaCasoUso.cadastrar(respostaDE, id)
//    }
//}