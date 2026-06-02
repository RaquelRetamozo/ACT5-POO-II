package repository;
import model.Reserva;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaRepositoryArchivo implements ReservaRepository {

    private static final String ARCHIVO = "reservas.txt";

    private List<Reserva> leerTodos() {
        List<Reserva> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split("\\|");
                Reserva r = new Reserva();
                r.setId(Integer.parseInt(p[0]));
                r.setNombreCliente(p[1]);
                r.setFechaIngreso(p[2]);
                r.setFechaSalida(p[3]);
                r.setHabitacion(p[4]);
                lista.add(r);
            }
        } catch (IOException ex) {
            System.out.println("Archivo no encontrado, se creará uno nuevo.");
        }
        return lista;
    }

    private void escribirTodos(List<Reserva> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Reserva r : lista) {
                bw.write(r.toString());
                bw.newLine();
            }
        } catch (IOException ex) {
            System.out.println("Error al escribir: " + ex.getMessage());
        }
    }

    @Override
    public void agregarReserva(Reserva r) {
        List<Reserva> lista = leerTodos();
        lista.add(r);
        escribirTodos(lista);
    }

    @Override
    public void modificarReserva(Reserva r) {
        List<Reserva> lista = leerTodos();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == r.getId()) {
                lista.set(i, r);
                break;
            }
        }
        escribirTodos(lista);
    }

    @Override
    public void eliminarReserva(int id) {
        List<Reserva> lista = leerTodos();
        lista.removeIf(r -> r.getId() == id);
        escribirTodos(lista);
    }

    @Override
    public List<Reserva> listarReservas() {
        return leerTodos();
    }
}