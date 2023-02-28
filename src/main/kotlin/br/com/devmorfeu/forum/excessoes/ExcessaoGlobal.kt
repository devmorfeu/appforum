package br.com.devmorfeu.forum.excessoes

import br.com.devmorfeu.forum.excessoes.modelos.ErroDS
import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest


@RestControllerAdvice
class ExcessaoGlobal {

    @ExceptionHandler(IdNaoEncontradoExcessao::class)
    @ResponseStatus(NOT_FOUND)
    fun controladoraIdNaoEncontrado(excessao: IdNaoEncontradoExcessao, entrada: HttpServletRequest): ErroDS {

        return ErroDS(
            status = NOT_FOUND.value(),
            erro = NOT_FOUND.name,
            mensagem = excessao.message,
            rota = entrada.servletPath
        )

    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    fun controladoraGenerico(excessao: Exception, entrada: HttpServletRequest): ErroDS {

        return ErroDS(
            status = INTERNAL_SERVER_ERROR.value(),
            erro = INTERNAL_SERVER_ERROR.name,
            mensagem = excessao.message,
            rota = entrada.servletPath
        )

    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(BAD_REQUEST)
    fun controladoraErrosEntrada(excessao: MethodArgumentNotValidException, entrada: HttpServletRequest): ErroDS {

        val erroMensagens = HashMap<String, String?>()

        excessao.bindingResult.fieldErrors.forEach{
            erro -> erroMensagens.put(erro.field, erro.defaultMessage)
        }

        return ErroDS(
            status = BAD_REQUEST.value(),
            erro = BAD_REQUEST.name,
            mensagem = erroMensagens.toString(),
            rota = entrada.servletPath
        )

    }
}