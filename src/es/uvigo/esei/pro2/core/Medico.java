package es.uvigo.esei.pro2.core;

/**
 * @author adotsuarez
 * (C) Agustin Suarez Martinez, 2020 - adot.c1.biz
 */
public class Medico extends Persona{
    private String numColegiado;

    public Medico(String nombre, String domicilio, String numColegiado) {
        super(nombre, domicilio);
        this.numColegiado = numColegiado;
    }

    public String getNumColegiado() {
        return numColegiado;
    }

    public void setNumColegiado(String numColegiado) {
        this.numColegiado = numColegiado;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Medico\n");
        sb.append("- Número de Colegiado ").append(numColegiado).append('\n');
        sb.append(super.toString());
        return sb.toString();
    }
}
