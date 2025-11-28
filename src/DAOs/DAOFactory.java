package DAOs;

import MySQL.MySQLDAOFactory;

public abstract class DAOFactory {

    public static final int MYSQL = 1;

    public abstract AlumnoDAO getAlumnoDAO();
    public abstract ProfesorDAO getProfesorDAO();
    public abstract AsignaturaDAO getAsignaturaDAO();
    public abstract MatrículaDAO getMatriculaDAO();

    public static DAOFactory getDAOFactory(int tipo) {
        switch (tipo) {
            case MYSQL:
                return new MySQLDAOFactory();
            default:
                throw new IllegalArgumentException("Tipo de DAOFactory no soportado: " + tipo);
        }
    }
}