package DAOs;

import Modelo.Matrícula;
import java.util.List;

public interface MatrículaDAO {

    void insertar(Matrícula m);

    void actualizar(Matrícula m);

    void eliminar(Long idAlumno, Long idAsignatura);

    List<Matrícula> obtenerTodos();

    Matrícula obtener(Long idAlumno, Long idAsignatura);
}
