package repository;
import model.Reserva;
import java.util.List;

public interface ReservaRepository {
    void agregarReserva(Reserva r);
    void modificarReserva(Reserva r);
    void eliminarReserva(int id);
    List<Reserva> listarReservas();
}