package Modelo;

public class Asignatura {

    private Long idAsignatura;  // id_asignatura
    private String nombre;      // nombre
    private long profesor;      // profesor (FK a id_profesor)

    public Asignatura() {
    }

    public Asignatura(Long idAsignatura, String nombre, Long profesor) {
        this.idAsignatura = idAsignatura;
        this.nombre = nombre;
        this.profesor = profesor;
    }

    public Long getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Long idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getProfesor() {
        return profesor;
    }

    public void setProfesor(Long profesor) {
        this.profesor = profesor;
    }

    @Override
    public String toString() {
        return "Asignatura{" +
                "idAsignatura=" + idAsignatura +
                ", nombre='" + nombre + '\'' +
                ", profesor=" + profesor +
                '}';
    }
}
