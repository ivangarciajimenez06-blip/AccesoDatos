package DAOs;

import MySQL.MySQLAlumno;
import MySQL.MySQLProfesor;
import MySQL.MySQLAsignatura;
import MySQL.MySQLMatricula;

/**
 * ManagerDAO se encarga de centralizar las instancias
 * de los objetos DAO. Implementa el patrón Singleton.
 */
public class ManagerDAO {

    private static ManagerDAO instance;

    private final AlumnoDAO alumnoDAO;
    private final ProfesorDAO profesorDAO;
    private final AsignaturaDAO asignaturaDAO;
    private final MatrículaDAO matrículaDAO;

    // Constructor privado -> Singleton
    private ManagerDAO() {
        this.alumnoDAO = new MySQLAlumno();
        this.profesorDAO = new MySQLProfesor();
        this.asignaturaDAO = new MySQLAsignatura();
        this.matrículaDAO = new MySQLMatricula();
    }

    // Único punto de acceso a la instancia
    public static synchronized ManagerDAO getInstance() {
        if (instance == null) {
            instance = new ManagerDAO();
        }
        return instance;
    }

    public AlumnoDAO getAlumnoDAO() {
        return alumnoDAO;
    }

    public ProfesorDAO getProfesorDAO() {
        return profesorDAO;
    }

    public AsignaturaDAO getAsignaturaDAO() {
        return asignaturaDAO;
    }

    public MatrículaDAO getMatrículaDAO() {
        return matrículaDAO;
    }
}

