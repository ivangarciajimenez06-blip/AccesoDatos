package DAOs;

import java.util.List;

public interface DAO<T, k> {

    void insertar(T a);

    void actualizar(T a);

    void eliminar(T a);

    List<T> obtenerTodos();
    T obtener(k id);
}