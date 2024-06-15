package Empresa;

import java.util.*;

public class ViajeFeliz implements IViajeFeliz {
    private String cuit;
    private Map<String, Cliente> clientes;
    private Map<Integer, Servicio> servicios;
    private Map<Integer, Paquete> paquetes;
    private Map<String, List<Contratacion>> historialContrataciones;
    private Map<Integer, Contratacion> contrataciones;
    private int nextCodigoServicio;
    private int nextCodigoPaquete;
    private int nextCodigoCliente;
    private int nextCodigoContratacion;

    public ViajeFeliz(String cuit) {
        this.cuit = cuit;
        this.clientes = new HashMap<>();
        this.servicios = new HashMap<>();
        this.paquetes = new HashMap<>();
        this.historialContrataciones = new HashMap<>();
        this.contrataciones = new HashMap<>();
        this.nextCodigoServicio = 1;
        this.nextCodigoPaquete = 1;
        this.nextCodigoCliente = 1;
        this.nextCodigoContratacion = 1;
    }

    @Override
    public void registrarCliente(String dni, String nombre, String direccion) {
        if (clientes.containsKey(dni)) {
            throw new RuntimeException("Cliente con el mismo DNI ya existe");
        }
        Cliente cliente = new Cliente(nextCodigoCliente++, nombre, direccion, dni);
        clientes.put(dni, cliente);
        historialContrataciones.put(dni, new ArrayList<>());
    }

    @Override
    public int crearServicio(double costoBase, String fechaInicio, int cantidad, String pais, String ciudad, String fechaLlegada, double tasa) {
        Servicio servicio = new Vuelo(nextCodigoServicio, costoBase, cantidad, pais, ciudad, fechaInicio, fechaLlegada, tasa);
        servicios.put(nextCodigoServicio, servicio);
        return nextCodigoServicio++;
    }

    @Override
    public int crearServicio(double costoBase, String fechaInicio, int cantidad, String pais, String ciudad, String fechaSalida, String hotel, double costoTraslado) {
        Servicio servicio = new Alojamiento(nextCodigoServicio, costoBase, cantidad, pais, ciudad, fechaInicio, fechaSalida, hotel, costoTraslado);
        servicios.put(nextCodigoServicio, servicio);
        return nextCodigoServicio++;
    }

    @Override
    public int crearServicio(double costoBase, String fechaInicio, int cantidad, String pais, String ciudad, double garantia, String fechaDevolucion) {
        Servicio servicio = new Alquiler(nextCodigoServicio, costoBase, cantidad, pais, ciudad, fechaInicio, fechaDevolucion, garantia);
        servicios.put(nextCodigoServicio, servicio);
        return nextCodigoServicio++;
    }

    @Override
    public int crearServicio(double costoBase, String fechaInicio, int cantidad, String lugarSalida, boolean esDiaCompleto) {
        Servicio servicio = new Excursion(nextCodigoServicio, costoBase, cantidad, lugarSalida, esDiaCompleto);
        servicios.put(nextCodigoServicio, servicio);
        return nextCodigoServicio++;
    }

    @Override
    public int[] crearPaquetesPredefinidos(int cantPaquetes, int[] codigosDeServicios) {
        int[] codigosPaquetes = new int[cantPaquetes];
        for (int i = 0; i < cantPaquetes; i++) {
            Paquete paquete = new Paquete(nextCodigoPaquete, codigosDeServicios.length);
            for (int codigoServicio : codigosDeServicios) {
                paquete.agregarServicio(servicios.get(codigoServicio));
            }
            paquetes.put(nextCodigoPaquete, paquete);
            codigosPaquetes[i] = nextCodigoPaquete++;
        }
        return codigosPaquetes;
    }

    @Override
    public int iniciarContratacion(String dni, int codServicio) {
        if (!clientes.containsKey(dni)) {
            throw new RuntimeException("Cliente no registrado");
        }
        if (!servicios.containsKey(codServicio)) {
            throw new RuntimeException("Servicio no encontrado");
        }
        Servicio servicioInicial = servicios.get(codServicio);
        Contratacion contratacion = new Contratacion(nextCodigoContratacion, dni, servicioInicial);
        contrataciones.put(nextCodigoContratacion, contratacion);
        historialContrataciones.get(dni).add(contratacion);
        return nextCodigoContratacion++;
    }

    @Override
    public void agregarServicioAContratacion(String dni, int codServicio) {
        if (!clientes.containsKey(dni)) {
            throw new RuntimeException("Cliente no registrado");
        }
        Contratacion contratacion = historialContrataciones.get(dni).stream()
                .filter(c -> !c.isFinalizada())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No hay una contratación vigente para el cliente"));

        Servicio servicio = servicios.get(codServicio);
        if (servicio == null) {
            throw new RuntimeException("Servicio no encontrado");
        }
        contratacion.agregarServicio(servicio);
        servicios.remove(codServicio);
    }

    @Override
    public void quitarServicioDeContratacion(String dni, int codServicio) {
        if (!clientes.containsKey(dni)) {
            throw new RuntimeException("Cliente no registrado");
        }
        Contratacion contratacion = historialContrataciones.get(dni).stream()
                .filter(c -> !c.isFinalizada())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No hay una contratación vigente para el cliente"));

        Servicio servicio = contratacion.quitarServicio(codServicio);
        if (servicio != null) {
            servicios.put(codServicio, servicio);
        } else {
            throw new RuntimeException("Servicio no encontrado en la contratación");
        }
    }

    @Override
    public double calcularCostoDePaquetePersonalizado(String dni, int codPaquetePersonalizado) {
        if (!clientes.containsKey(dni)) {
            throw new RuntimeException("Cliente no registrado");
        }
        Contratacion contratacion = historialContrataciones.get(dni).stream()
                .filter(c -> c.getCodigoUnicoContratacion() == codPaquetePersonalizado)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Contratación no encontrada"));
        return contratacion.calcularCosto();
    }

    @Override
    public double pagarContratacion(String dni, String fechaPago) {
        if (!clientes.containsKey(dni)) {
            throw new RuntimeException("Cliente no registrado");
        }
        Contratacion contratacion = historialContrataciones.get(dni).stream()
                .filter(c -> !c.isFinalizada())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No hay una contratación vigente para el cliente"));
        return contratacion.pagar(fechaPago);
    }

    @Override
    public List<Integer> historialDeContrataciones(String dni) {
        if (!clientes.containsKey(dni)) {
            throw new RuntimeException("Cliente no registrado");
        }
        List<Integer> codigos = new ArrayList<>();
        for (Contratacion c : historialContrataciones.get(dni)) {
            codigos.add(c.getCodigoUnicoContratacion());
        }
        return codigos;
    }

    @Override
    public String contratacionesSinIniciar(String fecha) {
        StringBuilder resultado = new StringBuilder();
        for (Contratacion contratacion : contrataciones.values()) {
            if (!contratacion.isFinalizada() && contratacion.getFechaInicio().equals(fecha)) {
                Cliente cliente = clientes.get(contratacion.getDniCliente());
                resultado.append(cliente.getNombre())
                        .append(" | ")
                        .append(contratacion.getFechaInicio())
                        .append(" | ")
                        .append(contratacion.obtenerTiposDeServicios())
                        .append("\n");
            }
        }
        return resultado.toString();
    }

    @Override
    public List<String> contratacionesQueInicianEnFecha(String fecha) {
        List<String> resultado = new ArrayList<>();
        for (Contratacion contratacion : contrataciones.values()) {
            if (contratacion.getFechaInicio().equals(fecha)) {
                Cliente cliente = clientes.get(contratacion.getDniCliente());
                resultado.add(contratacion.getCodigoUnicoContratacion() + " - (" + cliente.getDni() + " " + cliente.getNombre() + ")");
            }
        }
        return resultado;
    }

    @Override
    public Set<Integer> obtenerCodigosCatalogo() {
        Set<Integer> codigos = new HashSet<>(servicios.keySet());
        codigos.addAll(paquetes.keySet());
        return codigos;
    }

    public void bajaCliente(String dni) {
        clientes.remove(dni);
    }

    public void bajaServicio(int codigoUnico) {
        servicios.remove(codigoUnico);
    }
}
