package MySQL;

import DAOs.AsignaturaDAO;
import Modelo.Asignatura;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAsignatura implements AsignaturaDAO {

    @Override
    public void insertar(Asignatura a) {

        String sql = "INSERT INTO asignaturas (id_asignatura, nombre, profesor) VALUES (?, ?, ?)";

        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setLong(1, a.getIdAsignatura());
            ps.setString(2, a.getNombre());
            ps.setLong(3, a.getProfesor());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(Asignatura a) {

        String sql = "UPDATE asignaturas SET nombre=?, profesor=? WHERE id_asignatura=?";

        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, a.getNombre());
            ps.setLong(2, a.getProfesor());
            ps.setLong(3, a.getIdAsignatura());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Asignatura a) {
        String sql= "DELETE FROM asignaturas WHERE id_asignatura=?";
        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setLong(1, a.getIdAsignatura());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public List<Asignatura> obtenerTodos() {

        List<Asignatura> lista = new ArrayList<>();
        String sql = "SELECT * FROM asignaturas";

        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Asignatura a = new Asignatura(
                        rs.getLong("id_asignatura"),
                        rs.getString("nombre"),
                        rs.getLong("profesor")
                );

                lista.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public Asignatura obtener(Long id) {

        String sql = "SELECT * FROM asignaturas WHERE id_asignatura = ?";
        Asignatura a = null;

        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                a = new Asignatura(
                        rs.getLong("id_asignatura"),
                        rs.getString("nombre"),
                        rs.getLong("profesor")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return a;
    }
}
