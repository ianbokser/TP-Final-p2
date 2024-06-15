package Empresa;

class Alojamiento extends Servicio {
    private String paisDestino;
    private String ciudadDestino;
    private String fechaSalida;
    private String fechaLlegada;
    private String hotel;
    private double costoTraslado;

    public Alojamiento(int codigoUnico, double costoBase, int cantPersonas, String paisDestino, String ciudadDestino, String fechaSalida, String fechaLlegada, String hotel, double costoTraslado) {
        super(codigoUnico, costoBase, cantPersonas);
        this.paisDestino = paisDestino;
        this.ciudadDestino = ciudadDestino;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.hotel = hotel;
        this.costoTraslado = costoTraslado;
    }

    @Override
    public double calcularCosto() {
        return (costoBase * cantPersonas) + costoTraslado;
    }

    @Override
    public String toString() {
        return "Alojamiento [codigoUnico=" + codigoUnico + ", paisDestino=" + paisDestino + ", ciudadDestino=" + ciudadDestino +
               ", fechaSalida=" + fechaSalida + ", fechaLlegada=" + fechaLlegada + ", hotel=" + hotel + ", costoTraslado=" + costoTraslado + "]";
    }
}