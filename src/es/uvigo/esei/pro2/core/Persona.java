package es.uvigo.esei.pro2.core;

/**
 * @author adotsuarez
 * (C) Agustin Suarez Martinez, 2020 - adot.c1.biz
 */
public abstract class Persona {
    private String nombre;
    private String domicilio;

    public Persona(String nombre, String domicilio) {
        this.nombre = nombre;
        this.domicilio = domicilio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("- Nombre: ").append(nombre).append('\n');
        sb.append("- Domicilio: ").append(domicilio).append('\n');
        return sb.toString();
    }
}
