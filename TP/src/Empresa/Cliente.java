package Empresa;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private int codigoUnico;
    private String nombre;
    private String direccion;
    private String dni;
    private List<Paquete> reservas;

    public Cliente(int codigoUnico, String nombre, String direccion, String dni) {
        this.codigoUnico = codigoUnico;
        this.nombre = nombre;
        this.direccion = direccion;
        this.dni = dni;
        this.reservas = new ArrayList<>();
    }

    public void adquirir(Paquete paquete) {
        reservas.add(paquete);
    }

    public void eliminar(int codigoPaquete) {
        reservas.removeIf(paquete -> paquete.getCodigoUnico() == codigoPaquete);
    }

    public List<Paquete> getReservas() {
        return reservas;
    }

    public String getDni() {
        return dni;
    }

	public String getNombre() {
		
		return nombre;
	}
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente [codigoUnico=").append(codigoUnico)
          .append(", nombre=").append(nombre)
          .append(", direccion=").append(direccion)
          .append(", dni=").append(dni)
          .append(", reservas=");
        for (Paquete paquete : reservas) {
            sb.append(paquete.toString()).append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

	public List<Integer> obtenerHistorialContrataciones() {
		// TODO Auto-generated method stub
		return null;
	}


}