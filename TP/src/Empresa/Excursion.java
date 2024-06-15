package Empresa;

class Excursion extends Servicio {
    private String lugarSalida;
    private boolean esDiaCompleto;

    public Excursion(int codigoUnico, double costoBase, int cantPersonas, String lugarSalida, boolean esDiaCompleto) {
        super(codigoUnico, costoBase, cantPersonas);
        this.lugarSalida = lugarSalida;
        this.esDiaCompleto = esDiaCompleto;
    }

    @Override
    public double calcularCosto() {
        return esDiaCompleto ? costoBase * cantPersonas * 1.2 : costoBase * cantPersonas;
    }

    @Override
    public String toString() {
        return "Excursion [codigoUnico=" + codigoUnico + ", lugarSalida=" + lugarSalida + ", esDiaCompleto=" + esDiaCompleto + "]";
    }
}