package MySQL;

import DAOs.*;
import Modelo.*;

public class MySQLDAOFactory extends DAOFactory {

    @Override
    public AlumnoDAO getAlumnoDAO() {
        return new MySQLAlumno();
    }

    @Override
    public ProfesorDAO getProfesorDAO() {
        return new MySQLProfesor();
    }

    @Override
    public AsignaturaDAO getAsignaturaDAO() {
        return new MySQLAsignatura();
    }

    @Override
    public MatrículaDAO getMatriculaDAO() {
        return new MySQLMatricula();
    }

}