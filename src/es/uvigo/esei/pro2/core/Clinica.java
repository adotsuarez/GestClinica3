/*  Definición de la clase Clinica
 *  En una clínica tendremos una serie de pacientes
*/

package es.uvigo.esei.pro2.core;

import java.util.*;

/** Proyecto GESTCLINICA
 * @author nrufino - Modificaciones por adotsuarez
 */
public class Clinica {

    // EXCEPCIONES ======
    public static class General extends Exception {
        public General(String msg) {
            super(msg);
        }
    }

    public static class Overflow extends General {
        public Overflow(String msg) {
            super(msg);
        }
    }

    public static class Inexistente extends General {
        public Inexistente(String msg) {
            super(msg);
        }
    }

    public static class YaExisteCita extends General {
        public YaExisteCita(String msg) {
            super(msg);
        }
    }

    // CODIGO ======
    private String nombreClinica;

    private ArrayList <Paciente> pacientes;
    private ArrayList <Medico> medicos;
    private ArrayList <CitaMedica> citasMedicas;

    /** Nueva Clinica con un num. max. de pacientes.
     * @param maxPacientes el num. max. de pacientes, como entero.
     * @param maxMedicos el num. max. de medicos, como entero.
     * @param maxCitasMedicas el num. max. de citas medicas, como entero.
     * @param nombreClinica nombre de la clinica
     */
    public Clinica(int maxPacientes, int maxMedicos, int maxCitasMedicas, String nombreClinica) {
        this.nombreClinica = nombreClinica;

        pacientes = new ArrayList<>(maxPacientes);
        medicos = new ArrayList<>(maxMedicos);
        citasMedicas = new ArrayList<>(maxCitasMedicas);
    }

    public String getNombreClinica() {
        return nombreClinica;
    }

    public void setNombreClinica(String nombreClinica) {
        this.nombreClinica = nombreClinica;
    }

    // PACIENTES ======
    /**
     * Devuelve el paciente situado en pos
     * @param pos el lugar del paciente en el vector de pacientes
     * @return el objeto Paciente correspondiente.
     */
    public Paciente getPaciente(int pos) throws Inexistente {
        if ( pos >= getNumPacientes() ) {
            throw new Inexistente ("getPaciente(): sobrepasa la pos: " + ( pos + 1 ) + " / " + getNumPacientes() );
        }

        return pacientes.get(pos);
    }

    /** Devuelve el num. de pacientes creados.
     * @return el num. de pacientes existentes, como entero.
     */
    public int getNumPacientes() {
        return pacientes.size();
    }

    /** Inserta un nuevo paciente
     * @param p el nuevo objeto Paciente
     */
    public void insertaPaciente(Paciente p) throws Overflow {
        pacientes.add(p);
    }

    /** Elimina un paciente
     * @param pos la posicion del paciente a eliminar
     */
    public void eliminaPaciente(int pos) throws Inexistente, YaExisteCita {
        if (pos >= getNumPacientes()) {
            throw new Inexistente("El paciente no exsite");
        }
        if (tieneCitasPaciente(getPaciente(pos))) {
            throw new YaExisteCita("El paciente tiene citas");
        }

        pacientes.remove(pos);
    }

    /** Comprueba si un paciente tiene citas
     * @param p el paciente a analizar
     */
    private boolean tieneCitasPaciente(Paciente p) {
        int i = 0;
        while (i < getNumCitasMedicas() && !Objects.equals(citasMedicas.get(i).getPaciente(), p)) {
            i++;
        }

        return i != this.getNumCitasMedicas();
    }

    /** Imprime todos los pacientes
     * @return un String con todos los pacientes
     */
    public String toStringPacientes() {
        StringBuilder toret;
        final int numPacientes = getNumPacientes();

        toret = new StringBuilder();
        if ( numPacientes > 0 ) {
            for (int i = 0; i < numPacientes; i++) {
                toret.append(i + 1)
                        .append(". ")
                        .append(pacientes.get(i).toString())
                        .append("\n");
            }
        } else {
            toret.append("No hay pacientes." );
        }

        return toret.toString();
    }



    // MEDICOS ======
    /**
     * Devuelve el paciente situado en pos
     * @param pos el lugar del medico en el vector de medicos
     * @return el objeto Medico correspondiente.
     */
    public Medico getMedico(int pos) throws Inexistente  {
        if ( pos >= getNumMedicos() ) {
            throw new Inexistente ("getMedico(): sobrepasa la pos: " + ( pos + 1 ) + " / " + getNumMedicos() );
        }

        return medicos.get(pos);
    }

    /**
     * Devuelve si un medico existe dado un num. colegiado
     * @param nc el numero de colegiado
     * @return true: existe
     *         false: no existe
     */
    public boolean existeColegiado(String nc){
        int i =0;

        while(i<this.getNumMedicos() && !medicos.get(i).getNumColegiado().equals(nc)){
            i++;
        }
        return(i!=this.getNumMedicos());
    }

    /** Devuelve el num. de medicos creados.
     * @return el num. de medicos existentes, como entero.
     */
    public int getNumMedicos() {
        return medicos.size();
    }

    /** Inserta un nuevo medico
     * @param m el nuevo objeto Medico
     */
    public void insertaMedico(Medico m) throws Overflow {
        medicos.add(m);
    }

    /** Elimina un medico
     * @param pos la posicion del medico a eliminar
     */
    public void eliminaMedico(int pos) throws Inexistente, YaExisteCita {
        if (pos >= getNumMedicos()) {
            throw new Inexistente("El medico no exsite");
        }
        if (tieneCitasMedico(medicos.get(pos))) {
            throw new YaExisteCita("El medico tiene citas");
        }
        medicos.remove(pos);
    }

    /** Comprueba si un medico tiene citas
     * @param m el medico a analizar
     */
    private boolean tieneCitasMedico(Medico m) {
        int i = 0;
        while (i < getNumCitasMedicas() && !Objects.equals(citasMedicas.get(i).getMedico(), m)) {
            i++;
        }

        return i != this.getNumCitasMedicas();
    }
    /** Imprime todos los medicos
     * @return un String con todos los medicos
     */
    public String toStringMedicos() {
        StringBuilder toret;
        final int numMedicos = getNumMedicos();

        toret = new StringBuilder();
        if ( numMedicos > 0 ) {
            for (int i = 0; i < numMedicos; i++) {
                toret.append(i + 1)
                        .append(". ")
                        .append(medicos.get(i).toString())
                        .append("\n");
            }
        } else {
            toret.append("No hay medicos." );
        }

        return toret.toString();
    }


    // CITAS MEDICAS ======
    /**
     * Devuelve la cita medica situada en pos
     * @param pos el lugar de la cita medica en el vector de citasMedicas
     * @return el objeto citaMedica correspondiente.
     */
    public CitaMedica getCitaMedica(int pos) throws Inexistente {
        if ( pos >= getNumCitasMedicas() ) {
            throw new Inexistente ("getCitaMedica(): sobrepasa la pos: " + ( pos + 1 ) + " / " + getNumCitasMedicas() );
        }

        return citasMedicas.get(pos);
    }

    /** Devuelve el num. de citas meicas creadas.
     * @return el num. de citas medicas existentes, como entero.
     */
    public int getNumCitasMedicas() {
        return citasMedicas.size();
    }

    /** Inserta una nueva cita medica
     * @param cm el nuevo objeto CitaMedica
     */
    public void insertaCitaMedica(CitaMedica cm) throws Inexistente {
        citasMedicas.add(cm);
    }

    /** Elimina una cita medica
     * @param pos la posicion de la cita medica a eliminar
     */
    public void eliminaCitaMedica(int pos) throws Inexistente {
        if ( pos >= getNumCitasMedicas() ) {
            throw new Inexistente ("eliminaCitaMedica(): sobrepasa la pos: " + ( pos + 1 ) + " / " + getNumCitasMedicas() );
        }
        citasMedicas.remove(pos);
    }

    /**
     * Devuelve una lista de pacientes que tienen cita con un medico dado el num. colegiado
     * @param numColegiado el numero de colegiado del medico a buscar
     * @return Lista de pacientes asociados
     */
    public ArrayList obtenerPacientesCitaMedico ( String numColegiado )throws Inexistente {
        ArrayList <String> nombresPacientes;

        if(!existeColegiado(numColegiado)){
            throw new Inexistente("Ningun médico con ese numero de colegiado (" + numColegiado + ")\n");
        }

        nombresPacientes=new ArrayList<>(getNumCitasMedicas());

        for(CitaMedica cm: citasMedicas){
            if (cm.getMedico().getNumColegiado().equals(numColegiado)) {
                if (!nombresPacientes.contains(cm.getPaciente().getNombre())) {
                    nombresPacientes.add(cm.getPaciente().getNombre());
                }
            }
        }
        return nombresPacientes;
    }

    /**
     * Devuelve un HashMap con
     * @return HashMap de pacientes asociados
     */
    public HashMap obtenerMedicosPacientes() throws Inexistente {
        HashMap<Paciente, List<String>> map = new HashMap<>();
        List<String> medicosEnCita;
        Paciente pac;
        String med;

        if(!citasMedicas.isEmpty()){
            for(CitaMedica cm: citasMedicas){
                pac = cm.getPaciente();
                med = cm.getMedico().getNumColegiado();
                if (!map.containsKey(pac)) {
                    medicosEnCita= new ArrayList<>(citasMedicas.size());
                    medicosEnCita.add(med);
                    map.put(pac, medicosEnCita);
                } else {
                    if (!map.get(pac).contains(med)) {
                        map.get(pac).add(med);
                    }
                }
            }
        }else {
            throw new Inexistente("No existen citas");
        }

        return map;
    }

    /** Devuelve los medicos por orden de num. colegiado
     * @return LinkedList con los medicos por orden de num. colegiado
     */
    public List orderedMedicos(){
        List<Medico> medOrdenados = new LinkedList<>();
        int pos;
        Medico med;

        if(!citasMedicas.isEmpty()){
            for(CitaMedica cm : citasMedicas){
                med = cm.getMedico();
                if (!medOrdenados.contains(med)) {
                    pos = 0;
                    while ((pos < medOrdenados.size())
                            && (medOrdenados.get(pos).getNumColegiado().compareTo(med.getNumColegiado()))< med.getNumColegiado().charAt(pos)){
                        pos++;
                    }
                    if (pos == medOrdenados.size()) {
                        medOrdenados.add(med);
                    } else if (!medOrdenados.get(pos).equals(med)) {
                        medOrdenados.add(pos,med);
                    }
                }
            }
        }
        return medOrdenados;
    }

    // EXAMEN GESTCLINICA3 - B3
    /** Devuelve el conjunto de medicos que tienen citas con pacientes privados
     * @return Set de medicos con citas con pac. privados
     */
    public Set obtenerMedicosPacientesPrivados() throws Inexistente {
        Set<String> medicos = new HashSet<>();

        if (!citasMedicas.isEmpty()) {
            for (CitaMedica cm : citasMedicas) {
                if(cm.getPaciente() instanceof Privado) {
                    if(!medicos.contains(cm.getMedico().getNumColegiado())) {
                        medicos.add(cm.getMedico().getNumColegiado());
                    }
                }
            }
        } else {
            throw new Inexistente("No existen citas");
        }

        return medicos;
    }

    /** Imprime todas las citasMedicas
     * @return un String con todos los pacientes
     */
    public String toStringCitasMedicas() {
        StringBuilder toret;
        final int numCitasMedicas = getNumCitasMedicas();

        toret = new StringBuilder();
        if ( numCitasMedicas > 0 ) {
            for (int i = 0; i < numCitasMedicas; i++) {
                toret.append(i + 1)
                        .append(". ")
                        .append(citasMedicas.get(i).toString())
                        .append("\n");
            }
        } else {
            toret.append("No hay citas medicas." );
        }

        return toret.toString();
    }
    
}
