package Modelo;

public class Matrícula {

    private Long alumno;        // FK a id_alumno
    private Long asignatura;    // FK a id_asignatura
    private Long fecha;         // YEAR
    private Integer nota;       // puede ser null



    public Matrícula(Long alumno, Long asignatura, Long fecha, Integer nota) {
        this.alumno = alumno;
        this.asignatura = asignatura;
        this.fecha = fecha;
        this.nota = nota;
    }

    public Long getAlumno() {
        return alumno;
    }

    public void setAlumno(Long alumno) {
        this.alumno = alumno;
    }

    public Long getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Long asignatura) {
        this.asignatura = asignatura;
    }

    public Long getFecha() {
        return fecha;
    }

    public void setFecha(Long fecha) {
        this.fecha = fecha;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Matricula{" +
                "alumno=" + alumno +
                ", asignatura=" + asignatura +
                ", fecha=" + fecha +
                ", nota=" + nota +
                '}';
    }
}
