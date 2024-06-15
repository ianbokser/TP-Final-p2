package Empresa;

public abstract class Servicio {
    protected int codigoUnico;
    protected double costoBase;
    protected int cantPersonas;
    protected String fechaInicio;
    
    
    public int getCodigoUnico() {
        return codigoUnico;
    }

    public double getCostoBase() {
        return costoBase;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public Servicio(int codigoUnico, double costoBase, int cantPersonas) {
        this.codigoUnico = codigoUnico;
        this.costoBase = costoBase;
        this.cantPersonas = cantPersonas;
    }

	protected abstract double calcularCosto();
    
}
