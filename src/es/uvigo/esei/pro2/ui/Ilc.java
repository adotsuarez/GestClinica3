package es.uvigo.esei.pro2.ui;

import es.uvigo.esei.pro2.core.*;

import java.util.Scanner;

/** Proyecto GESTCLINICA
 * @author nrufino - Modificaciones por @adotsuarez
 */

/**
 * Interfaz de lin. de comando
 */
public class Ilc {

    // EXCEPCIONES ======
    public static class General extends Exception {
        public General(String msg) {
            super(msg);
        }
    }

    public static class FaltanElementos extends General {
        public FaltanElementos(String msg) {
            super(msg);
        }
    }


    /**
     * Realiza el reparto de la funcionalidad
     * ler = lee, evalua, repite
     */
    public void ler() {
        int op;

        // Lee el num. max. de los vectores
        int maxPacientes = leeNum( "Num. max. pacientes: " );
        int maxMedicos = leeNum( "Num. max. medicos: " );
        int maxCitasMedicas = leeNum( "Num. max. citas medicas: " );
        String nombreClinica = leeString("Nombre de la clinica: ");


        // Prepara
        Clinica coleccion = new Clinica(maxPacientes,maxMedicos,maxCitasMedicas, nombreClinica);

        // Bucle ppal
        do {
            op = menuPrincipal (coleccion);

            try {
                switch (op) {
                    case 1:         // PACIENTES
                        do {
                            op = menuPacientes( coleccion );

                            try {
                                switch( op ) {
                                    case 1:
                                        insertaPaciente( coleccion );
                                        break;
                                    case 2:
                                        modificaPaciente( coleccion );
                                        break;
                                    case 3:
                                        eliminaPaciente( coleccion );
                                        break;
                                    case 4:
                                        visualizaPacientes( coleccion );
                                        break;
                                    case 5:
                                        listarPorTipo(coleccion);
                                        break;
                                    case 6:
                                        // VOLVER
                                        break;
                                    default:
                                        System.out.println( "No es correcta esa opción ( "
                                                + op + " )" );
                                }
                            } catch (Exception e) {
                                System.err.println("\nERROR: " + e.getMessage());
                            }
                        } while (op != 6);
                        break;
                    case 2:         // MEDICOS
                        do {
                            op = menuMedicos( coleccion );

                            try {
                                switch( op ) {
                                    case 1:
                                        insertaMedico( coleccion );
                                        break;
                                    case 2:
                                        modificaMedico( coleccion );
                                        break;
                                    case 3:
                                        eliminaMedico( coleccion );
                                        break;
                                    case 4:
                                        visualizaMedicos( coleccion );
                                        break;
                                    case 5:
                                        // VOLVER
                                        break;
                                    default:
                                        System.out.println( "No es correcta esa opción ( "
                                                + op + " )" );
                                }
                            } catch (Exception e) {
                                System.err.println("\nERROR: " + e.getMessage());
                            }
                        } while (op != 5);
                        break;
                    case 3:         // CITAS MEDICAS
                        do {
                            op = menuCitasMedicas( coleccion );

                            try {
                                switch( op ) {
                                    case 1:
                                        insertaCitaMedica( coleccion );
                                        break;
                                    case 2:
                                        modificaCitaMedica( coleccion );
                                        break;
                                    case 3:
                                        eliminaCitaMedica( coleccion );
                                        break;
                                    case 4:
                                        visualizaCitasMedicas( coleccion );
                                        break;
                                    case 5:
                                        visualizaCitasMedicasExternos(coleccion);
                                        break;
                                    case 6:
                                        // VOLVER
                                        break;
                                    default:
                                        System.out.println( "No es correcta esa opción ( "
                                                + op + " )" );
                                }
                            } catch (Exception e) {
                                System.err.println("\nERROR: " + e.getMessage());
                            }
                        } while (op != 5);
                        break;
                    case 4:
                        // SALIR
                        break;
                    default:
                        System.out.println( "No es correcta esa opción ( "
                                + op + " )" );
                }

            } catch (Exception e) {
                System.err.println("\nERROR: " + e.getMessage());
            }

        } while( op != 4 );

    }

    /**
     * Presenta un menu con las opciones, y permite seleccionar una.
     * @return la opcion seleccionada, como entero
     */
    private int menuPrincipal(Clinica coleccion) {
        int toret;
        System.out.println("\n\n------------------\n\n");

        do {
            System.out.println("GESTIÓN CLÍNICA HOSPITALARIA\n");
            System.out.println(
                    "\n1. Gestión pacientes\n"
                            + "2. Gestión médicos\n"
                            + "3. Gestión citas médicas\n"
                            + "4. Salir\n" );
            toret = leeNum( "Selecciona: " );
        } while( toret < 1
                || toret > 4 );

        System.out.println();
        return toret;
    }

    /**
     * Presenta un menu con las opciones, y permite seleccionar una.
     * @return la opcion seleccionada, como entero
     */
    private int menuPacientes(Clinica coleccion) {
        int toret;
        System.out.println("\n\n------------------\n\n");

        do {
            System.out.println("GESTIÓN PACIENTES:\n"
                                + "Número de pacientes: "
                                + coleccion.getNumPacientes()
                                + " / " + coleccion.getMaxPacientes());
            System.out.println(
                              "\n1. Inserta un nuevo paciente\n"
                            + "2. Modifica un paciente\n"
                            + "3. Elimina un paciente\n"
                            + "4. Listar pacientes\n"
                            + "5. Listar pacientes por tipo\n"
                            + "6. Volver al menu principal\n" );
            toret = leeNum( "Selecciona: " );
        } while( toret < 1
              || toret > 6 );

        System.out.println();
        return toret;
    }

    /**
     * Presenta un menu con las opciones, y permite seleccionar una.
     * @return la opcion seleccionada, como entero
     */
    private int menuMedicos(Clinica coleccion) {
        int toret;
        System.out.println("\n\n------------------\n\n");

        do {
            System.out.println("GESTIÓN MÉDICOS:\n");
            System.out.println(
                    "\n1. Inserta un nuevo médicos\n"
                            + "2. Modifica un médicos\n"
                            + "3. Elimina un médicos\n"
                            + "4. Listar médicos\n"
                            + "5. Volver al menu principal\n" );
            toret = leeNum( "Selecciona: " );
        } while( toret < 1
                || toret > 5 );

        System.out.println();
        return toret;
    }

    /**
     * Presenta un menu con las opciones, y permite seleccionar una.
     * @return la opcion seleccionada, como entero
     */
    private int menuCitasMedicas(Clinica coleccion) {
        int toret;
        System.out.println("\n\n------------------\n\n");


        do {
            System.out.println("GESTIÓN CITAS MÉDICAS:\n");
            System.out.println(
                    "\n1. Inserta una nueva cita médica\n"
                            + "2. Modifica una cita médica\n"
                            + "3. Elimina una cita médica\n"
                            + "4. Listar citas médicas\n"
                            + "5. Citas médicos externos <EXAMEN>\n"
                            + "6. Volver al menu principal\n");
            toret = leeNum( "Selecciona: " );
        } while( toret < 1
                || toret > 6 );

        System.out.println();
        return toret;
    }

    /**
     * Lee un num. de teclado
     * @param msg El mensaje a visualizar.
     * @return El num., como entero
     */
    private int leeNum(String msg) {
        boolean repite;
        int toret = 0;
        Scanner teclado = new Scanner( System.in );

        do {
            repite = false;
            System.out.print( msg );

            try {
                toret = Integer.parseInt( teclado.nextLine() );
            } catch (NumberFormatException exc) {
                repite = true;
            }
        } while( repite );

        return toret;
    }

//    /**
//     * Lee un caracter del teclado
//     * @param men Mensaje a visualizar
//     * @return el caracter introducido por el usuario
//     */
//    private char leeCaracter(String men) {
//        Scanner teclado = new Scanner (System.in);
//
//        System.out.print(men);
//        return ( teclado.nextLine().trim().charAt(0) );
//    }

    /**
     * Lee un String de teclado
     * @param msg El mensaje a visualizar
     * @return El String
     */
    private String leeString(String msg)
    {
        boolean repite;
        String toret;
        Scanner teclado = new Scanner( System.in );

        do {
            repite = false;
            System.out.print( msg );
            toret = teclado.nextLine();
            if (toret.length() == 0) {
                repite = true;
            }
        } while( repite );

        return toret;
    }

    // PACIENTES ======
    /**
     *  Crea un nuevo paciente y lo inserta en la coleccion
     *  @param coleccion La coleccion en la que se inserta el paciente.
     */
    private void insertaPaciente(Clinica coleccion) throws Exception {
        Scanner entrada = new Scanner(System.in);
        boolean repetir;
        Paciente p = new Privado("", "", "", new Fecha(0, 0, 0), "");
        do {
            repetir = false;
            System.out.println("Crear paciente por privado [P] / asegurado [A]:");
            char temp = entrada.nextLine().trim().toUpperCase().charAt(0);
            switch (temp) {
                case 'P':
                    break;
                case 'A':
                    p = new Asegurado("", "", "", new Fecha(0, 0, 0), "", "");
                    break;
                default:
                    repetir = true;
            }
        } while (repetir);

        modificaPaciente( p );
        coleccion.insertaPaciente( p );
    }

    /**
     * Borra un paciente por su posicion en la colección.
     * @param coleccion La coleccion en la que se elimina el paciente
     */
    private void eliminaPaciente(Clinica coleccion) throws Clinica.Inexistente, Clinica.YaExisteCita {
        visualizaPacientes(coleccion);
        if (coleccion.getNumPacientes() != 0) {
            coleccion.eliminaPaciente(leePosPaciente(coleccion));
        }
    }

    /**
     * Modifica un paciente existente.
     * @param coleccion La coleccion de la cual modificar un paciente.
     */
    private void modificaPaciente(Clinica coleccion) throws Exception {
        visualizaPacientes(coleccion);
        if (coleccion.getNumPacientes() != 0) {
            this.modificaPaciente(coleccion.getPaciente(leePosPaciente(coleccion)));
        }
    }

    private void modificaPaciente(Paciente p) {
        String info;
        Scanner teclado = new Scanner( System.in );

        // Numero de historial
        System.out.print( "Numero de historial del paciente " );
        if ( p.getNumHistorial().length() > 0 ) {
            System.out.print( "[" + p.getNumHistorial() + "]" );
        }
        System.out.print( ": " );
        info = teclado.nextLine().trim();

        if ( info.length() > 0 ) {
            p.setNumHistorial(info );
        }

        // Nombre
        System.out.print( "Nombre del paciente " );
        if ( p.getNombre().length() > 0 ) {
            System.out.print( "[" + p.getNombre() + "]" );
        }
        System.out.print( ": " );
        info = teclado.nextLine().trim();

        if ( info.length() > 0 ) {
            p.setNombre( info );
        }
        
        // Domicilio
        System.out.print( "Domicilio del paciente " );
        if ( p.getDomicilio().length() > 0 ) {
            System.out.print( "[" + p.getDomicilio() + "]" );
        }
        System.out.print( ": " );
        info = teclado.nextLine().trim();

        if ( info.length() > 0 ) {
            p.setDomicilio( info );
        }
        
        // Fecha
        System.out.print( "Fecha de nacimiento del paciente " );
        if ( p.getFechaNacimiento().getAnho() > 0) {
            System.out.print( "["
                    + p.getFechaNacimiento().getDia()
                    + " / "
                    + p.getFechaNacimiento().getMes()
                    + " / "
                    + p.getFechaNacimiento().getAnho()
                    + "]" );
        }
        System.out.println( ": " );
        int dia = leeNum("Nuevo dia: ");
        int mes = leeNum("Nuevo mes: ");
        int ano = leeNum("Nuevo ano: ");


        if ( info.length() > 0 ) {
            Fecha fn = new Fecha (dia,mes,ano);
            p.setFechaNacimiento(fn);
        }

        if (p instanceof Asegurado) {
            Asegurado a = (Asegurado) p;

            System.out.print( "Numero de poliza " );

            if ( a.getPoliza().length() > 0 ) {
                System.out.print("[" + a.getPoliza() + "]");
            }

            System.out.print(": ");
            info = teclado.nextLine().trim();

            if ( info.length() > 0 ) {
                a.setPoliza( info );
            }

            System.out.print( "Companhia " );

            if ( a.getCompanhia().length() > 0 ) {
                System.out.print("[" + a.getCompanhia() + "]");
            }

            System.out.print(": ");
            info = teclado.nextLine().trim();

            if ( info.length() > 0 ) {
                a.setCompanhia( info );
            }

        } else {
            Privado pr = (Privado) p;

            System.out.print( "DNI " );

            if ( pr.getDni().length() > 0 ) {
                System.out.print("[" + pr.getDni() + "]");
            }

            System.out.print(": ");
            info = teclado.nextLine().trim();

            if ( info.length() > 0 ) {
                pr.setDni( info );
            }
        }
    }    
    
    /**
     * Lee del teclado la posición de un paciente en la colección
     * @param coleccion La colección de la que se obtiene el max.
     * @return la posición del paciente, como entero.
     */
    private int leePosPaciente(Clinica coleccion) {
        final int numPacientes = coleccion.getNumPacientes();
        int toret;

        do {
            toret = leeNum( "Introduzca posición del paciente (1..." + numPacientes + "): " );
        } while( toret < 1
              || toret > numPacientes );

        return toret - 1;
    }
    
    /**
     * Visualiza los pacientes almacenados en la coleccion por la salida std.
     * @param coleccion El objeto Clinica del que visualizar sus pacientes.
     */
    private void visualizaPacientes(Clinica coleccion) throws Clinica.Inexistente {
        final int numPacientes = coleccion.getNumPacientes();

        if ( numPacientes > 0 ) {
            for (int i = 0; i < numPacientes; i++) {
                System.out.print( ( i + 1 ) + ".\n" );
                System.out.println( coleccion.getPaciente( i ).toString() + '\n');
            }
        } else {
            System.out.println( "No hay pacientes." );
        }

    }
    
     /**
     *  Lista las pacientes de la coleccion por el tipo de seguro
     *  @param coleccion La Clinica de la que se listan los pacientes.
     */
    public void listarPorTipo( Clinica coleccion ) throws Clinica.Inexistente {
        char info;
       
        Scanner entrada = new Scanner( System.in );
        
        do {
            System.out.print("Indica el tipo de pacientes a listar: (Privado (P) / Asegurados (A)): ");
            info = entrada.nextLine().trim().toUpperCase().charAt(0);
        } while ((info != 'P') && (info != 'A') );

        switch (info) {
            case 'P':
                for (int i = 0; i < coleccion.getNumPacientes(); i++) {
                    if (coleccion.getPaciente(i) instanceof Privado) {
                        System.out.println((i + 1)
                                + ".\n"
                                + coleccion.getPaciente(i).toString()
                                + '\n');
                    }
                }
                break;

            case 'A':
                for (int i = 0; i < coleccion.getNumPacientes(); i++) {
                    if (coleccion.getPaciente(i) instanceof Asegurado) {
                        System.out.println((i + 1)
                                + ".\n"
                                + coleccion.getPaciente(i).toString()
                                + '\n');
                    }
                }
                break;
        }


    }

    // MEDICOS ======

    /**
     *  Crea un nuevo medico y lo inserta en la coleccion
     *  @param coleccion La coleccion en la que se inserta el medico.
     */
    private void insertaMedico(Clinica coleccion) throws Exception {
        Medico m = new Medico("","","");
        Scanner entrada = new Scanner(System.in);


        boolean repetir;

        do {
            repetir = false;
            System.out.println("Crear medico externo? Sí (S) / No (N):");
            char temp = entrada.nextLine().trim().toUpperCase().charAt(0);

            switch (temp) {
                case 'N':
                    break;
                case 'S':
                    m = new Externo("", "", "", false, new Fecha(0,0,0));
                    break;
                default:
                    repetir = true;
            }
        } while (repetir);

        modificaMedico( m );
        coleccion.insertaMedico( m );
    }

    /**
     * Borra un medico por su posicion en la colección.
     * @param coleccion La coleccion en la que se elimina el medico
     */
    private void eliminaMedico(Clinica coleccion) throws Clinica.Inexistente, Clinica.YaExisteCita {
        visualizaMedicos(coleccion);
        if (coleccion.getNumMedicos() != 0) {
            coleccion.eliminaMedico(leePosMedico(coleccion));
        }
    }

    /**
     * Modifica un medico existente.
     * @param coleccion La coleccion de la cual modificar un medico.
     */
    private void modificaMedico(Clinica coleccion) throws Exception {
        visualizaMedicos(coleccion);
        if (coleccion.getNumMedicos() != 0) {
            this.modificaMedico(coleccion.getMedico(leePosMedico(coleccion)));
        }
    }

    private void modificaMedico(Medico m) {
        String info;
        Scanner teclado = new Scanner( System.in );

        // Nombre
        System.out.print( "Nombre del medico " );
        if ( m.getNombre().length() > 0 ) {
            System.out.print( "[" + m.getNombre() + "]" );
        }
        System.out.print( ": " );
        info = teclado.nextLine().trim();

        if ( info.length() > 0 ) {
            m.setNombre( info );
        }

        // Numero colegiado
        System.out.print( "Numero de colegiado " );
        if ( m.getNumColegiado().length() > 0 ) {
            System.out.print( "[" + m.getNumColegiado() + "]" );
        }
        System.out.print( ": " );
        info = teclado.nextLine().trim();

        if ( info.length() > 0 ) {
            m.setNumColegiado( info );
        }

        // Domicilio
        System.out.print( "Domicilio del medico " );
        if ( m.getDomicilio().length() > 0 ) {
            System.out.print( "[" + m.getDomicilio() + "]" );
        }
        System.out.print( ": " );
        info = teclado.nextLine().trim();

        if ( info.length() > 0 ) {
            m.setDomicilio(info);
        }

        // Especialidad
        if (m instanceof Externo) {
            System.out.print( "Especialidad " );
            if ( m.getDomicilio().length() > 0 ) {
                System.out.print( "[" + ((Externo) m).isEspecialidad() + "]" );
            }
            System.out.print( " (S) para true / (N) para false: " );

            Scanner entrada = new Scanner(System.in);
            char temp = entrada.nextLine().trim().toUpperCase().charAt(0);
            boolean repetir;

            do {
                repetir = false;
                switch (temp) {
                    case 'S':
                        ((Externo) m).setEspecialidad(true);
                        break;
                    case 'N':
                        ((Externo) m).setEspecialidad(false);
                        break;
                    default:
                        repetir = true;
                }
            } while (repetir);

            System.out.print( "Incorporacion " );
            if ( ((Externo) m).getIncorporacion().getAnho() > 0) {
                System.out.print( "["
                        + ((Externo) m).getIncorporacion().getDia()
                        + " / "
                        + ((Externo) m).getIncorporacion().getMes()
                        + " / "
                        + ((Externo) m).getIncorporacion().getAnho()
                        + "]" );
            }
            System.out.println( ": " );
            int dia = leeNum("Nuevo dia: ");
            int mes = leeNum("Nuevo mes: ");
            int ano = leeNum("Nuevo ano: ");


            if ( info.length() > 0 ) {
                Fecha fi = new Fecha (dia,mes,ano);
                ((Externo) m).setIncorporacion(fi);
            }
        }

    }

    /**
     * Lee del teclado la posición de un medico en la colección
     * @param coleccion La colección de la que se obtiene el max.
     * @return la posición del medico, como entero.
     */
    private int leePosMedico(Clinica coleccion) {
        final int numMedicos = coleccion.getNumMedicos();
        int toret;

        do {
            toret = leeNum( "Introduzca posición del medico (1..." + numMedicos + "): " );
        } while( toret < 1
                || toret > numMedicos );

        return toret - 1;
    }

    /**
     * Visualiza los medicos almacenados en la coleccion por la salida std.
     * @param coleccion El objeto Clinica del que visualizar sus pacientes.
     */
    private void visualizaMedicos(Clinica coleccion) throws Clinica.Inexistente {
        final int numMedicos = coleccion.getNumMedicos();

        if ( numMedicos > 0 ) {
            for (int i = 0; i < numMedicos; i++) {
                System.out.print( ( i + 1 ) + ".\n" );
                System.out.println( coleccion.getMedico( i ).toString() + '\n');
            }
        } else {
            System.out.println( "No hay médicos." );
        }

    }

    // CITAS MEDICAS ======

    /**
     *  Crea un nuevo medico y lo inserta en la coleccion
     *  @param coleccion La coleccion en la que se inserta el medico.
     */
    private void insertaCitaMedica(Clinica coleccion) throws Exception {
        CitaMedica cm = new CitaMedica(new Fecha(0,0,0),
                new Hora(25,0),
                new Medico("","",""),
                new Asegurado("","","",new Fecha(0,0,0),"",""));

        modificaCitaMedica( cm, coleccion );
        coleccion.insertaCitaMedica( cm );
    }

    /**
     * Borra un medico por su posicion en la colección.
     * @param coleccion La coleccion en la que se elimina el medico
     */
    private void eliminaCitaMedica(Clinica coleccion) throws Clinica.Inexistente {
        visualizaCitasMedicas(coleccion);
        if (coleccion.getNumCitasMedicas() != 0) {
            coleccion.eliminaCitaMedica(leePosCitaMedica(coleccion));
        }
    }

    /**
     * Modifica un medico existente.
     * @param coleccion La coleccion de la cual modificar un medico.
     */
    private void modificaCitaMedica(Clinica coleccion) throws Exception {
        visualizaCitasMedicas(coleccion);
        if (coleccion.getNumCitasMedicas() != 0) {
            this.modificaCitaMedica(coleccion.getCitaMedica(leePosCitaMedica(coleccion)), coleccion);
        }
    }

    private void modificaCitaMedica(CitaMedica cm, Clinica coleccion) throws Clinica.Inexistente, FaltanElementos {

        // Fecha
        System.out.print( "Fecha de la cita " );
        if ( cm.getFecha().getAnho() > 0) {
            System.out.print( "["
                    + cm.getFecha().getDia()
                    + " / "
                    + cm.getFecha().getMes()
                    + " / "
                    + cm.getFecha().getAnho()
                    + "]" );
        }
        System.out.println( ": " );
        int dia = leeNum("Nuevo dia: ");
        int mes = leeNum("Nuevo mes: ");
        int ano = leeNum("Nuevo ano: ");
        cm.setFecha(new Fecha(dia,mes,ano));

        // Hora
        System.out.print( "Hora de la cita " );
        if ( cm.getHora().getHora() != 25) {
            System.out.print( "["
                    + cm.getFecha().getDia()
                    + " / "
                    + cm.getFecha().getMes()
                    + " / "
                    + cm.getFecha().getAnho()
                    + "]" );
        }
        System.out.println( ": " );
        int hora = leeNum("Nueva hora: ");
        int minutos = leeNum("Nuevo minutos: ");
        cm.setHora(new Hora(hora,minutos));

        if (coleccion.getNumPacientes() == 0 || coleccion.getNumMedicos() == 0) {
            throw new FaltanElementos("No hay medicos y/o doctores");
        }

        // Paciente
        visualizaPacientes(coleccion);
        cm.setPaciente( coleccion.getPaciente( leePosPaciente(coleccion) ) );

        // Medico
        visualizaMedicos(coleccion);
        cm.setMedico( coleccion.getMedico( leePosMedico(coleccion) ) );

    }

    /**
     * Lee del teclado la posición de una cita medica en la colección
     * @param coleccion La colección de la que se obtiene el max.
     * @return la posición de la cita medica, como entero.
     */
    private int leePosCitaMedica(Clinica coleccion) {
        final int numCitasMedicas = coleccion.getNumCitasMedicas();
        int toret;

        do {
            toret = leeNum( "Introduzca posición de la cita medica (1..." + numCitasMedicas + "): " );
        } while( toret < 1
                || toret > numCitasMedicas );

        return toret - 1;
    }

    /**
     * Visualiza las citas medicas almacenadas en la coleccion por la salida std.
     * @param coleccion El objeto Clinica del que visualizar sus pacientes.
     */
    private void visualizaCitasMedicas(Clinica coleccion) throws Clinica.Inexistente {
        final int numCitasMedicas = coleccion.getNumCitasMedicas();

        if ( numCitasMedicas > 0 ) {
            for (int i = 0; i < numCitasMedicas; i++) {
                System.out.print( ( i + 1 ) + ".\n" );
                System.out.println( coleccion.getCitaMedica( i ).toString() + '\n');
            }
        } else {
            System.out.println( "No hay citas médicas." );
        }

    }

    /**
     * Visualiza las citas medicas almacenadas en la coleccion por la salida std.
     * @param coleccion El objeto Clinica del que visualizar sus pacientes.
     */
    private void visualizaCitasMedicasExternos(Clinica coleccion) throws Clinica.Inexistente {
        final int numCitasMedicas = coleccion.getNumCitasMedicas();

        boolean noHayCMExternos = true;
        if ( numCitasMedicas > 0 ) {
            for (int i = 0; i < numCitasMedicas; i++) {
                if (coleccion.getCitaMedica(i).getMedico() instanceof Externo
                        && !((Externo) coleccion.getCitaMedica(i).getMedico()).isEspecialidad()) {
                    System.out.println((i + 1)
                            + ".\n"
                            + coleccion.getCitaMedica( i ).toString()
                            + '\n');
                    noHayCMExternos = false;
                }
            }

            if (noHayCMExternos) {
                System.out.println( "No hay citas médicas de médicos externos sin especialidad." );
            }

        } else {
            System.out.println( "No hay ninguna cita médica." );
        }

    }

}
