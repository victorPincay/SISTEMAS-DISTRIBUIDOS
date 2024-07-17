/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uees.sd.lectura;

/**
 *
 * @author ninoska
 */
public class CensusForm {
    public String id;

    public String nombre;

    public String apellido;

    public int edad;

    public String genero;

    public String direccion;

    public String ciudad;

    public String estado;

    public String codigoPostal;

    public String pais;

    public String telefono;

    public String email;

    public String ocupacion;

    public String estadoCivil;

    public int numeroDeHijos;

    public String nivelEducativo;

    public double ingresoAnual;

    public boolean tieneMascotas;

    public int numeroDeMascotas;

    public int tamanoHogar;
    
    public boolean isValid() {
        return id != null && !id.isEmpty() &&
               nombre != null && !nombre.isEmpty() &&
               apellido != null && !apellido.isEmpty() &&
               edad > 0 &&
               genero != null && !genero.isEmpty() &&
               direccion != null && !direccion.isEmpty() &&
               ciudad != null && !ciudad.isEmpty() &&
               estado != null && !estado.isEmpty() &&
               codigoPostal != null && !codigoPostal.isEmpty() &&
               pais != null && !pais.isEmpty() &&
               telefono != null && !telefono.isEmpty() &&
               email != null && !email.isEmpty() &&
               ocupacion != null && !ocupacion.isEmpty() &&
               estadoCivil != null && !estadoCivil.isEmpty() &&
               numeroDeHijos >= 0 &&
               nivelEducativo != null && !nivelEducativo.isEmpty() &&
               ingresoAnual >= 0 &&
               numeroDeMascotas >= 0 &&
               tamanoHogar > 0;
    }
}
