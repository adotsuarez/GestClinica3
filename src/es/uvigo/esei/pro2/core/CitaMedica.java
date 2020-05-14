package es.uvigo.esei.pro2.core;

/**
 * @author adotsuarez
 * (C) Agustin Suarez Martinez, 2020 - adot.c1.biz
 */
public class CitaMedica {
    private Fecha fecha;
    private Hora hora;
    private Medico medico;
    private Paciente paciente;

    public CitaMedica(Fecha fecha, Hora hora, Medico medico, Paciente paciente) {
        this.fecha = fecha;
        this.hora = hora;
        this.medico = medico;
        this.paciente = paciente;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    public Hora getHora() {
        return hora;
    }

    public void setHora(Hora hora) {
        this.hora = hora;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Cita Médica");
        sb.append("- Fecha: ").append(fecha);
        sb.append("- Hora=").append(hora);
        sb.append("- ").append(medico);
        sb.append("- ").append(paciente);
        sb.append('}');
        return sb.toString();
    }
}
