package Empresa;

class Alquiler extends Servicio {
    private String pais;
    private String ciudad;
    private String fechaInicio;
    private String fechaDevolucion;
    private double garantia;

    public Alquiler(int codigoUnico, double costoBase, int cantPersonas, String pais, String ciudad, String fechaInicio, String fechaDevolucion, double garantia) {
        super(codigoUnico, costoBase, cantPersonas);
        this.pais = pais;
        this.ciudad = ciudad;
        this.fechaInicio = fechaInicio;
        this.fechaDevolucion = fechaDevolucion;
        this.garantia = garantia;
    }

    @Override
    public double calcularCosto() {
        return costoBase * cantPersonas + garantia;
    }

    @Override
    public String toString() {
        return "Alquiler [codigoUnico=" + codigoUnico + ", pais=" + pais + ", ciudad=" + ciudad +
               ", fechaInicio=" + fechaInicio + ", fechaDevolucion=" + fechaDevolucion + ", garantia=" + garantia + "]";
    }
}