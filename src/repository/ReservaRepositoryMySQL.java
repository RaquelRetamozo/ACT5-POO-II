package repository;
import model.Reserva;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaRepositoryMySQL implements ReservaRepository {

    private static final String URL = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "tu_password";

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }

    @Override
    public void agregarReserva(Reserva r) {
        String sql = "INSERT INTO reservas (id, nombreCliente, fechaIngreso, fechaSalida, habitacion) VALUES (?,?,?,?,?)";
        try (Connection con = conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, r.getId());
            ps.setString(2, r.getNombreCliente());
            ps.setString(3, r.getFechaIngreso());
            ps.setString(4, r.getFechaSalida());
            ps.setString(5, r.getHabitacion());
            ps.executeUpdate();
            System.out.println("Reserva agregada en MySQL.");
        } catch (SQLException ex) {
            System.out.println("Error al agregar: " + ex.getMessage());
        }
    }

    @Override
    public void modificarReserva(Reserva r) {
        String sql = "UPDATE reservas SET nombreCliente=?, fechaIngreso=?, fechaSalida=?, habitacion=? WHERE id=?";
        try (Connection con = conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, r.getNombreCliente());
            ps.setString(2, r.getFechaIngreso());
            ps.setString(3, r.getFechaSalida());
            ps.setString(4, r.getHabitacion());
            ps.setInt(5, r.getId());
            ps.executeUpdate();
            System.out.println("Reserva actualizada en MySQL.");
        } catch (SQLException ex) {
            System.out.println("Error al modificar: " + ex.getMessage());
        }
    }

    @Override
    public void eliminarReserva(int id) {
        String sql = "DELETE FROM reservas WHERE id=?";
        try (Connection con = conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Reserva eliminada de MySQL.");
        } catch (SQLException ex) {
            System.out.println("Error al eliminar: " + ex.getMessage());
        }
    }

    @Override
    public List<Reserva> listarReservas() {
        List<Reserva> lista = new ArrayList<>();
        String sql = "SELECT * FROM reservas";
        try (Connection con = conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Reserva r = new Reserva();
                r.setId(rs.getInt("id"));
                r.setNombreCliente(rs.getString("nombreCliente"));
                r.setFechaIngreso(rs.getString("fechaIngreso"));
                r.setFechaSalida(rs.getString("fechaSalida"));
                r.setHabitacion(rs.getString("habitacion"));
                lista.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar: " + ex.getMessage());
        }
        return lista;
    }
}
