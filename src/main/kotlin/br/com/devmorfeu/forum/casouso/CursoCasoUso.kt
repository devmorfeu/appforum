package br.com.devmorfeu.forum.casouso

import br.com.devmorfeu.forum.modelos.Curso
import org.springframework.stereotype.Service
import java.util.*

@Service
class CursoCasoUso(var cursos: List<Curso>) {

    init {
        val curso = Curso(
                id = 1,
                nome = "Sexo bicho kkk",
                categoria = "Sexolandia"
        )
        cursos = Arrays.asList(curso);
    }

    fun buscarCursoPorId(idCurso: Long): Curso {
        return cursos.stream()
                .filter { curso -> curso.id == idCurso }
                .findFirst()
                .get()
    }
}