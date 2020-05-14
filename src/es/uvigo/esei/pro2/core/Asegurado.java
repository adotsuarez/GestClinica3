package es.uvigo.esei.pro2.core;

/**
 * @author adotsuarez
 * (C) Agustin Suarez Martinez, 2020 - adot.c1.biz
 */
public class Asegurado extends Paciente {
    private String poliza;
    private String companhia;

    public Asegurado(String nombre, String domicilio, String numHistorial, Fecha fechaNacimiento, String poliza, String companhia) {
        super(nombre,domicilio,numHistorial,fechaNacimiento);
        this.poliza = poliza;
        this.companhia = companhia;
    }

    public String getPoliza() {
        return poliza;
    }

    public void setPoliza(String poliza) {
        this.poliza = poliza;
    }

    public String getCompanhia() {
        return companhia;
    }

    public void setCompanhia(String companhia) {
        this.companhia = companhia;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("- Poliza: ").append(poliza).append('\n');
        sb.append("- Companhia: ").append(companhia).append('\n');
        return sb.toString();
    }
}
