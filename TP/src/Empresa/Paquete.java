package Empresa;

import java.util.ArrayList;
import java.util.List;

public class Paquete {
    private int codigoUnico;
    private int cantPersonasXpaquete;
    private List<Servicio> servicios;

    public Paquete(int codigoUnico, int cantPersonasXpaquete) {
        this.codigoUnico = codigoUnico;
        this.cantPersonasXpaquete = cantPersonasXpaquete;
        this.servicios = new ArrayList<>();
    }

    public void agregarServicio(Servicio servicio) {
        servicios.add(servicio);
    }

    public void quitarServicio(int codigoServicio) {
        servicios.removeIf(servicio -> servicio.getCodigoUnico() == codigoServicio);
    }

    public double calcularCostoTotal() {
        double totalCosto = 0;
        for (Servicio servicio : servicios) {
            totalCosto += servicio.calcularCosto();
        }
        return totalCosto;
    }

    public double aplicarDescuentos() {
        double totalCosto = calcularCostoTotal();
        int cantidadServicios = servicios.size();
        if (cantidadServicios >= 3) {
            return totalCosto * 0.9; // 10% de descuento
        } else if (cantidadServicios == 2) {
            return totalCosto * 0.95; // 5% de descuento
        }
        return totalCosto;
    }

    public int getCodigoUnico() {
        return codigoUnico;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Paquete [codigoUnico=").append(codigoUnico)
          .append(", cantPersonasXpaquete=").append(cantPersonasXpaquete)
          .append(", servicios=");
        for (Servicio servicio : servicios) {
            sb.append(servicio.toString()).append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}