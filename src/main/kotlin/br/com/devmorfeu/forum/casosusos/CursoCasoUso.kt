package br.com.devmorfeu.forum.casosusos

import br.com.devmorfeu.forum.entidades.Curso
import br.com.devmorfeu.forum.repositorios.CursoRepositorio
import org.springframework.stereotype.Service

@Service
class CursoCasoUso(private val repositorio: CursoRepositorio) {

    fun buscarCursoPorId(idCurso: Long): Curso {
        return repositorio.getOne(idCurso)
    }
}