package es.uvigo.esei.pro2.core;

/**
 * @author adotsuarez
 * (C) Agustin Suarez Martinez, 2020 - adot.c1.biz
 */
public class Privado extends Paciente {
    private String dni;

    public Privado(String nombre, String domicilio, String numHistorial, Fecha fechaNacimiento, String dni) {
        super(nombre,domicilio,numHistorial,fechaNacimiento);
        this.dni = dni;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("- DNI: ").append(dni).append('\n');
        return sb.toString();
    }
}
