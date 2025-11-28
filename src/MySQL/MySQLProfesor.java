package MySQL;

import DAOs.ProfesorDAO;
import Modelo.Profesor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLProfesor implements ProfesorDAO {

    @Override
    public void insertar(Profesor p) {

        String sql = "INSERT INTO profesores (id_profesor, nombre, apellidos) VALUES (?, ?, ?)";

        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setLong(1, p.getIdProfesor());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getApellidos());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(Profesor p) {

        String sql = "UPDATE profesores SET nombre=?, apellidos=? WHERE id_profesor=?";

        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellidos());
            ps.setLong(3, p.getIdProfesor());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Profesor a) {
        String sql= "DELETE FROM profesores WHERE id_profesor=?";
        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setLong(1, a.getIdProfesor());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Profesor> obtenerTodos() {

        List<Profesor> lista = new ArrayList<>();
        String sql = "SELECT * FROM profesores";

        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Profesor p = new Profesor(
                        rs.getLong("id_profesor"),
                        rs.getString("nombre"),
                        rs.getString("apellidos")
                );

                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public Profesor obtener(Long id) {

        String sql = "SELECT * FROM profesores WHERE id_profesor=?";
        Profesor p = null;

        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                p = new Profesor(
                        rs.getLong("id_profesor"),
                        rs.getString("nombre"),
                        rs.getString("apellidos")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return p;
    }
}
