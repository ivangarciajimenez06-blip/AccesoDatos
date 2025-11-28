package MySQL;

import DAOs.AlumnoDAO;
import Modelo.Alumno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAlumno implements AlumnoDAO {

    @Override
    public void insertar(Alumno a) {

        String sql = "INSERT INTO alumnos (id_alumno, nombre, apellidos, fecha_nac) VALUES (?, ?, ?, ?)";

        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setLong(1, a.getIdAlumno());
            ps.setString(2, a.getNombre());
            ps.setString(3, a.getApellidos());
            ps.setDate(4, Date.valueOf(a.getFechaNac()));

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(Alumno a) {

        String sql = "UPDATE alumnos SET nombre=?, apellidos=?, fecha_nac=? WHERE id_alumno=?";

        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, a.getNombre());
            ps.setString(2, a.getApellidos());
            ps.setDate(3, Date.valueOf(a.getFechaNac()));
            ps.setLong(4, a.getIdAlumno());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Alumno a) {
        String sql= "DELETE FROM alumnos WHERE id_alumno=?";
        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setLong(1, a.getIdAlumno());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Alumno> obtenerTodos() {

        List<Alumno> lista = new ArrayList<>();
        String sql = "SELECT * FROM alumnos";

        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Alumno a = new Alumno(
                        rs.getLong("id_alumno"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getDate("fecha_nac").toLocalDate()
                );
                lista.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public Alumno obtener(Long id) {

        String sql = "SELECT * FROM alumnos WHERE id_alumno=?";

        Alumno a = null;

        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                a = new Alumno(
                        rs.getLong("id_alumno"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getDate("fecha_nac").toLocalDate()
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return a;
    }
}
