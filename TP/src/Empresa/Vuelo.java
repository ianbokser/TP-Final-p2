package Empresa;

class Vuelo extends Servicio {
    private String paisDestino;
    private String ciudadDestino;
    private String fechaSalida;
    private String fechaLlegada;
    private double tasaImpuestos;

    public Vuelo(int codigoUnico, double costoBase, int cantPersonas, String paisDestino, String ciudadDestino, String fechaSalida, String fechaLlegada, double tasaImpuestos) {
        super(codigoUnico, costoBase, cantPersonas);
        this.paisDestino = paisDestino;
        this.ciudadDestino = ciudadDestino;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.tasaImpuestos = tasaImpuestos;
    }

	@Override
    public double calcularCosto() {
        return costoBase + (costoBase * tasaImpuestos);
    }

    @Override
    public String toString() {
        return "Vuelo [codigoUnico=" + codigoUnico + ", paisDestino=" + paisDestino + ", ciudadDestino=" + ciudadDestino +
               ", fechaSalida=" + fechaSalida + ", fechaLlegada=" + fechaLlegada + ", tasaImpuestos=" + tasaImpuestos + "]";
    }
}