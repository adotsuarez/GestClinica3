/*  Definición de la clase Clinica
 *  En una clínica tendremos una serie de pacientes
*/

package es.uvigo.esei.pro2.core;

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

    private Paciente[] pacientes;
    private Medico[] medicos;
    private CitaMedica[] citasMedicas;

    private int numPacientes;
    private int numMedicos;
    private int numCitasMedicas;

    /** Nueva Clinica con un num. max. de pacientes.
     * @param maxPacientes el num. max. de pacientes, como entero.
     * @param maxMedicos el num. max. de medicos, como entero.
     * @param maxCitasMedicas el num. max. de citas medicas, como entero.
     * @param nombreClinica nombre de la clinica
     */
    public Clinica(int maxPacientes, int maxMedicos, int maxCitasMedicas, String nombreClinica) {
        this.nombreClinica = nombreClinica;

        numPacientes = 0;
        numMedicos = 0;
        numCitasMedicas = 0;

        pacientes = new Paciente[maxPacientes];
        medicos = new Medico[maxMedicos];
        citasMedicas = new CitaMedica[maxCitasMedicas];
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
            throw new Inexistente ("getPaciente(): sobrepasa la pos: " + ( pos + 1 ) + " / " + getMaxPacientes() );
        }

        return pacientes[ pos ];
    }

    /** Devuelve el num. de pacientes creados.
     * @return el num. de pacientes existentes, como entero.
     */
    public int getNumPacientes() {
        return numPacientes;
    }

    /** Devuelve el max. de numPacientes
     * @return el num. de pacientes max, como entero
     */
    public int getMaxPacientes() {
        return pacientes.length;
    }

    /** Inserta un nuevo paciente
     * @param p el nuevo objeto Paciente
     */
    public void insertaPaciente(Paciente p) throws Overflow {
        final int maxPacientes = getMaxPacientes();

        if ( getNumPacientes() >= maxPacientes ) {
            throw new Overflow("Demasiados pacientes");
        }

        pacientes[ numPacientes ] = p;
        ++numPacientes;
    }

    /** Elimina un paciente
     * @param pos la posicion del paciente a eliminar
     */
    public void eliminaPaciente(int pos) throws Inexistente, YaExisteCita {
        if (pos >= getNumPacientes()) {
            throw new Inexistente("El paciente no exsite");
        }
        if (tieneCitasPaciente(pacientes[pos])) {
            throw new YaExisteCita("El paciente tiene citas");
        }
        pacientes [ pos ] = pacientes [ --numPacientes ];
    }

    /** Comprueba si un paciente tiene citas
     * @param p el paciente a analizar
     */
    private boolean tieneCitasPaciente(Paciente p) {
        int i = 0;
        while (i < getNumCitasMedicas() && !citasMedicas[i].getPaciente().equals(p)) {
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
                        .append(pacientes[i].toString())
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
            throw new Inexistente ("getMedico(): sobrepasa la pos: " + ( pos + 1 ) + " / " + getMaxMedicos() );
        }

        return medicos[ pos ];
    }

    /** Devuelve el num. de medicos creados.
     * @return el num. de medicos existentes, como entero.
     */
    public int getNumMedicos() {
        return numMedicos;
    }

    /** Devuelve el max. de numMedicos
     * @return el num. de medicos max, como entero
     */
    public int getMaxMedicos() {
        return medicos.length;
    }

    /** Inserta un nuevo medico
     * @param m el nuevo objeto Medico
     */
    public void insertaMedico(Medico m) throws Overflow {
        final int maxMedicos = getMaxMedicos();

        if ( getNumMedicos() >= maxMedicos ) {
            throw new Overflow ("insertaMedico(): sobrepasa max.: " + getMaxMedicos() );
        }

        medicos[ numMedicos ] = m;
        ++numMedicos;
    }

    /** Elimina un medico
     * @param pos la posicion del medico a eliminar
     */
    public void eliminaMedico(int pos) throws Inexistente, YaExisteCita {
        if (pos >= getNumMedicos()) {
            throw new Inexistente("El medico no exsite");
        }
        if (tieneCitasMedico(medicos[pos])) {
            throw new YaExisteCita("El medico tiene citas");
        }
        medicos [ pos ] = medicos [ --numMedicos ];
    }

    /** Comprueba si un medico tiene citas
     * @param m el medico a analizar
     */
    private boolean tieneCitasMedico(Medico m) {
        int i = 0;
        while (i < getNumCitasMedicas() && !citasMedicas[i].getMedico().equals(m)) {
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
                        .append(medicos[i].toString())
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
            throw new Inexistente ("getCitaMedica(): sobrepasa la pos: " + ( pos + 1 ) + " / " + getMaxCitasMedicas() );
        }

        return citasMedicas[ pos ];
    }

    /** Devuelve el num. de citas meicas creadas.
     * @return el num. de citas medicas existentes, como entero.
     */
    public int getNumCitasMedicas() {
        return numCitasMedicas;
    }

    /** Devuelve el max. de numCitasMedicas
     * @return el num. de citas medicas max, como entero
     */
    public int getMaxCitasMedicas() {
        return citasMedicas.length;
    }

    /** Inserta una nueva cita medica
     * @param cm el nuevo objeto CitaMedica
     */
    public void insertaCitaMedica(CitaMedica cm) throws Inexistente {
        final int maxCitasMedicas = getMaxCitasMedicas();

        if ( getNumCitasMedicas() >= maxCitasMedicas ) {
            throw new Inexistente ("insertaCitasMedicas(): sobrepasa max.: " + getMaxCitasMedicas() );
        }

        citasMedicas[ numCitasMedicas ] = cm;
        ++numCitasMedicas;
    }

    /** Elimina una cita medica
     * @param pos la posicion de la cita medica a eliminar
     */
    public void eliminaCitaMedica(int pos) throws Inexistente {
        if ( pos >= getNumCitasMedicas() ) {
            throw new Inexistente ("eliminaCitaMedica(): sobrepasa la pos: " + ( pos + 1 ) + " / " + getMaxCitasMedicas() );
        }
        citasMedicas [ pos ] = citasMedicas [ --numCitasMedicas ];
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
                        .append(citasMedicas[i].toString())
                        .append("\n");
            }
        } else {
            toret.append("No hay citas medicas." );
        }

        return toret.toString();
    }
    
}
