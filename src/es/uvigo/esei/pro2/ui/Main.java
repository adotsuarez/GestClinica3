/*
 * Proyecto de Programación II.
 * Gestión de una clínica. 
 */
package es.uvigo.esei.pro2.ui;
/** Proyecto GESTCLINICA
 * @author nrufino - Modificaciones por adotsuarez
 */

public class Main {
    public static void main(String[] args) {
        System.out.println("Agustín Suárez Martínez");

        /* Ejercicio prueba GestClinica3 - B3:

        A partir del proyecto GestClinica3 añade al menú “GESTIÓN CITAS MÉDICAS” la opción “Médicos con cita
        con pacientes privados”. Esta opción debe mostrar el número de colegiado de aquellos médicos que tengan
        alguna cita médica con pacientes privados.

        Es obligatorio usar un HashSet.

        ------------------------------------------------------

        !! Puedes consultar el códgio escrito de este ejercicio en:
         --> La línea #357 de Clinica.java
         --> La línea #147, #271 y #981 de ILC.java
         
         */

        new Ilc().ler();
    }
}