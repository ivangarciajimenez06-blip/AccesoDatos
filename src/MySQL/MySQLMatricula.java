package MySQL;

import DAOs.MatrículaDAO;
import Modelo.Matrícula;
import Modelo.Matrícula;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLMatricula implements MatrículaDAO {

    @Override
    public void insertar(Matrícula m) {

        String sql = "INSERT INTO matriculas (alumno, asignatura, fecha, nota) VALUES (?, ?, ?, ?)";

        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setLong(1, m.getAlumno());
            ps.setLong(2, m.getAsignatura());
            ps.setLong(3, m.getFecha());
            if (m.getNota() == null)
                ps.setNull(4, Types.INTEGER);
            else
                ps.setInt(4, m.getNota());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void actualizar(Matrícula m) {

        String sql = "UPDATE matriculas SET fecha=?, nota=? WHERE alumno=? AND asignatura=?";

        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setLong(1, m.getFecha());
            if (m.getNota() == null)
                ps.setNull(2, Types.INTEGER);
            else
                ps.setInt(2, m.getNota());

            ps.setLong(3, m.getAlumno());
            ps.setLong(4, m.getAsignatura());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Long alumno, Long asignatura) {

        String sql = "DELETE FROM matriculas WHERE alumno=? AND asignatura=?";

        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setLong(1, alumno);
            ps.setLong(2, asignatura);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Matrícula> obtenerTodos() {

        List<Matrícula> lista = new ArrayList<>();
        String sql = "SELECT * FROM matriculas";

        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Matrícula m = new Matrícula(
                        rs.getLong("alumno"),
                        rs.getLong("asignatura"),
                        rs.getLong("fecha"),
                        (rs.getObject("nota") == null ? null : rs.getInt("nota"))
                );
                lista.add(m);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public Matrícula obtener(Long alumno, Long asignatura) {

        String sql = "SELECT * FROM matriculas WHERE alumno=? AND asignatura=?";
        Matrícula m = null;

        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setLong(1, alumno);
            ps.setLong(2, asignatura);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                m = new Matrícula(
                        rs.getLong("alumno"),
                        rs.getLong("asignatura"),
                        rs.getLong("fecha"),
                        (rs.getObject("nota") == null ? null : rs.getInt("nota"))
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return m;
    }
}
