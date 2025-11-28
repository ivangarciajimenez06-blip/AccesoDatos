package app;

import DAOs.*;
import Modelo.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);

        AlumnoDAO alumnoDAO = factory.getAlumnoDAO();
        ProfesorDAO profesorDAO = factory.getProfesorDAO();
        AsignaturaDAO asignaturaDAO = factory.getAsignaturaDAO();
        MatrículaDAO matriculaDAO = factory.getMatriculaDAO();

        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Elige una opción: ");

            switch (opcion) {
                case 1 -> insertarAlumno(alumnoDAO);
                case 2 -> listarAlumnos(alumnoDAO);
                case 3 -> insertarProfesor(profesorDAO);
                case 4 -> listarProfesores(profesorDAO);
                case 5 -> insertarAsignatura(asignaturaDAO);
                case 6 -> listarAsignaturas(asignaturaDAO);
                case 7 -> insertarMatricula(matriculaDAO);
                case 8 -> listarMatriculas(matriculaDAO);
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }

        } while (opcion != 0);

        sc.close();
    }

    // -------- MENU --------

    private static void mostrarMenu() {
        System.out.println("\n===== MENÚ INSTITUTO =====");
        System.out.println("1. Insertar alumno");
        System.out.println("2. Listar alumnos");
        System.out.println("3. Insertar profesor");
        System.out.println("4. Listar profesores");
        System.out.println("5. Insertar asignatura");
        System.out.println("6. Listar asignaturas");
        System.out.println("7. Insertar matrícula");
        System.out.println("8. Listar matrículas");
        System.out.println("0. Salir");
    }

    // -------- ALUMNO --------

    private static void insertarAlumno(AlumnoDAO dao) {
        System.out.println("\n--- Insertar alumno ---");
        Long id = leerLong("ID alumno: ");
        String nombre = leerTexto("Nombre: ");
        String apellidos = leerTexto("Apellidos: ");
        String fechaStr = leerTexto("Fecha nacimiento (YYYY-MM-DD): ");
        LocalDate fechaNac = LocalDate.parse(fechaStr);

        Alumno a = new Alumno(id, nombre, apellidos, fechaNac);
        dao.insertar(a);
        System.out.println("Alumno insertado correctamente.");
    }

    private static void listarAlumnos(AlumnoDAO dao) {
        System.out.println("\n--- Listar alumnos ---");
        List<Alumno> lista = dao.obtenerTodos();
        if (lista.isEmpty()) {
            System.out.println("No hay alumnos.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    // -------- PROFESOR --------

    private static void insertarProfesor(ProfesorDAO dao) {
        System.out.println("\n--- Insertar profesor ---");
        Long id = leerLong("ID profesor: ");
        String nombre = leerTexto("Nombre: ");
        String apellidos = leerTexto("Apellidos: ");

        Profesor p = new Profesor(id, nombre, apellidos);
        dao.insertar(p);
        System.out.println("Profesor insertado correctamente.");
    }

    private static void listarProfesores(ProfesorDAO dao) {
        System.out.println("\n--- Listar profesores ---");
        List<Profesor> lista = dao.obtenerTodos();
        if (lista.isEmpty()) {
            System.out.println("No hay profesores.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    // -------- ASIGNATURA --------

    private static void insertarAsignatura(AsignaturaDAO dao) {
        System.out.println("\n--- Insertar asignatura ---");
        Long id = leerLong("ID asignatura: ");
        String nombre = leerTexto("Nombre: ");
        Long idProfesor = leerLong("ID profesor responsable: ");

        Asignatura a = new Asignatura(id, nombre, idProfesor);
        dao.insertar(a);
        System.out.println("Asignatura insertada correctamente.");
    }

    private static void listarAsignaturas(AsignaturaDAO dao) {
        System.out.println("\n--- Listar asignaturas ---");
        List<Asignatura> lista = dao.obtenerTodos();
        if (lista.isEmpty()) {
            System.out.println("No hay asignaturas.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    // -------- MATRÍCULA --------

    private static void insertarMatricula(MatrículaDAO dao) {
        System.out.println("\n--- Insertar matrícula ---");
        Long idAlumno = leerLong("ID alumno: ");
        Long idAsignatura = leerLong("ID asignatura: ");
        Long fecha = leerLong("Año (YYYY): ");
        String notaStr = leerTexto("Nota (o vacío si null): ");

        Integer nota = notaStr.isBlank() ? null : Integer.parseInt(notaStr);

        Matrícula m = new Matrícula(idAlumno, idAsignatura, fecha, nota);
        dao.insertar(m);
        System.out.println("Matrícula insertada correctamente.");
    }

    private static void listarMatriculas(MatrículaDAO dao) {
        System.out.println("\n--- Listar matrículas ---");
        List<Matrícula> lista = dao.obtenerTodos();
        if (lista.isEmpty()) {
            System.out.println("No hay matrículas.");
        } else {
            lista.forEach(m -> System.out.println(
                    "Alumno=" + m.getAlumno() +
                            ", Asignatura=" + m.getAsignatura() +
                            ", Año=" + m.getFecha() +
                            ", Nota=" + m.getNota()
            ));
        }
    }

    // -------- UTILIDADES LECTURA --------

    private static int leerEntero(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                int n = Integer.parseInt(sc.nextLine());
                return n;
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número entero válido.");
            }
        }
    }

    private static Long leerLong(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                long n = Long.parseLong(sc.nextLine());
                return n;
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número Long válido.");
            }
        }
    }

    private static String leerTexto(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }
}