package br.com.devmorfeu.forum.casouso

import br.com.devmorfeu.forum.modelos.Curso
import br.com.devmorfeu.forum.repositorio.CursoRepositorio
import org.springframework.stereotype.Service

@Service
class CursoCasoUso(private val repositorio: CursoRepositorio) {

    fun buscarCursoPorId(idCurso: Long): Curso {
        return repositorio.getReferenceById(idCurso)
    }
}