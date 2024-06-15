package Empresa;

import java.util.*;

class Contratacion {
    private int codigoUnico;
    private String dniCliente;
    private List<Servicio> servicios;
    private boolean finalizada;
    private String fechaInicio;
    private String fechaPago;

    public Contratacion(int codigoUnico, String dniCliente, Servicio servicioInicial) {
        this.codigoUnico = codigoUnico;
        this.dniCliente = dniCliente;
        this.servicios = new ArrayList<>();
        this.servicios.add(servicioInicial);
        this.finalizada = false;
        this.fechaInicio = servicioInicial.getFechaInicio();
    }

    public void agregarServicio(Servicio servicio) {
        servicios.add(servicio);
    }

    public Servicio quitarServicio(int codigoServicio) {
        for (Iterator<Servicio> iterator = servicios.iterator(); iterator.hasNext();) {
            Servicio servicio = iterator.next();
            if (servicio.getCodigoUnico() == codigoServicio) {
                iterator.remove();
                return servicio;
            }
        }
        return null;
    }

    public double calcularCosto() {
        double costoTotal = 0.0;
        for (Servicio servicio : servicios) {
            costoTotal += servicio.calcularCosto();
        }
        if (servicios.size() > 1) {
            costoTotal *= 0.95; // Descuento del 5% por paquete
        }
        return costoTotal;
    }

    public double pagar(String fechaPago) {
        if (fechaPago.compareTo(this.fechaInicio) > 0) {
            throw new RuntimeException("La fecha de pago no puede ser posterior a la fecha de inicio");
        }
        this.finalizada = true;
        this.fechaPago = fechaPago;
        return calcularCosto();
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String obtenerTiposDeServicios() {
        StringBuilder tipos = new StringBuilder("[ ");
        for (Servicio servicio : servicios) {
            tipos.append(servicio.getClass().getSimpleName()).append(", ");
        }
        if (tipos.length() > 2) {
            tipos.setLength(tipos.length() - 2); // Eliminar la última coma y espacio
        }
        tipos.append(" ]");
        return tipos.toString();
    }

    public int getCodigoUnicoContratacion() {
        return codigoUnico;
    }
}
