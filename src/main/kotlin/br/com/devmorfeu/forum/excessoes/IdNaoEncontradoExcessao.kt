package br.com.devmorfeu.forum.excessoes

import java.lang.RuntimeException

class IdNaoEncontradoExcessao (mensagem: String?) : RuntimeException(mensagem)